package com.example.project_demo.vo.models.response

data class ModelError(
    val errors: Errors
)

data class Errors(val message: String?)
