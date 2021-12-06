package com.example.core

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MobilesListController
@Inject constructor(
    private var service: MobilesListService
) {
    fun getMobilesList(
        onSuccess: (List<MobileEntity>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        service.getMobilesList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<List<MobileEntity>>() {
                override fun onSuccess(list: List<MobileEntity>) {
                    onSuccess(list)
                }

                override fun onError(e: Throwable) {
                    onError(e)
                }
            })
    }
}