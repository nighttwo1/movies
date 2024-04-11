package com.nighttwo1.data.adapter

import android.util.Log
import com.nighttwo1.domain.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <T> networkAdapter(call: suspend () -> T): Flow<NetworkResult<T>> = flow {
    emit(NetworkResult.Loading())
    Log.d("network", "loading")
    try {
        emit(NetworkResult.Success(call()))
    } catch (e: Exception) {
        Log.d("network", "error: $e")
        emit(NetworkResult.Error(e))
    }
}