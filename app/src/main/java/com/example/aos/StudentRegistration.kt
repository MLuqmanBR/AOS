package com.example.aos

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.nfc.*
import android.nfc.tech.Ndef
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import java.io.IOException


class StudentRegistration : AppCompatActivity() {


    val Error_Detected = "No NFC Tag Detected!"
    val Write_Success = "Text Written Successfully!"
    val Write_Error = "Error during Writing, Try again!"

    var nfcAdapter: NfcAdapter? = null
    var pendingintent: PendingIntent? = null
    var writingtagfilters: IntentFilter? = null

    var writeMode: Boolean? = null
    var myTag: Tag? = null
    var context: Context? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_registration)
        supportActionBar?.hide()

        val textinputlayout = findViewById<TextInputLayout>(R.id.textInputLayout)
        val tinLay = textinputlayout.editText!!.toString()
        val rollno = findViewById<TextInputLayout>(R.id.roll_no)
        val rn = textinputlayout.editText!!.toString()
        val phoneNo = findViewById<TextInputLayout>(R.id.phoneNo)
        val pn = textinputlayout.editText!!.toString()
        val branch = findViewById<TextInputLayout>(R.id.Branch)
        val br = textinputlayout.editText!!.toString()
        val blockname = findViewById<TextInputLayout>(R.id.blockname)
        val bn = textinputlayout.editText!!.toString()
        context = this


        val button3 = findViewById<Button>(R.id.button3)
        button3.setOnClickListener {

            try {
                if (myTag == null) {
                    Toast.makeText(context, Error_Detected, Toast.LENGTH_LONG).show()
                } else {
                    write("Plain Text |"+ tinLay, myTag!!)
                    Toast.makeText(context, Write_Success, Toast.LENGTH_LONG).show()
                }
            } catch (e: IOException) {
                Toast.makeText(context, Write_Error, Toast.LENGTH_LONG).show()
                e.printStackTrace()
            } catch (e: FormatException) {
                Toast.makeText(context, Write_Error, Toast.LENGTH_LONG).show()
                e.printStackTrace()
            }
        }


        val nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        if (nfcAdapter == null) {
            Toast.makeText(this, "This device does not support NFC", Toast.LENGTH_SHORT).show()
            finish()
        }

        pendingintent = PendingIntent.getActivity(this, 0, Intent(this, javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), PendingIntent.FLAG_MUTABLE)
        val tagDetected = IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED)
        tagDetected.addCategory(Intent.CATEGORY_DEFAULT)
        writingtagfilters = IntentFilter(tagDetected)






        val button4 = findViewById<Button>(R.id.button4)
        button4.setOnClickListener {
            val intent = Intent(this, AdminSignup::class.java)
            startActivity(intent)
            finish()
        }
    }





    private fun write(text: String, tag: Tag) {
        val records = arrayOf(createRecord(text))
        val message = NdefMessage(records)
        // Get an instance of Ndef for the tag.
        val ndef = Ndef.get(tag)
        // Enable I/O
        ndef.connect()
        // Write the message
        ndef.writeNdefMessage(message)
        // Close the connection
        ndef.close()
    }

    private fun createRecord(text: String): NdefRecord {
        val lang = "en"
        val textBytes = text.toByteArray()
        val langBytes = lang.toByteArray(charset("US-ASCII"))
        val langLength = langBytes.size
        val textLength = textBytes.size
        val payload = ByteArray(1 + langLength + textLength)

// Set status byte (see NDEF spec for actual bits)
        payload[0] = langLength.toByte()

// Copy langbytes and textbytes into payload
        System.arraycopy(langBytes, 0, payload, 1, langLength)
        System.arraycopy(textBytes, 0, payload, 1 + langLength, textLength)

        val recordNFC = NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_TEXT, byteArrayOf(), payload)

        return recordNFC
    }


    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)

        if (NfcAdapter.ACTION_TAG_DISCOVERED == intent.action) {
            @Suppress("DEPRECATION")
            myTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG)
        }
    }

    override fun onPause() {
        super.onPause()
        WriteModeOff()
    }

    override fun onResume() {
        super.onResume()
        WriteModeOn()
    }

    private fun WriteModeOn() {
        writeMode = true
        nfcAdapter!!.enableForegroundDispatch(this, pendingintent, arrayOf(writingtagfilters), null)
    }

    private fun WriteModeOff() {
        writeMode = false
        nfcAdapter!!.disableForegroundDispatch(this)
    }


}

