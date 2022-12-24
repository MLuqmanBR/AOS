package com.example.aos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent


class AdminSignup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try
        {
            supportActionBar?.hide()
        }catch (_:NullPointerException){}

        setContentView(R.layout.activity_admin_signup)

        val button_1 = findViewById<Button>(R.id.button1)
        button_1.setOnClickListener({
            val intent = Intent(this,Attendance::class.java)
            startActivity(intent)
        })

        val button_2 = findViewById<Button>(R.id.button2)
        button_2.setOnClickListener({
            val intent = Intent(this,StudentRegistration::class.java)
            startActivity(intent)
        })
    }
}