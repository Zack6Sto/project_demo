package com.example.project_demo.data.rest

import com.example.project_demo.vo.models.User
import com.example.project_demo.vo.models.response.RespRegister
import com.example.project_demo.vo.models.response.ResponseContacts
import com.example.project_demo.vo.models.resq.resqRegister
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.*

interface APIService {

//    @POST("api/login")
//    fun doLogin(@Body paramLogin: BodyLogin): Observable<ResponseLogin>
//
//    @POST("api/login/facebook")
//    fun doLoginFacebook(@Body paramLogin: BodyLoginFaceBook): Observable<ResponseLogin>
//
//    @POST("api/logout")
//    fun doLogout(
//        @Header("Authorization") accessToken: String
//    ): Observable<BaseResponse>
//
//    @GET("api/term")
//    fun getTermOfUse(): Observable<ResponseTermOfUse>
//
//    @GET("api/policy")
//    fun getPolicy(): Observable<ResponseTermOfUse>
//
    @GET("api/contactus")
    fun getContactUs(): Observable<ResponseContacts>

    @GET("users")
    fun getListUsers(): Observable<User>

//
//    @POST("api/forgotpassword")
//    fun doForgotPassword(
//        @Body bodyForgotPassword: BodyForgotPassword
//    ): Observable<BaseResponse>
//
//    @PUT("api/user/profile/{id}")
//    fun doUpdatePassword(
//        @Path("id") id: String,
//        @Body body: BodyChangePassword,
//        @Header("Authorization") accessToken: String
//    ): Observable<BaseResponse>
//
//    @GET("shops/bookings")
//    fun getOrderBookings(
//        @Header("Accept-Language") language: String? = "en_US",
//        @Header("Authorization") accessToken: String,
//        @Query("page") page: Int
//    ): Observable<ResponseOrderList>
//
//    @GET("api/user/profile")
//    fun getProfile(
//        @Header("Authorization") accessToken: String
//    ): Observable<ResponseProfile>
//
//    @Multipart
//    @POST("api/register/user")
//    fun registerUser(
//        @Part profileImage: MultipartBody.Part?,
//        @PartMap registerBody: MutableMap<String, @JvmSuppressWildcards RequestBody?>?
//    ): Observable<ResponseRegister>


    @Multipart
    @POST("/register")
    fun register(
        @Body param :resqRegister
    ): Observable<RespRegister>

//
//    @Multipart
//    @POST("api/register/user")
//    fun registerUser(
//        @PartMap registerBody: MutableMap<String, @JvmSuppressWildcards RequestBody?>?
//    ): Observable<ResponseRegister>
//
//    @Multipart
//    @POST("api/user/profile/{id}")
//    fun updateProfile(
//        @Path("id") userId: String,
//        @Header("Authorization") accessToken: String,
//        @PartMap profileBody: MutableMap<String, @JvmSuppressWildcards RequestBody?>?
//    ): Observable<BaseResponse>
//
//    @Multipart
//    @POST("api/user/profile/{id}")
//    fun updateImageProfile(
//        @Path("id") id: String,
//        @Header("Authorization") accessToken: String,
//        @Part profileImage: MultipartBody.Part?,
//        @PartMap profileBody: MutableMap<String, @JvmSuppressWildcards RequestBody?>?
//    ): Observable<BaseResponse>
//
//    @GET("api/user/shop/{id}")
//    fun getShopInfo(
//        @Path("id") id: Int,
//        @Header("Authorization") accessToken: String,
//        @Query("pageCurrent") pageCurrent: Int? = 1
//    ): Observable<ResponseShopInfo>
//
//    @GET("/api/user/promotion/{id}")
//    fun getListPromotion(
//        @Path("id") id: Int,
//        @Header("Authorization") accessToken: String
//    ):Observable<ResponseListPromotion>
//
//    @GET("api/user/menu/{id}")
//    fun getMenuDetail(
//        @Path("id") id: Int,
//        @Header("Authorization") accessToken: String
//    ): Observable<ResponseMenuDetail>
//
//    @POST("api/user/cart")
//    fun doAddCart(
//        @Header("Authorization") accessToken: String,
//        @Body bodyCart: BodyCart
//    ): Observable<BaseResponse>
//
//    @GET("api/user/cart/{id}")
//    fun getListCart(
//        @Path("id") id: Int,
//        @Header("Authorization") accessToken: String
//    ): Observable<ModelBasket>
//
//    @PUT("api/user/cart/{id}")
//    fun doUpdateCart(
//        @Path("id") id: Int,
//        @Header("Authorization") accessToken: String,
//        @Body bodyUpdateCart: BodyUpdateCart
//    ): Observable<ModelBasket>
//
//    @DELETE("api/user/cart/{id}")
//    fun doDeleteItemCart(
//        @Path("id") id: Int,
//        @Header("Authorization") accessToken: String
//    ): Observable<ModelBasket>
//
//    @GET("api/user/favorite/{id}")
//    fun getListFavorites(
//        @Path("id") id: Int,
//        @Header("Authorization") accessToken: String
//    ): Observable<ModelFavorites>
//
//    @DELETE("api/user/favorite/{id}")
//    fun doDeleteFavorite(
//        @Path("id") id: Int,
//        @Header("Authorization") accessToken: String
//    ): Observable<BaseResponse>
//
//    @POST("api/user/favorite")
//    fun doFavorite(
//        @Header("Authorization") accessToken: String,
//        @Body bodyCart: BodyFavorite
//    ): Observable<BaseResponse>
//
//
//    @POST("api/user/payment")
//    fun doPayment(
//        @Header("Authorization") accessToken: String,
//        @Body bodyPayment: BodyPayment
//    ): Observable<BaseResponse>
//
//    @GET("api/user/order")
//    fun getListOrders(
//        @Header("Authorization") accessToken: String,
//        @Query("shopId") shopId: Int,
//        @Query("tabMenu") tabMenu: String
//    ): Observable<ResponseOrderLists>
//
//    @GET("api/user/order/{id}")
//    fun getOrdersDetail(
//        @Path("id") id: Int,
//        @Header("Authorization") accessToken: String
//    ): Observable<ResponseOrderDetail>
//
//    @Multipart
//    @POST("api/user/review")
//    fun addReviewOrder(
//        @Header("Authorization") accessToken: String,
//        @Part profileImage: List<MultipartBody.Part?>,
//        @PartMap registerBody: MutableMap<String, @JvmSuppressWildcards RequestBody?>?
//    ): Observable<BaseResponse>
//
//    @Multipart
//    @POST("api/user/review")
//    fun addReviewOrder(
//        @Header("Authorization") accessToken: String,
//        @PartMap registerBody: MutableMap<String, @JvmSuppressWildcards RequestBody?>?
//    ): Observable<BaseResponse>
//
//
//    @GET("api/user/notification")
//    fun getListNotifications(
//        @Header("Authorization") accessToken: String
//    ): Observable<ResponseNotifications>
//
//    @PUT("api/user/notification/{id}")
//    fun doReadNotifications(
//        @Path("id")id: Int,
//        @Header("Authorization") accessToken: String
//    ): Observable<BaseResponse>

}
