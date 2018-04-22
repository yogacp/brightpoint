package app.brightpoint.com.vo.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



data class DetailSPBUImagesData(
        @SerializedName("filename")
        @Expose
        var filename: String
)