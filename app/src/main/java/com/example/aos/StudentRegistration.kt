package com.example.aos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

class StudentRegistration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_registration)

        // access the items of the list
        val languages = resources.getStringArray(R.array.BlockNames)

        // access the spinner
        val spinner = findViewById<Spinner>(R.id.spinner_BLock)
        if (spinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, languages
            )
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    Toast.makeText(
                        this@StudentRegistration,
                        getString(R.string.selected_item) + " " +
                                "" + languages[position], Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }
}