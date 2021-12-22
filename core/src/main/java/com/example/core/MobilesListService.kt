package com.example.core

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MobilesListService {
    @GET("api/mobiles")
    fun getMobilesList(): Single<List<MobileEntity>>

    @GET("api/mobiles")
    suspend fun getMobilesListFLow(): List<MobileEntity>

    @GET("api/mobiles/{mobileId}")
    suspend fun getMobileDetail(@Path("mobileId") mobileId: Int): MobileEntity

    @GET("api/mobiles/{mobileId}/images")
    suspend fun getMobileDetailImage(@Path("mobileId") mobileId: Int): List<MobileImageEntity>
}