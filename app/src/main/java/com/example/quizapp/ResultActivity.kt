package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.util.Log
import android.widget.Toast

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        Log.d("ResultActivity", "onCreate: ResultActivity started")

        val resultText: TextView = findViewById(R.id.result_text)
        val correctAnswersText: TextView = findViewById(R.id.correct_answers_text)
        val incorrectAnswersText: TextView = findViewById(R.id.incorrect_answers_text)
        val feedbackName: EditText = findViewById(R.id.feedback_name)
        val homeButton: Button = findViewById(R.id.home_button)

        val score = intent.getIntExtra("SCORE", 0)
        val correctAnswers = intent.getStringArrayListExtra("CORRECT_ANSWERS")
        val incorrectAnswers = intent.getStringArrayListExtra("INCORRECT_ANSWERS")

        resultText.text = "Your score: $score"

        correctAnswersText.text = "Correct Answers:\n" + correctAnswers?.joinToString("\n")
        incorrectAnswersText.text = "Incorrect Answers:\n" + incorrectAnswers?.joinToString("\n")

        homeButton.setOnClickListener {
            val name = feedbackName.text.toString()
            if (name.isNotBlank()) {
                Toast.makeText(this, "Thank you for your feedback, $name!", Toast.LENGTH_SHORT).show()
            }
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}


