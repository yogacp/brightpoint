package app.brightpoint.com.vo.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DetailSPBUData(
        @SerializedName("detail")
        @Expose
        var detail: DetailData,
        @SerializedName("images")
        @Expose
        var images: List<DetailSPBUImagesData>,
        @SerializedName("products")
        @Expose
        var products: List<DetailSPBUProductData>
)