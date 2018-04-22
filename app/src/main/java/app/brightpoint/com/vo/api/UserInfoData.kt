package app.brightpoint.com.vo.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



data class UserInfoData (
        @SerializedName("name")
        @Expose
        var name: String,
        @SerializedName("email")
        @Expose
        var email: String,
        @SerializedName("phone")
        @Expose
        var phone: String
)