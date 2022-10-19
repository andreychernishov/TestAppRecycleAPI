package com.example.testapprecycleapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.io.Serializable as Serializable1


class MainActivity : AppCompatActivity(), NumberAdapter.Listener, Serializable1 {

    private var editLauncher: ActivityResultLauncher<Intent>? = null
    private val adapter = NumberAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

        mainRcView.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
        mainRcView.adapter = adapter


        searchBtn.setOnClickListener {
            testTv.text = getResult(edText.text.toString()).toString()

        }

    }
    override fun onClick(number: Number) {
        startActivity(Intent(this, Content::class.java).apply {
            putExtra("NUMBER",number)
        })
    }

    private fun getResult(number: String) {
        val url = "http://numbersapi.com/$number/math"
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(Request.Method.GET,
            url,
            { response ->
                adapter.addDesc(Number(response))
                Log.d("LOG", "responce $response")
            },
            {
                Log.d("LOG", "ERROR ")
            }
        )
        queue.add(stringRequest)
    }

    fun init(){
        searchBtn = findViewById(R.id.btn_search)
        randomNumber = findViewById(R.id.btn_random)
        edText = findViewById(R.id.ed_text)
        mainRcView = findViewById(R.id.main_rc_view)
        testTv = findViewById(R.id.test_tv)

    }

    lateinit var searchBtn: Button
    lateinit var randomNumber: Button
    lateinit var edText : EditText
    lateinit var mainRcView: RecyclerView
    lateinit var testTv: TextView

}




