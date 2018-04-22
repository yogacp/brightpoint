package app.brightpoint.com.vo.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



data class DetailBrightWashData(
        @SerializedName("id")
        @Expose
        var id: Int,
        @SerializedName("capacity")
        @Expose
        var capacity: Int,
        @SerializedName("name")
        @Expose
        var name: String,
        @SerializedName("latitude")
        @Expose
        var latitude: String,
        @SerializedName("longitude")
        @Expose
        var longitude: String,
        @SerializedName("address")
        @Expose
        var address: String,
        @SerializedName("current_queue")
        @Expose
        var currentQueue: Int,
        @SerializedName("image")
        @Expose
        var image: String
)