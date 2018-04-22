package app.brightpoint.com.api.responses.success

import com.google.gson.annotations.SerializedName

data class BaseApiResponse<T>(
        @SerializedName("result_code") val resultCode : Int,
        @SerializedName("result_message") val resultMessage : String,
        @SerializedName("data") val data : T
)