package com.example.myowntexttospeechapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.myowntexttospeechapp.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        // this the list view of the item
//        val listview = binding.ListView


//        val stringBuilder = StringBuilder()
        var allRecipes = ArrayList<Recipe>()
        binding.getImage.setOnClickListener {
            val randNo = Random.nextInt(1 , 20)
            val apiBuilder = Retrofit.Builder()
                .baseUrl("https://dummyjson.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApiInterface::class.java)
            val apicall = apiBuilder.getRecipes()
            apicall.enqueue(object : retrofit2.Callback<MyData> {
                override fun onResponse(call: retrofit2.Call<MyData>, response: retrofit2.Response<MyData>) {
                    val responsebody = response.body()
                    val productList = responsebody!!.recipes
                    Picasso.get().load(productList[randNo].image).into(binding.ImageView);
                }
                override fun onFailure(call: retrofit2.Call<MyData>, t: Throwable) {
                    Log.d("fail" , t.message.toString())
                }})
        }

//        listview.isClickable = true
//        listview.adapter = MyAdapter(this@MainActivity,allRecipes)
    }
//    fun callApi (randNo : Int): String{
//
//        return randomLink
//    }
}

