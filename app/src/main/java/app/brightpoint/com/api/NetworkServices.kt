package app.brightpoint.com.api

import app.brightpoint.com.api.responses.success.BaseApiResponse
import app.brightpoint.com.vo.api.*
import io.reactivex.Flowable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface NetworkServices {
    @FormUrlEncoded
    @POST("/api/login")
    fun login(@Field("data") param: String): Flowable<BaseApiResponse<LoginData>>

    @FormUrlEncoded
    @POST("/api/register")
    fun registration(@Field("data") param: String): Flowable<BaseApiResponse<DefaultData>>

    @FormUrlEncoded
    @POST("/api/user_information")
    fun userInformation(@Field("user_info") param: String): Flowable<BaseApiResponse<UserInfoData>>

    @FormUrlEncoded
    @POST("/api/nearest_spbu")
    fun nearestSpbu(@Field("user_info") param: String, @Field("data") data: String): Flowable<BaseApiResponse<List<NearestSPBUData>>>

    @FormUrlEncoded
    @POST("/api/detail_spbu")
    fun detailSpbu(@Field("user_info") param: String, @Field("data") data: String): Flowable<BaseApiResponse<DetailSPBUData>>

    @FormUrlEncoded
    @POST("/api/find_brightwash")
    fun findBrightWash(@Field("user_info") param: String, @Field("data") data: String): Flowable<BaseApiResponse<List<FindBrightWashData>>>

    @FormUrlEncoded
    @POST("/api/detail_brightwash")
    fun detailBrightWash(@Field("user_info") param: String, @Field("data") data: String): Flowable<BaseApiResponse<DetailBrightWashData>>

    @FormUrlEncoded
    @POST("/api/confirm_queue")
    fun confirmQueue(@Field("user_info") param: String, @Field("data") data: String): Flowable<BaseApiResponse<BookingConfirmedData>>

    @FormUrlEncoded
    @POST("/api/wash_schedule")
    fun washSchedule(@Field("user_info") param: String): Flowable<BaseApiResponse<List<WashScheduleData>>>
}