package net

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface CoreApi {
    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST
    fun sendMsg(@Url url: String, @Body json: RequestBody?): Call<ResponseBody?>
}