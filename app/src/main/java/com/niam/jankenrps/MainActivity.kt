package com.niam.jankenrps

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPlayWithComputer = findViewById<Button>(R.id.btnPlayWithComputer)
        btnPlayWithComputer.setOnClickListener {
            val intent = Intent(this, PlayWithComputer::class.java)
            startActivity(intent)
        }

        val tvInstruction: TextView = findViewById(R.id.tvInstruction)
        tvInstruction.setOnClickListener {
            showInstruction()
        }

    }
        private fun showInstruction()
        {
            val msgInstructions = Dialog(this)
            msgInstructions.setContentView(R.layout.instruction)
            msgInstructions.findViewById<Button>(R.id.btnOk).setOnClickListener {
                msgInstructions.cancel()
            }
            msgInstructions.show()
        }
}