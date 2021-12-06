package com.example.android_mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.MobilesListController
import com.example.core.MobileEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(
    private var controller: MobilesListController
) : ViewModel() {
    private var _mobilesList = MutableLiveData<List<MobileEntity>>()
    val mobilesList: LiveData<List<MobileEntity>> = _mobilesList

    fun getMobilesList() {
        controller.getMobilesList(
            {
                Log.i("balltest", it.toString())
                _mobilesList.value = it
            }, {
                Log.i("balltest", it.toString())
            })
    }
}