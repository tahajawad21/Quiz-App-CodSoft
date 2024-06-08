package com.example.quizapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import android.util.Log

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("MainActivity", "onCreate: MainActivity started")

        val englishQuizButton: Button = findViewById(R.id.english_quiz)
        val mathQuizButton: Button = findViewById(R.id.math_quiz)
        val generalQuizButton: Button = findViewById(R.id.general_quiz)
        val randomQuizButton: Button = findViewById(R.id.random_quiz)

        englishQuizButton.setOnClickListener {
            Log.d("MainActivity", "English Quiz button clicked")
            startQuiz("English")
        }

        mathQuizButton.setOnClickListener {
            Log.d("MainActivity", "Math Quiz button clicked")
            startQuiz("Mathematics")
        }

        generalQuizButton.setOnClickListener {
            Log.d("MainActivity", "General Quiz button clicked")
            startQuiz("General")
        }

        randomQuizButton.setOnClickListener {
            Log.d("MainActivity", "Random Quiz button clicked")
            startQuiz("Random")
        }
    }

    private fun startQuiz(category: String) {
        val intent = Intent(this, QuizActivity::class.java)
        intent.putExtra("CATEGORY", category)
        startActivity(intent)
    }
}
