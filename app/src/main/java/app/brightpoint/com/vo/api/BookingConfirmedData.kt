package app.brightpoint.com.vo.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BookingConfirmedData(
        @SerializedName("queue_no")
        @Expose
        var queueNo: Int,
        @SerializedName("spbu_name")
        @Expose
        var spbuName: String
)