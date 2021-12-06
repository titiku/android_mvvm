package com.example.core

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MobilesListController
@Inject constructor(
    private var api: MobilesListApi
) {
    fun getMobilesList(
        onSuccess: (List<MobilesListEntity>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        api.getMobilesList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<List<MobilesListEntity>>() {
                override fun onSuccess(list: List<MobilesListEntity>) {
                    onSuccess(list)
                }

                override fun onError(e: Throwable) {
                    onError(e)
                }
            })
    }
}