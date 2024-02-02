package com.example.myowntexttospeechapp

data class MyData(
    val limit: Int,
    val recipes: List<Recipe>,
    val skip: Int,
    val total: Int
)