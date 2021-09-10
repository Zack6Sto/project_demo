package com.example.project_demo.domain

import android.os.Handler
import com.example.project_demo.data.Preferences
import com.example.project_demo.data.rest.NetworkBoundResource
import com.example.project_demo.data.rest.repository.GeneralRepository
import com.example.project_demo.vo.models.User
import com.example.project_demo.vo.models.response.RespRegister
import com.example.project_demo.vo.models.response.ResponseContacts
import com.example.project_demo.vo.models.resq.resqRegister
import io.reactivex.Observable

class GeneralUseCase(
    private val generalRepository: GeneralRepository,
    private val preferences: Preferences
) {
    private var mHandle = Handler()

//    fun doLogin(paramLogin: BodyLogin) =
//        object : NetworkBoundResource<ResponseLogin>() {
//            override fun saveCallResult(item: String) {}
//            override fun createCall(): Observable<ResponseLogin> =
//                generalRepository.onLogin(paramLogin)
//        }.asLiveData()
//
//    fun doForgotPassword(bodyForgotPassword: BodyForgotPassword) =
//        object : NetworkBoundResource<BaseResponse>() {
//            override fun saveCallResult(item: String) {}
//            override fun createCall(): Observable<BaseResponse> =
//                generalRepository.doForgotPassword(bodyForgotPassword)
//        }.asLiveData()
//
//    fun getTermOfUse() =
//        object : NetworkBoundResource<ResponseTermOfUse>() {
//            override fun saveCallResult(item: String) {}
//            override fun createCall(): Observable<ResponseTermOfUse> =
//                generalRepository.getTermOfUse()
//        }.asLiveData()
//
//    fun getPolicy() =
//        object : NetworkBoundResource<ResponseTermOfUse>() {
//            override fun saveCallResult(item: String) {}
//            override fun createCall(): Observable<ResponseTermOfUse> =
//                generalRepository.getPolicy()
//        }.asLiveData()
//
    fun getContactsUs() =
        object : NetworkBoundResource<ResponseContacts>() {
            override fun saveCallResult(item: String) {}
            override fun createCall(): Observable<ResponseContacts> =
                generalRepository.getContactsUs()
        }.asLiveData()

    fun getListUsers() =
        object : NetworkBoundResource<User>() {
            override fun saveCallResult(item: String) {}
            override fun createCall(): Observable<User> =
                generalRepository.getListUsers()
        }.asLiveData()

//
//    fun doLoginFacebook(paramLogin: BodyLoginFaceBook) =
//        object : NetworkBoundResource<ResponseLogin>() {
//            override fun saveCallResult(item: String) {}
//            override fun createCall(): Observable<ResponseLogin> =
//                generalRepository.onLoginFacebook(paramLogin)
//        }.asLiveData()
//
//    fun doLogout() =
//        object : NetworkBoundResource<BaseResponse>() {
//            override fun saveCallResult(item: String) {}
//            override fun createCall(): Observable<BaseResponse> =
//                generalRepository.onLogout(preferences.getToken())
//        }.asLiveData()
//
//    fun doChangePassword(bodyChangePw: BodyChangePassword) =
//        object : NetworkBoundResource<BaseResponse>() {
//            override fun createCall(): Observable<BaseResponse> =
//                generalRepository.onChangePassword(
//                    preferences.getUserId() ?: "",
//                    bodyChangePw,
//                    preferences.getToken()
//                )
//
//            override fun saveCallResult(item: String) {}
//        }.asLiveData()
//
//    fun doRegisterHaveImage(bodyRegister: BodyRegister, image: File?) =
//        object : NetworkBoundResource<ResponseRegister>() {
//            override fun saveCallResult(item: String) {}
//            override fun createCall(): Observable<ResponseRegister> =
//                if (image != null) {
//                    generalRepository.onRegisterHaveImage(bodyRegister, image)
//                } else {
//                    generalRepository.onRegisterNoImage(bodyRegister)
//                }
//
//        }.asLiveData()

    fun doRegister(bodyRegister: resqRegister) =
        object : NetworkBoundResource<RespRegister>() {
            override fun createCall(): Observable<RespRegister> =
                generalRepository.onRegister(
                    bodyRegister
                )
            override fun saveCallResult(item: String) {}
        }.asLiveData()

//
//    fun doAddReviewOrders(
//        bodyReviewOrder: BodyReviewOrder,
//        image: ArrayList<MultipartBody.Part?>?
//    ) =
//        object : NetworkBoundResource<BaseResponse>() {
//            override fun saveCallResult(item: String) {}
//            override fun createCall(): Observable<BaseResponse> =
//                if (image != null && image.isNotEmpty()) {
//                    generalRepository.doAddReviewOrder(
//                        preferences.getToken(),
//                        image,
//                        bodyReviewOrder
//                    )
//                } else {
//                    generalRepository.doAddReviewOrder(
//                        preferences.getToken(),
//                        bodyReviewOrder
//                    )
//                }
//
//        }.asLiveData()
//
//
//    fun getProfile() = object : NetworkBoundResource<ResponseProfile>() {
//        override fun createCall(): Observable<ResponseProfile> =
//            generalRepository.getProfile(preferences.getToken())
//
//        override fun saveCallResult(item: String) {}
//    }.asLiveData()
//
//
//    fun updateProfile(bodyProfile: BodyUpdateProfile, image: File?) =
//        object : NetworkBoundResource<BaseResponse>() {
//            override fun createCall(): Observable<BaseResponse> =
//                generalRepository.updateProfile(
//                    preferences.getUserId() ?: "",
//                    preferences.getToken(),
//                    bodyProfile
//                )
//
//            override fun saveCallResult(item: String) {}
//        }.asLiveData()
//
//
//    fun doUpdateShopInfo(
//        bodyProfile: BodyUpdateProfile, image: File?
//    ): MutableLiveData<ModelMergeUpdateProfile> {
//        val mResponse = MutableLiveData<ModelMergeUpdateProfile>()
//
//        Observable.zip(
//            HttpRetrofit.myAppService.updateProfile(
//                preferences.getUserId() ?: "",
//                preferences.getToken(),
//                ConvertRequestBody.onConvertObjectToMap(bodyProfile)
//            ).subscribeOn(Schedulers.io()),
//            HttpRetrofit.myAppService.updateImageProfile(
//                preferences.getUserId() ?: "",
//                preferences.getToken(),
//                ConvertRequestBody.onConvertFileToMultipartBody(image!!, "image"),
//                ConvertRequestBody.onConvertObjectToMap(BodyUpdateImageProfile())
//            ).subscribeOn(Schedulers.io()),
//            BiFunction<BaseResponse, BaseResponse, ModelMergeUpdateProfile> { t1, t2 ->
//                ModelMergeUpdateProfile(t1, t2, "")
//            })
//            .subscribe(object : Observer<ModelMergeUpdateProfile> {
//                override fun onComplete() {
//                }
//
//                override fun onSubscribe(d: Disposable) {
//                }
//
//                override fun onNext(data: ModelMergeUpdateProfile) {
//                    mHandle.post {
//                        mResponse.value = data
//                    }
//                }
//
//                override fun onError(e: Throwable) {
//                    mHandle.post {
//                        mResponse.value =
//                            ModelMergeUpdateProfile(
//                                null,
//                                null,
//                                ApiResponse.onErrorResponseServer(e)
//                            )
//                    }
//                }
//            })
//        return mResponse
//    }

//    fun getOrderList(pageCurrent: Int) = object : NetworkBoundResource<ResponseOrderList>() {
//        override fun saveCallResult(item: String) {}
//        override fun createCall(): Observable<ResponseOrderList> =
//            generalRepository.getOrderList("th", pageCurrent, preferences.getToken())
//    }.asLiveData()

}