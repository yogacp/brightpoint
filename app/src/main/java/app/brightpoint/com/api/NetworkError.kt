package app.brightpoint.com.api

import app.brightpoint.com.api.responses.error.ErrorResponse
import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException

class NetworkError(var err: Throwable): Throwable() {
    var DEFAULT_ERROR_MESSAGE = "Something went wrong! Please try again."
    val NETWORK_ERROR_MESSAGE = "No Internet Connection!"
    private val ERROR_MESSAGE_HEADER = "Error-Message"
    lateinit var mErrorResponse : ErrorResponse
    private var mListErrorResponse = ArrayList<String>()

    fun getErrorMessage(): String {
        if (err is IOException){
            return NETWORK_ERROR_MESSAGE
        }
        if (err !is HttpException){
            return DEFAULT_ERROR_MESSAGE
        }
        if(getHttpErrorCode()!! == 404 || getHttpErrorCode()!! >= 500){
            DEFAULT_ERROR_MESSAGE =  "Server sedang bermasalah"
        }else{
            val response = (err as HttpException).response()
            if (response != null) {
                DEFAULT_ERROR_MESSAGE = "Terjadi kesalahan, silahkan coba lagi"
            }
        }


        return DEFAULT_ERROR_MESSAGE
    }

    /**
     * Get Http Error Code
     * @Return 200,300,400,etc
     * */

    fun getHttpErrorCode(): Int?{
        return (err as HttpException).code()
    }


    /**
     * Get Error Status
     * */
    fun getJsonErrorStatus(response: retrofit2.Response<*>) : String? {
        val jsonString = response.errorBody()!!.string()
        val errorResponse = Gson().fromJson(jsonString, ErrorResponse::class.java)
        return errorResponse.resultMessage
    }


    /**
     * Get Json Error Messages
     *  @Return Beningmart Error Code
     * */
    private fun getJsonStringFromResponse(response: retrofit2.Response<*>): String? {
        val jsonString = response.errorBody()!!.string()
        val errorResponse = Gson().fromJson(jsonString, ErrorResponse::class.java)
        return errorResponse.resultMessage
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false

        val that = other as NetworkError?

        return err.equals(that!!.err)
    }

    override fun hashCode(): Int {
        return err.hashCode()
    }
}