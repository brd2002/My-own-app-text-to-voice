package com.example.myowntexttospeechapp

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.TextView
import com.bumptech.glide.Glide

class MyAdapter (val context : Activity , val arrayList: ArrayList<Recipe>) : ArrayAdapter<Recipe>(context, R.layout.each_item, arrayList) {
    @SuppressLint("MissingInflatedId")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.each_item, null)
        val image = view.findViewById<ImageView>(R.id.ImageOfProduct)
        val name = view.findViewById<TextView>(R.id.NameOfProduct).also {
            it.setText(arrayList[position].name)
        }
       Glide
            .with(context)
            .load(arrayList[position].image)
            .centerCrop()
//            .placeholder(R.drawable.loading_spinner)
            .into(image);
        return super.getView(position, convertView, parent)
    }
}