package com.example.android_mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {
    var mobilesList = MutableLiveData<String>()
    fun onTimeFinished() = mobilesList

    fun setMobilesList() {
        mobilesList.value = MobilesList("test").name
        CoroutineScope(Dispatchers.IO).launch {
            delay(3000)
//            val list = listOf(MobilesList("test"))
            withContext(Dispatchers.Main) {
                mobilesList.value = MobilesList("test123").name
            }
        }
    }
}