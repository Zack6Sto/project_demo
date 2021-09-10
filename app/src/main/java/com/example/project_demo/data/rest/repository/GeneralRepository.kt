package com.example.project_demo.data.rest.repository

import com.example.project_demo.data.rest.APIService
import com.example.project_demo.vo.models.response.RespRegister
import com.example.project_demo.vo.models.resq.resqRegister
import io.reactivex.Observable
import okhttp3.MultipartBody
import java.io.File

class GeneralRepository
constructor(
    private val apiService: APIService
) {

//    fun onLogin(paramLogin: BodyLogin): Observable<ResponseLogin> {
//        return apiService.doLogin(paramLogin)
//    }
//
//    fun onLoginFacebook(paramLogin: BodyLoginFaceBook) = apiService.doLoginFacebook(paramLogin)
//
//    fun doForgotPassword(bodyForgotPassword: BodyForgotPassword) =
//        apiService.doForgotPassword(bodyForgotPassword)
//
//    fun getTermOfUse() = apiService.getTermOfUse()
//
//    fun getPolicy() = apiService.getPolicy()
//
    fun getContactsUs() = apiService.getContactUs()

    fun getListUsers() = apiService.getListUsers()

//
//    fun onRegisterHaveImage(data: BodyRegister, profileImage: File?): Observable<ResponseRegister> {
//        return apiService.registerUser(
//            onConvertFileToMultipartBody(profileImage!!, "image"),
//            onConvertObjectToMap(data)
//        )
//    }
//
//    fun doAddReviewOrder(
//        token: String,
//        imageList: ArrayList<MultipartBody.Part?>,
//        data: BodyReviewOrder
//    ): Observable<BaseResponse> {
//        return apiService.addReviewOrder(
//            token,
//            imageList,
//            onConvertObjectToMap(data)
//        )
//    }
//
//    fun doAddReviewOrder(token: String, data: BodyReviewOrder): Observable<BaseResponse> {
//        return apiService.addReviewOrder(
//            token,
//            onConvertObjectToMap(data)
//        )
//    }
//
//    fun onRegisterNoImage(data: BodyRegister): Observable<ResponseRegister> {
//        return apiService.registerUser(
//            onConvertObjectToMap(data)
//        )
//    }
        fun onRegister(data: resqRegister): Observable<RespRegister> {
        return apiService.register(
            data
        )
    }

//
//    fun onLogout(token: String) =
//        apiService.doLogout(token)
//
//    fun onChangePassword(userId: String, data: BodyChangePassword, token: String) =
//        apiService.doUpdatePassword(
//            userId,
//            data,
//            token
//        )
//
//    fun getProfile(token: String) =
//        apiService.getProfile(token)
//
//    fun updateProfile(userId: String, token: String, data: BodyUpdateProfile) =
//        apiService.updateProfile(
//            userId,
//            token,
//            onConvertObjectToMap(data)
//        )
//
//    fun getOrderList(language: String, pageCurrent: Int, token: String) =
//        apiService.getOrderBookings(language, token, pageCurrent)
}
