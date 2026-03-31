package com.esihle.mytest

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.time.LocalTime

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val enterButton = findViewById<Button>(R.id.button)
        val results = findViewById<TextView>(R.id.textView2)
        val timeDay = findViewById<EditText>(R.id.editTextText)
        val resetButton = findViewById<Button>(R.id.button2)

        fun isValidTime(input: String): Boolean {
            val timeRegex = Regex("^([01][0-9]|2[0-3]):[0-5][0-9]$")
            return timeRegex.matches(input)
        }

        fun getSparks(input: String): String {
            val morning = LocalTime.parse("08:59")
            val midMorning = LocalTime.parse("11:59")
            val afternoon = LocalTime.parse("14:59")
            val afternoonSnack = LocalTime.parse("16:59")
            val dinner = LocalTime.parse("19:59")
            val afterDinner = LocalTime.parse("04:59")

            val userTime = LocalTime.parse(input)

            return if (userTime.isBefore(morning)) {
                "Send a \"Good morning\" text to a family member"
            } else if (userTime.isAfter(morning) && userTime.isBefore(midMorning)) {
                "Reach out to a colleague with a quick \"Thank you\" "
            } else if (userTime.isAfter(midMorning) && userTime.isBefore(afternoon)) {
                "Share a funny meme or interesting link with a friend "
            } else if (userTime.isAfter(afternoon) && userTime.isBefore(afternoonSnack)) {
                " Send a quick \" thinking of you\" "
            } else if (userTime.isAfter(afternoonSnack) && userTime.isBefore(dinner)) {
                "Call a friend or relative for a 5-minute catch up "
            } else {
                " Leave a thoughtful comment on a friend's post"
            }
        }
            enterButton.setOnClickListener {
                if (!isValidTime(timeDay.text.toString())) {
                    timeDay.error = "enter your correct day time in correct format(HH:mm)"
                }

                results.setText(getSparks(timeDay.text.toString()))

            }

            resetButton.setOnClickListener {
                results.setText("")
                timeDay.setText("")
            }
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

        }
    }