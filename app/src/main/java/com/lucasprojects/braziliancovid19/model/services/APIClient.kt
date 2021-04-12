package com.lucasprojects.braziliancovid19.model.services

import com.lucasprojects.braziliancovid19.model.domain.response.Response
import com.lucasprojects.braziliancovid19.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object APIClient {

    private var servicesAPIInterface: ServicesAPIInterface? = null

    fun build(): ServicesAPIInterface {
        val builder = Retrofit.Builder()
            .baseUrl(Constants.APICOVID.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(getInterceptor())

        val retrofit = builder.client(httpClient.build()).build()
        servicesAPIInterface = retrofit.create(ServicesAPIInterface::class.java)
        return servicesAPIInterface as ServicesAPIInterface
    }

    private fun getInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    interface ServicesAPIInterface {
        @GET(Constants.APICOVID.GET_ALL)
        fun getAllData(): Call<List<Response>?>

        @GET(Constants.APICOVID.GET_CITY)
        fun getAllCity(@Query("page_size") size: Int): Call<List<Response>?>
    }
}