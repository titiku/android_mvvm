package com.example.core

import io.reactivex.Single
import javax.inject.Inject

class MobilesListApi @Inject constructor(
    private val service: MobilesListService
) {
    fun getMobilesList(): Single<List<MobilesListEntity>> {
        return service.getMobilesList()
    }
}