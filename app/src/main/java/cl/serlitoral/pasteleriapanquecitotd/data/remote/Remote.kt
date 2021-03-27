package cl.serlitoral.pasteleriapanquecitotd.data.remote

import cl.serlitoral.pasteleriapanquecitotd.data.model.Cake
import cl.serlitoral.pasteleriapanquecitotd.data.model.CakeDetail
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

// https://my-json-server.typicode.com/Himuravidal/cakesApi/cakes
// https://my-json-server.typicode.com/Himuravidal/cakesApi/cakeDetails/1

interface CakeAPI {
    @GET("cakes")
    suspend fun getCakes(): Response<List<Cake>>

    @GET("cakeDetails/{pid}")
    suspend fun getCake(@Path("pid") id: String): Response<CakeDetail>
}

class RetrofitClient {
    companion object {
        private const val BASE_URL = "https://my-json-server.typicode.com/Himuravidal/cakesApi/"

        fun retrofitInstance(): CakeAPI {
            val retrofit = Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(CakeAPI::class.java)
        }
    }
}