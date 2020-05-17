package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.setTitle(this.getResources().getString(R.string.registar_page_name))
    }
}
