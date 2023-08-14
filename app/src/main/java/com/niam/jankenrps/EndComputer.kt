package com.niam.jankenrps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import pl.droidsonroids.gif.GifImageView

class EndComputer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_computer)

        val btnHome = findViewById<Button>(R.id.btnHome)

        val winner = intent.getStringExtra("winner")
        setView(winner.toString())
        btnHome.setOnClickListener {
            finish()
        }
    }

    private fun setView(winner:String)
    {
        val imageView: GifImageView = findViewById(R.id.statusDisplay)
        val tvStatus:TextView = findViewById(R.id.tv_Status)
        if (winner=="player")
        {
            imageView.setImageResource(R.drawable.win1_gif)
            tvStatus.text = "Congratulation\nYou Won!"
        }

        else
        {
            imageView.setImageResource(R.drawable.lose1_gif)
            tvStatus.text = "Better Luck Next Time\nYou Lose!"
        }
    }
}