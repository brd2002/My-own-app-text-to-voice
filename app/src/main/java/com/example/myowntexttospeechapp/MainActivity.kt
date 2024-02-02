package com.example.myowntexttospeechapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.myowntexttospeechapp.databinding.ActivityMainBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        // this the list view of the item
        val listview = binding.ListView

        val apiBuilder = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyApiInterface::class.java)
        val apicall = apiBuilder.getRecipes()
//        val stringBuilder = StringBuilder()
        val imageAndName = ArrayList<Recipe>()
        apicall.enqueue(object : retrofit2.Callback<MyData> {
            override fun onResponse(call: retrofit2.Call<MyData>, response: retrofit2.Response<MyData>) {
                val responsebody = response.body()
                val productList = responsebody!!.recipes
                for (i in productList) {

//                    imageAndName.add(i.name)
//                    imageAndName.add(i.image)
                    imageAndName.add(i)
                }
                listview.isClickable = true
                listview.adapter = MyAdapter(this@MainActivity,imageAndName)
//                val adapterForMyListView = ArrayAdapter(this@MainActivity, android.R.layout.simple_gallery_item)
//                Glide
//                    .with(this@MainActivity)
//                    .load(firstProductImage)
//                    .centerCrop()
////                    .placeholder(R.drawable.loading_spinner)
////                    .into(binding.imageView);
            }
            override fun onFailure(call: retrofit2.Call<MyData>, t: Throwable) {
                Log.d("fail" , t.message.toString())
            }})

    }
}