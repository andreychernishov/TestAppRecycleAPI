package com.example.testapprecycleapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.io.Serializable as Serializable1


class MainActivity : AppCompatActivity(), NumberAdapter.Listener, Serializable1 {
    lateinit var searchBtn: Button
    lateinit var randomNumber: Button
    lateinit var edText : EditText
    lateinit var mainRcView: RecyclerView
    lateinit var testTv: TextView

    private val adapter = NumberAdapter(this)
    private val urlRandom: String = "http://numbersapi.com/random/math"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

        searchBtn.setOnClickListener {
             getResult("http://numbersapi.com/${edText.text}/math")

        }
        randomNumber.setOnClickListener {
             getResult(urlRandom)

        }
    }

    private fun getResult(url: String) {
        val url = url
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(Request.Method.GET,
            url,
            { response ->
                adapter.addDesc(Number(response))
                Log.d(CONSTANCE.LOG_TAG, "responce $response")
            },
            {
                Log.d(CONSTANCE.LOG_TAG, "ERROR ")
            }
        )
        queue.add(stringRequest)
    }

   private fun init(){
        searchBtn = findViewById(R.id.btn_search)
        randomNumber = findViewById(R.id.btn_random)
        edText = findViewById(R.id.ed_text)
        mainRcView = findViewById(R.id.main_rc_view)
        testTv = findViewById(R.id.test_tv)
        mainRcView.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
        mainRcView.adapter = adapter

    }
    override fun onClick(number: Number) {
        startActivity(Intent(this, Content::class.java).apply {
            putExtra(CONSTANCE.NUMBER_KEY,number)
        })
    }



}




