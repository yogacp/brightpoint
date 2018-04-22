package app.brightpoint.com.vo.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



data class WashScheduleData(
        @SerializedName("queue_no")
        @Expose
        var queueNo: Int,
        @SerializedName("queue_date")
        @Expose
        var queueDate: String,
        @SerializedName("status")
        @Expose
        var status: String,
        @SerializedName("spbu_id")
        @Expose
        var spbuId: Int,
        @SerializedName("spbu_name")
        @Expose
        var spbuName: String,
        @SerializedName("current_queue")
        @Expose
        var currentQueue: Any
)