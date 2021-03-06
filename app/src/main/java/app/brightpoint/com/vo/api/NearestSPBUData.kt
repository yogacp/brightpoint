package app.brightpoint.com.vo.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



data class NearestSPBUData(
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
        @SerializedName("is_mosque")
        @Expose
        var isMosque: Int,
        @SerializedName("is_toilet")
        @Expose
        var isToilet: Int,
        @SerializedName("is_brightwash")
        @Expose
        var isBrightwash: Int,
        @SerializedName("is_snack_store")
        @Expose
        var isSnackStore: Int,
        @SerializedName("is_olimart")
        @Expose
        var isOlimart: Int,
        @SerializedName("distance")
        @Expose
        var distance: Double
)