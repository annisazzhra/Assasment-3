package org.d3if3042.e_commerce.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if3042.e_commerce.model.Product
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://fakestoreapi.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ProductApiService {
    @GET("products")
    suspend fun getProducts(): List<Product>
}

object ProductApi {
    val retrofitService: ProductApiService by lazy {
        retrofit.create(ProductApiService::class.java)
    }
}
