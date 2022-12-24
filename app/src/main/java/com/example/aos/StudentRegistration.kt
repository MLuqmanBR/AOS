package com.example.aos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class StudentRegistration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            supportActionBar?.hide()
        }catch (_: NullPointerException){}

        setContentView(R.layout.activity_student_registration)

        val button_3 = findViewById<Button>(R.id.button3)
        button_3.setOnClickListener({
            val intent = Intent(this,Attendance::class.java)
            startActivity(intent)
        })

        val button_4 = findViewById<Button>(R.id.button4)
        button_4.setOnClickListener({
            val intent = Intent(this,AdminSignup::class.java)
            startActivity(intent)
        })
        }
}