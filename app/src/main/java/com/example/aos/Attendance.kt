package com.example.aos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Attendance : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

            supportActionBar?.hide()
        setContentView(R.layout.activity_attendance)

    }
}