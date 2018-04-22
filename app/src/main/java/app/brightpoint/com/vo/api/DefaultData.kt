package app.brightpoint.com.vo.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



data class DefaultData (
        @SerializedName("result_code")
        @Expose
        var resultCode: Int,
        @SerializedName("result_message")
        @Expose
        var resultMessage: String,
        @SerializedName("data")
        @Expose
        var data: String
)