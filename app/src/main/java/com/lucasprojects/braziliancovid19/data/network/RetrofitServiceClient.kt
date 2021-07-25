package com.lucasprojects.braziliancovid19.data.network

import com.lucasprojects.braziliancovid19.data.network.models.ResponseData
import com.lucasprojects.braziliancovid19.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RetrofitServiceClient {

    @Headers("Authorization: Token ${Constants.APICOVID.API_KEY}")
    @GET(Constants.APICOVID.GET_ALL)
    fun getAllData(): Call<ResponseData>

    @Headers("Authorization: Token ${Constants.APICOVID.API_KEY}")
    @GET(Constants.APICOVID.GET_CITY)
    fun getAllCity(@Query("page_size") size: Int = 5000): Call<ResponseData>

    companion object {
        @Volatile
        private var INSTANCE: RetrofitServiceClient? = null

        fun getInstance(): RetrofitServiceClient {
            return INSTANCE ?: synchronized(this) {
                val httpClient = OkHttpClient.Builder()
                httpClient.addInterceptor(buildInterceptor())
                val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.APICOVID.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build()).build()
                    .create(RetrofitServiceClient::class.java)
                INSTANCE = retrofit
                retrofit
            }
        }

        private fun buildInterceptor(): HttpLoggingInterceptor {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            return httpLoggingInterceptor
        }
    }
}