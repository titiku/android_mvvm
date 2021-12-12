package com.example.android_mvvm

import android.util.Log
import androidx.lifecycle.*
import com.example.core.MobileEntity
import com.example.core.MobilesListCache
import com.example.core.MobilesListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(
    private var repository: MobilesListRepository,
    private var cache: MobilesListCache
) : ViewModel() {
    private var listFav: ArrayList<MobileEntity> = arrayListOf()
    private var _mobilesList = MutableLiveData<List<MobileEntity>>()
    var mobilesList: LiveData<List<MobileEntity>> = _mobilesList

    fun getMobilesList(pageIndex: Int) {
        listFav = cache.getMobilesListCache().toCollection(ArrayList())
        if (pageIndex == 0) {
            viewModelScope.launch {
                repository.getMobilesListFlow().collect {
                    when {
                        it.isLoading -> {
                            Log.i("balltest", "isLoading")
                        }
                        it.isFail -> {
                            Log.i("balltest", "isFail")
                        }
                        else -> {
                            _mobilesList.value = it.valueOrNull?.filter { mobile ->
                                listFav.filter { mobileCache -> mobileCache.id != mobile.id }.size == listFav.size
                            }
                        }
                    }
                }
            }
        } else {
            _mobilesList.value = listFav
        }
    }

    fun clickFavorite(mobileEntity: MobileEntity, pageIndex: Int) {
        if (pageIndex == 0) {
            _mobilesList.value?.toCollection(ArrayList())?.apply {
                remove(mobileEntity)
                _mobilesList.value = this
            }
            listFav.add(mobileEntity)
            cache.setMobilesListCache(listFav)
        } else {
            listFav.remove(mobileEntity)
            _mobilesList.value = listFav
            cache.setMobilesListCache(listFav)
        }
    }
}