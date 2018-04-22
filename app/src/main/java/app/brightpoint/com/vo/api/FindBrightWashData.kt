package app.brightpoint.com.vo.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



data class FindBrightWashData(
        @SerializedName("id")
        @Expose
        var id: Int,
        @SerializedName("name")
        @Expose
        var name: String,
        @SerializedName("address")
        @Expose
        var address: String,
        @SerializedName("latitude")
        @Expose
        var latitude: String,
        @SerializedName("longitude")
        @Expose
        var longitude: String,
        @SerializedName("distance")
        @Expose
        var distance: Double,
        @SerializedName("capacity")
        @Expose
        var capacity: Int,
        @SerializedName("current_queue")
        @Expose
        var currentQueue: Int
)