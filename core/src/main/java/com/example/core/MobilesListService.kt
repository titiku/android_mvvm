package com.example.core

import io.reactivex.Single
import retrofit2.http.GET

interface MobilesListService {
    @GET("api/mobiles")
    fun getMobilesList(): Single<List<MobileEntity>>
}