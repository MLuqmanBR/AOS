package com.example.aos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Attendance : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            supportActionBar?.hide()
        }catch (_: NullPointerException){}
        setContentView(R.layout.activity_attendance)

    }
}