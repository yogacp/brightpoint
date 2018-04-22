package app.brightpoint.com.session

import android.content.Context
import android.text.TextUtils
import app.brightpoint.com.BrightPointApp
import com.google.android.gms.maps.model.LatLng

class DataSession {
    var isLogin: Boolean = false

    private val PREF_TOKEN = "token"
    private val PREF_EMAIL = "email"
    private val PREF_NAME = "name"
    private val PREF_PHONE = "phone"
    private val PREF_TOKEN_ID = "token_id"
    private val PREF_FIREBASE_TOKEN = "firebase_token"
    private val PREF_FROM_PUSH_NOTIF = "from_push_notif"
    private val PREF_USER_CURRENT_LOCATION = "user_current_location"

    var pref = Pref()

    inner class Pref internal constructor() : BaseSharedPreferences() {
        init {
            check()
        }

        override fun _getApplicationContext(): Context {
            return BrightPointApp.instance
        }

        override fun _getUserProfileName(): String {
            return "user_session"
        }
    }


    fun getLoginToken(): String {
        return pref._getString(PREF_TOKEN, "")!!
    }

    fun saveToken(token: String) {
        pref._setString(PREF_TOKEN, token)
        isLogin = true
    }

    fun getLoginTokenId(): String {
        return pref._getString(PREF_TOKEN_ID, "")!!
    }

    fun saveTokenId(tokenid: String) {
        pref._setString(PREF_TOKEN_ID, tokenid)
        isLogin = true
    }

    fun getEmail(): String {
        return pref._getString(PREF_EMAIL, "")!!
    }

    fun saveEmail(email: String) {
        pref._setString(PREF_EMAIL, email)
    }

    fun getName(): String {
        return pref._getString(PREF_NAME, "")!!
    }

    fun saveName(name: String) {
        pref._setString(PREF_NAME, name)
    }

    fun getPhoneNumber(): String {
        return pref._getString(PREF_PHONE, "")!!
    }

    fun savePhoneNumber(phone: String) {
        pref._setString(PREF_PHONE, phone)
    }

    fun isFromPushNotif(): Boolean {
        return pref._getBoolean(PREF_FROM_PUSH_NOTIF, false)
    }

    fun setFromPushNotif(boolean: Boolean) {
        pref._setBoolean(PREF_FROM_PUSH_NOTIF, boolean)
    }


    fun getFirebaseToken(): String {
        return pref._getString(PREF_FIREBASE_TOKEN, "")!!
    }

    fun saveFirebaseToken(phone: String) {
        pref._setString(PREF_FIREBASE_TOKEN, phone)
    }

    fun isInitialized(): Boolean {
        return !TextUtils.isEmpty(getLoginToken())
    }

    fun saveUserCurrentLocation(location: String) {
        pref._setString(PREF_USER_CURRENT_LOCATION, location)
    }

    fun getUserCurrentLocation(): String {
        return pref._getString(PREF_USER_CURRENT_LOCATION,"")!!
    }

    fun clear() {
        //clear the login session
        pref._clear()
        isLogin = false
    }
}