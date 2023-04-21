package com.jephter.weatherapp.Model



import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("forecast.json")
    suspend fun getWeatherForecast(
        @Query("key") apiKey: String,
        @Query("q") location: String,
        @Query("days") days: Int
    ): Response<Weather>

    fun searchLocations(@Query("q") query: String): Call<List<Location>>
}
