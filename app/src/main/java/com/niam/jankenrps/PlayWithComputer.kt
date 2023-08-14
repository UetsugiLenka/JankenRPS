package com.niam.jankenrps

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.niam.jankenrps.databinding.ActivityPlayWithComputerBinding

class PlayWithComputer : AppCompatActivity() {

    private var binding: ActivityPlayWithComputerBinding? = null

    private var animation1: AnimationDrawable? = null
    private var animation2: AnimationDrawable? = null
    private var setTime: CountDownTimer? = null

    private var allowPlaying = true

    private lateinit var selectP1: String
    private lateinit var selectP2: String

    private var scoreP1 = 0
    private var scoreP2 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayWithComputerBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.btnP2rock?.setOnClickListener {
            onPlay("rock")
        }
        binding?.btnP2paper?.setOnClickListener {
            onPlay("paper")
        }
        binding?.btnP2scissor?.setOnClickListener {
            onPlay("scissor")
        }
    }

    private fun playAnimation() {
        binding?.ivIconP1?.setImageResource(0)
        binding?.ivIconP2?.setImageResource(0)
        binding?.tvP1Status?.text = ""
        binding?.tvP2Status?.text = ""

        binding?.ivIconP1?.setBackgroundResource(R.drawable.animation_janken)
        animation1 = binding?.ivIconP1?.background as AnimationDrawable

        binding?.ivIconP2?.setBackgroundResource(R.drawable.animation_janken)
        animation2 = binding?.ivIconP2?.background as AnimationDrawable

        setTime = object : CountDownTimer(3000, 1000) {
            override fun onTick(p0: Long) {
                animation1?.start()
                animation2?.start()
            }

            override fun onFinish() {
                animation1?.stop()
                animation2?.stop()
                allowPlaying = true

                binding?.ivIconP1?.setBackgroundResource(0)
                binding?.ivIconP2?.setBackgroundResource(0)
                setSelectedIcon()
                setScore()
                endGame()
            }

        }.start()
    }

    private fun endGame()
    {
        if(scoreP1==3 || scoreP2==3)
        {
            var winner = if (scoreP1==3)
                "Comp"
            else
               "player"
            val intent = Intent(this, EndComputer::class.java)
            intent.putExtra("winner",winner)
            startActivity(intent)
            finish()
        }
    }

    private fun getResult(): String {
        return if (selectP1 == selectP2)
            "tie"
        else if (selectP1 == "rock" && selectP2 == "scissor" ||
            selectP1 == "paper" && selectP2 == "rock" ||
            selectP1 == "scissor" && selectP2 == "paper"
        )
            "P1"
        else
            "P2"
    }

    private fun setScore() {
        if (getResult() == "tie") {
            binding?.tvP1Status?.text = "Tie"
            binding?.tvP2Status?.text = "Tie"
        } else if (getResult() == "P1") {
            binding?.tvP1Status?.text = "win"
            binding?.tvP2Status?.text = "Lose"
            scoreP1++
            when (scoreP1) {
                1 -> binding?.ivP1Star1?.setImageResource(R.drawable.star)
                2 -> binding?.ivP1Star2?.setImageResource(R.drawable.star)
                3 -> binding?.ivP1Star3?.setImageResource(R.drawable.star)
            }
        } else {
            binding?.tvP1Status?.text = "Lose"
            binding?.tvP2Status?.text = "Win"
            scoreP2++
            when (scoreP2) {
                1 -> binding?.ivP2Star1?.setImageResource(R.drawable.star)
                2 -> binding?.ivP2Star2?.setImageResource(R.drawable.star)
                3 -> binding?.ivP2Star3?.setImageResource(R.drawable.star)
            }
        }
    }

    private fun setSelectedIcon() {
        when (selectP1) {
            "rock" -> binding?.ivIconP1?.setImageResource(R.drawable.rock2)
            "paper" -> binding?.ivIconP1?.setImageResource(R.drawable.paper2)
            "scissor" -> binding?.ivIconP1?.setImageResource(R.drawable.scissor2)
        }
        when (selectP2) {
            "rock" -> binding?.ivIconP2?.setImageResource(R.drawable.rock2)
            "paper" -> binding?.ivIconP2?.setImageResource(R.drawable.paper2)
            "scissor" -> binding?.ivIconP2?.setImageResource(R.drawable.scissor2)
        }
    }

    private fun onPlay(selection: String)
    {
        if (allowPlaying)
        {
            selectP1 = listOf("rock", "paper", "scissor").random()
            selectP2 = selection
            allowPlaying = false
            playAnimation()
        }
    }

    override fun onBackPressed() {
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        setTime = null
    }

}