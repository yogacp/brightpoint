package app.brightpoint.com.vo.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



data class LoginData (
        @SerializedName("email")
        @Expose
        var email: String,
        @SerializedName("phone")
        @Expose
        var phone: String,
        @SerializedName("name")
        @Expose
        var name: String,
        @SerializedName("token")
        @Expose
        var token: String
)