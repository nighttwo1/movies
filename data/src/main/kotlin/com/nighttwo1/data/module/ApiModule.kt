package com.nighttwo1.data.module

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.nighttwo1.data.BuildConfig
import com.nighttwo1.data.adapter.NetworkResultCallFactory
import com.nighttwo1.data.service.AccountService
import com.nighttwo1.data.service.MovieService
import com.nighttwo1.data.service.SearchService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    @Provides
    fun provideBaseUrl() = "https://api.themoviedb.org/3/"

    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor())
            .addInterceptor(loggingInterceptor)
            .build()
    } else OkHttpClient
        .Builder()
        .addInterceptor(RequestInterceptor())
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        baseUrl: String,
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(NetworkResultCallFactory())
            .addConverterFactory(json.asConverterFactory("application/json".toMediaTypeOrNull()!!))
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideMovieService(retrofit: Retrofit): MovieService = retrofit.create(MovieService::class.java)

    @Provides
    @Singleton
    fun provideSearchService(retrofit: Retrofit): SearchService = retrofit.create(SearchService::class.java)

    @Provides
    @Singleton
    fun provideAccountService(retrofit: Retrofit): AccountService = retrofit.create(AccountService::class.java)
}

class RequestInterceptor: Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
        val newRequest = request().newBuilder()
            .addHeader("Authorization", "Bearer ${BuildConfig.API_TOKEN}")
            .build()
        chain.proceed(newRequest)
    }
}

val json = Json {
    ignoreUnknownKeys = true
}