package com.sanjib.koin.mvvmdemo.api

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import okhttp3.logging.HttpLoggingInterceptor




class ApiService {

/*
    var apiInterface: AppApiInterface? = null


    init {
         initDefaultRetrofitService()

    }



    private fun initDefaultRetrofitService() {
        try {

            val builder = OkHttpClient.Builder()
            builder.connectTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            builder.readTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                builder.addInterceptor(logging)
            }

            builder.addInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder()

                chain.proceed(requestBuilder.build())
            }

            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build()

            apiInterface = retrofit.create<AppApiInterface>(AppApiInterface::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


    companion object {
        const val DEFAULT_TIMEOUT = 50 * 1000
    }
*/


    fun createHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        val client = OkHttpClient.Builder()
        client.addInterceptor(logging)
        client.readTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
        return client.addInterceptor {
            val original = it.request()
            val requestBuilder = original.newBuilder()
            val request = requestBuilder.method(original.method(), original.body()).build()
            return@addInterceptor it.proceed(request)
        }.build()
    }

    inline fun <reified T> createWebService(
        okHttpClient: OkHttpClient, baseUrl: String
    ): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()
        return retrofit.create(T::class.java)
    }

        companion object {
        const val DEFAULT_TIMEOUT = 50 * 1000
    }

}