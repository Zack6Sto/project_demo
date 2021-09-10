package com.example.project_demo.vo.models.response

class RespRegister : ArrayList<RespRegisterItem>()

data class RespRegisterItem(
    val id: Int,
    val lname: String,
    val fname: String,
    val email: String,
    val password: String,
    val username: String,
    val cf_password: String
)