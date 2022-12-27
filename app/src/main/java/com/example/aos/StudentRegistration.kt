package com.example.aos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class StudentRegistration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_registration)
        supportActionBar?.hide()

        val button3 = findViewById<Button>(R.id.button3)
        button3.setOnClickListener({
            val intent = Intent(this,Attendance::class.java)
            startActivity(intent)

        })

        val button4 = findViewById<Button>(R.id.button4)
        button4.setOnClickListener({
            val intent = Intent(this,AdminSignup::class.java)
            startActivity(intent)
            finish()
        })
        }
}