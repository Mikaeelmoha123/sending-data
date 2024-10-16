package com.example.intents

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var editText: EditText
    private lateinit var repliedMessageView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        editText = findViewById(R.id.editTextMain)
        val sendButton: Button = findViewById(R.id.main_send_button)
        repliedMessageView = findViewById(R.id.repliedMessage)


        sendButton.setOnClickListener {
            val message = editText.text.toString()


            val messageUri = Uri.parse("message:$message")
            val intent = Intent(this, SecondActivity::class.java).apply {
                data = messageUri
            }
            startActivity(intent)
        }
    }


    override fun onResume() {
        super.onResume()
        intent?.let {
            if (it.hasExtra("reply")) {
                val reply = it.getStringExtra("reply")
                repliedMessageView.text = "Reply: $reply"
            }
        }
    }
}