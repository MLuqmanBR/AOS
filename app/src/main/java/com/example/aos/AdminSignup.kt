package com.example.aos

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent


class AdminSignup : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_admin_signup)

        val button1 = findViewById<Button>(R.id.button1)
        button1.setOnClickListener({
            val intent = Intent(this,Attendance::class.java)
            startActivity(intent)
            finish()
        })

        val buttonnew = findViewById<Button>(R.id.buttonew)
        buttonnew.setOnClickListener({
            val intent = Intent(this,StudentRegistration::class.java)
            startActivity(intent)
            finish()
        })
    }
}