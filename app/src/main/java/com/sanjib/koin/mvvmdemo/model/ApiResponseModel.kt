package com.sanjib.koin.mvvmdemo.model


data class ApiResponseModel(
    val `data`: Datas,
    val message: Any,
    val status: Boolean
)
data class Datas(
    val has_more: Boolean,
    val users: List<Users>
)
data class Users(
    val image: String,
    val items: List<String>,
    val name: String
)