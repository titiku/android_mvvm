package com.example.android_mvvm

import android.util.Log
import androidx.lifecycle.*
import com.example.core.MobileEntity
import com.example.core.MobileImageEntity
import com.example.core.MobilesListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MobileDetailViewModel
@Inject constructor(
    private var repository: MobilesListRepository
) : ViewModel() {
    private var _mobile = MutableLiveData<MobileEntity>()
    private var _mobileImage = MutableLiveData<List<MobileImageEntity>>()
    var mobile: LiveData<MobileEntity> = _mobile
    var mobileImage: LiveData<List<MobileImageEntity>> = _mobileImage

    fun getMobile(mobileId: Int) {
        viewModelScope.launch {
            repository.getMobileDetail(mobileId).collect {
                when {
                    it.isLoading -> {
                        Log.i("balltest", "isLoading")
                    }
                    it.isFail -> {
                        Log.i("balltest", "isFail")
                    }
                    else -> {
                        _mobile.value = it.valueOrNull
                    }
                }
            }
        }
    }

    fun getMobileImage(mobileId: Int) {
        viewModelScope.launch {
            repository.getMobileImage(mobileId).collect {
                when {
                    it.isLoading -> {
                        Log.i("balltest", "isLoading")
                    }
                    it.isFail -> {
                        Log.i("balltest", "isFail")
                    }
                    else -> {
                        _mobileImage.value = it.valueOrNull
                        Log.i("balltest", _mobileImage.value.toString())
                    }
                }
            }
        }
    }
}