package com.example.testapprecycleapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Content : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)

        val item = intent.getSerializableExtra(CONSTANCE.NUMBER_KEY) as Number

        val contentTv: TextView = findViewById(R.id.content_Tv)
        contentTv.text = item.description

    }
}