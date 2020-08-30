package com.sanjib.koin.mvvmdemo.interfaces

import com.sanjib.koin.mvvmdemo.model.ApiResponseModel
import com.sanjib.koin.mvvmdemo.model.ListApi
import com.sanjib.koin.mvvmdemo.model.User
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap


interface AppApiInterface {

    @GET("/api/users?")
    fun apiData(@Query("page")pages:String): Deferred<Response<User>>


    @GET("marvel")
    fun getList(): Deferred<Response<List<ListApi>>>


    @GET("users")
    fun getList( @QueryMap params: HashMap<String,String>): Deferred<ApiResponseModel>
}