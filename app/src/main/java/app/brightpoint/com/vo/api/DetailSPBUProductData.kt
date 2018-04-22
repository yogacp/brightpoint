package app.brightpoint.com.vo.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



data class DetailSPBUProductData(
        @SerializedName("name")
        @Expose
        var name: String,
        @SerializedName("price")
        @Expose
        var price: Int,
        @SerializedName("is_available")
        @Expose
        var isAvailable: Int
)