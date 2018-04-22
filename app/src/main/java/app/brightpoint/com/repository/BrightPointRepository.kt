package app.brightpoint.com.repository

import app.brightpoint.com.api.NetworkServices
import app.brightpoint.com.api.responses.success.BaseApiResponse
import app.brightpoint.com.helper.Helper
import app.brightpoint.com.session.DataSession
import app.brightpoint.com.vo.api.*
import com.google.gson.Gson
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BrightPointRepository @Inject constructor(var mNetworkServices: NetworkServices){

    /**
     * Register Accounts
     */
    fun registerAccount(data: String): Flowable<BaseApiResponse<DefaultData>> {
        return mNetworkServices.registration(data)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
    }


    /**
     * User Login
     */
    fun userLogin(data: String): Flowable<BaseApiResponse<LoginData>> {
        return mNetworkServices.login(data)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
    }


    /**
     * User Information
     */
    fun userInfo(userInfo: String): Flowable<BaseApiResponse<UserInfoData>> {
        return mNetworkServices.userInformation(userInfo)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
    }


    /**
     * Search Nearest SPBU
     */
    fun searchNearestSPBU(userInfo: String, data: String): Flowable<BaseApiResponse<List<NearestSPBUData>>> {
        return mNetworkServices.nearestSpbu(userInfo, data)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
    }


    /**
     * Get Detail SPBU
     */
    fun getDetailSPBU(userInfo: String, data: String): Flowable<BaseApiResponse<DetailSPBUData>> {
        return mNetworkServices.detailSpbu(userInfo, data)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * Search BrightWash
     */
    fun findBrightWash(userInfo: String, data: String): Flowable<BaseApiResponse<List<FindBrightWashData>>> {
        return mNetworkServices.findBrightWash(userInfo, data)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
    }


    /**
     * Get Detail BrightWash
     */
    fun getDetailBrightwash(userInfo: String, data: String): Flowable<BaseApiResponse<DetailBrightWashData>> {
        return mNetworkServices.detailBrightWash(userInfo, data)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
    }


    /**
     * Book Confirmation BrightWash
     */
    fun bookConfirmation(userInfo: String, data: String): Flowable<BaseApiResponse<BookingConfirmedData>> {
        return mNetworkServices.confirmQueue(userInfo, data)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * Wash Schedule BrightWash
     */
    fun washSchedule(userInfo: String): Flowable<BaseApiResponse<List<WashScheduleData>>> {
        return mNetworkServices.washSchedule(userInfo)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
    }

}