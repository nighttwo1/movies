package com.nighttwo1.data.adapter

import android.util.Log
import com.nighttwo1.domain.NetworkResult
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class NetworkResultCallAdapter<R>(
    private val responseType: Type
) : CallAdapter<R, Call<NetworkResult<R>>> {
    override fun responseType(): Type {
        return responseType
    }

    override fun adapt(p0: Call<R>): Call<NetworkResult<R>> {
        return NetworkResultCall(p0)
    }
}

class NetworkResultCallFactory : CallAdapter.Factory() {
    override fun get(returnType: Type, annotations: Array<out Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        if (getRawType(returnType) != Call::class.java) return null

        // 이후 리턴타입이 제네릭 인자를 가지는지 확인한다. 리턴 타입은 Call<?>가 돼야 한다.
        check(returnType is ParameterizedType) {
            "return type must be parameterized as Call<NetworkResult<Foo>> or Call<NetworkResult<out Foo>>"
        }

        // 리턴 타입에서 첫 번째 제네릭 인자를 얻는다.
        val responseType = getParameterUpperBound(0, returnType)

        // 기대한것 처럼 동작하기 위해선 추출한 제네릭 인자가 Result 타입이어야 한다.
        if (getRawType(responseType) != NetworkResult::class.java) {
            return null
        }

        // NetworkResult 클래스가 제네릭 인자를 가지는지 확인한다. 제네릭 인자로는 응답을 변환할 클래스를 받아야 한다.
        check(responseType is ParameterizedType) {
            "Response must be parameterized as NetworkResult<Foo> or NetworkResult<out Foo>"
        }

        // 마지막으로 NetworkResult 제네릭 인자를 얻어서 CallAdapter를 생성한다.
        val successBodyType = getParameterUpperBound(0, responseType)

        return NetworkResultCallAdapter<Any>(successBodyType)
    }
}

class NetworkResultCall<T>(
    private val call: Call<T>
) : Call<NetworkResult<T>> {
    override fun clone(): Call<NetworkResult<T>> = NetworkResultCall(call.clone())

    override fun execute(): Response<NetworkResult<T>> {
        throw UnsupportedOperationException("NetworkResultCall doesn't support execute")
    }

    override fun enqueue(callback: Callback<NetworkResult<T>>) {
        return call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    Log.d("adpater, successs", "${response.body()}")
                    callback.onResponse(
                        this@NetworkResultCall,
                        Response.success(NetworkResult.Success(response.body()))
                    )
                } else {
                    callback.onResponse(
                        this@NetworkResultCall,
                        Response.success(NetworkResult.Error(Throwable(response.message())))
                    )
                }
            }

            override fun onFailure(call: Call<T>, throwable: Throwable) {
                val networkResponse = NetworkResult.Error<T>(throwable)
                callback.onResponse(this@NetworkResultCall, Response.success(networkResponse))
            }
        })
    }

    override fun isExecuted(): Boolean = call.isExecuted

    override fun cancel() = call.cancel()

    override fun isCanceled(): Boolean = call.isCanceled

    override fun request(): Request = call.request()

    override fun timeout(): Timeout = call.timeout()
}