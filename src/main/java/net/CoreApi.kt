package net

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface CoreApi {
    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("/v1/LuaApiCaller?funcname=SendMsg&timeout=10")
    fun sendMsg(@Query("qq") qq: Long, @Body json: RequestBody?): Call<ResponseBody?>
}