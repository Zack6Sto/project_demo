package com.example.project_demo.data.rest

import com.example.project_demo.Constants.Constants
import com.example.project_demo.vo.models.response.ModelError
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException

object ApiResponse {

    private val mGson = Gson()

    fun onErrorResponseServer(e: Throwable): String {
        var mMessageError: String
        try {
            mMessageError = when (e) {
                is retrofit2.HttpException -> {
                    val responseBody = (e).response()!!
                    if (responseBody.code() == 401) {
                        "401"
                    } else {
                        val dataMessage = responseBody.errorBody()!!.string()
                        deseRializeObject(dataMessage)
                    }
                }
                else -> {
                    val responseBody = e.message
                    if (responseBody!!.contains("No address associated with hostname")) {
                        Constants.MESSAGE_NO_INTERNET
                    } else
                        responseBody.toString()
                }
            }
        } catch (ex: Exception) {
            mMessageError = "${e.message}"
        }
        return mMessageError
    }

    private fun deseRializeObject(errorString: String): String {
        return try {
            mGson.fromJson(errorString, ModelError::class.java).errors.message
                ?: "som ting went wrong"
        } catch (e: JsonSyntaxException) {
            e.message.toString()
        }
    }
}
