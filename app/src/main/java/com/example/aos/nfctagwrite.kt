package com.example.aos

import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.nfc.*
import android.nfc.tech.Ndef
import android.nfc.tech.NdefFormatable
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import java.io.IOException

class nfctagwrite : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var blockNameEditText: EditText
    private lateinit var roomNoEditText: EditText
    private lateinit var phoneNoEditText: EditText
    private lateinit var rollNoEditText: EditText
    private lateinit var BranchEditText: EditText
    private lateinit var nfcAdapter: NfcAdapter

    var Buttton3: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_registration)

        // Get references to the EditTexts in the layout
        nameEditText = findViewById(R.id.textInputLayout)
        blockNameEditText = findViewById(R.id.blockname)
        phoneNoEditText = findViewById(R.id.PhoneNo)
        rollNoEditText = findViewById(R.id.roll_no)
        BranchEditText = findViewById(R.id.Branch)



        val button3 = findViewById<Button>(R.id.button3)
        button3.setOnClickListener {
            // Write the NDEF message to the NFC tag

            val ndefMessage = null
            val tag = null

            writeNdefMessageToTag(ndefMessage, tag)

        }
        // Set up the NFC adapter
        nfcAdapter = NfcAdapter.getDefaultAdapter(this)

        // Set up a pending intent to handle NFC tag scanning
        val pendingIntent = PendingIntent.getActivity(
            this, 0,
            Intent(this, javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0
        )

        // Set up an intent filter to handle NFC tag scanning
        val filters = arrayOf(IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED))
        val techList = arrayOf(arrayOf(Ndef::class.java.name), arrayOf(NdefFormatable::class.java.name))

        nfcAdapter.enableForegroundDispatch(
            this, pendingIntent, filters, techList
        )
    }


    fun writeNdefMessageToTag(ndefMessage: Nothing?, tag: Tag?) {
        try {
            // Get an NDEF object from the NFC tag
            val ndef = Ndef.get(tag)

            // Connect to the NFC tag
            ndef.connect()

            // Write the NDEF message to the NFC tag
            ndef.writeNdefMessage(ndefMessage)

            // Close the connection to the NFC tag
            ndef.close()

            // Show a toast message to confirm that the NDEF message was written to the NFC tag
            Toast.makeText(this, "NDEF message written to NFC tag.", Toast.LENGTH_SHORT)
                .show()
        } catch (e: IOException) {
            // Show a toast message if there was an error writing the NDEF message to the NFC tag
            Toast.makeText(
                this,
                "Error writing NDEF message to NFC tag.",
                Toast.LENGTH_SHORT
            ).show()
        } catch (e: FormatException) {
            // Show a toast message if there was an error formatting the NDEF message
            Toast.makeText(this, "Error formatting NDEF message.", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        // Check if the intent is for an NFC tag
        if (intent != null && NfcAdapter.ACTION_TAG_DISCOVERED == intent.action) {
            // Get the NFC tag from the intent
            val tag = intent.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)

            // Get the input from the EditText views
            val name = nameEditText.text.toString()
            val blockName = blockNameEditText.text.toString()
            val roomNo = roomNoEditText.text.toString()
            val phoneNo = phoneNoEditText.text.toString()
            val rollNo = rollNoEditText.text.toString()
            val Branch = BranchEditText.text.toString()

            // Create an NDEF message with the input from the EditText views
            val ndefMessage = NdefMessage(
                arrayOf(
                    NdefRecord.createTextRecord(null, name),
                    NdefRecord.createTextRecord(null, blockName),
                    NdefRecord.createTextRecord(null, roomNo),
                    NdefRecord.createTextRecord(null, phoneNo),
                    NdefRecord.createTextRecord(null, rollNo),
                    NdefRecord.createTextRecord(null, Branch)
                )
            )


        }
    }



}