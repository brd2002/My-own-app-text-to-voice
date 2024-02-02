package com.example.myowntexttospeechapp

import retrofit2.http.GET

interface MyApiInterface {
    @GET("recipes")
    fun getRecipes(): retrofit2.Call<MyData>
}