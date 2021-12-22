package com.example.core

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MobilesListRepository
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

    fun getMobilesListFlow(): Flow<Resource<List<MobileEntity>>> {
        return flow {
            emit(Resource.Loading)
            val resource = try {
                val response = service.getMobilesListFLow()
                Resource.Success(response)
            } catch (e: Throwable) {
                Resource.Fail(e)
            }
            emit(resource)
        }
    }

    fun getMobileDetail(mobileId: Int): Flow<Resource<MobileEntity>> {
        return flow {
            emit(Resource.Loading)
            val resource = try {
                val response = service.getMobileDetail(mobileId)
                Resource.Success(response)
            } catch (e: Throwable) {
                Resource.Fail(e)
            }
            emit(resource)
        }
    }

    fun getMobileImage(mobileId: Int): Flow<Resource<List<MobileImageEntity>>> {
        return flow {
            emit(Resource.Loading)
            val resource = try {
                val response = service.getMobileDetailImage(mobileId)
                Resource.Success(response)
            } catch (e: Throwable) {
                Resource.Fail(e)
            }
            emit(resource)
        }
    }
}