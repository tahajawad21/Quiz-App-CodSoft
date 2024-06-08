package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import android.util.Log

class QuizActivity : AppCompatActivity() {

    private lateinit var questionText: TextView
    private lateinit var optionsGroup: RadioGroup
    private lateinit var submitButton: Button

    private var currentQuestionIndex = 0
    private var score = 0
    private val correctAnswers = arrayListOf<String>()
    private val incorrectAnswers = arrayListOf<String>()
    private lateinit var questions: List<Question>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        Log.d("QuizActivity", "onCreate: QuizActivity started")

        questionText = findViewById(R.id.question_text)
        optionsGroup = findViewById(R.id.options_group)
        submitButton = findViewById(R.id.submit_button)

        val category = intent.getStringExtra("CATEGORY")
        if (category != null) {
            questions = QuizData.getQuestions(category)
        } else {
            questions = listOf()
        }

        loadQuestion()

        submitButton.setOnClickListener {
            val selectedOptionId = optionsGroup.checkedRadioButtonId
            if (selectedOptionId != -1) {
                val selectedOption = findViewById<RadioButton>(selectedOptionId)
                checkAnswer(selectedOption.text.toString())
                loadNextQuestion()
            } else {
                Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadQuestion() {
        if (currentQuestionIndex < questions.size) {
            val question = questions[currentQuestionIndex]
            questionText.text = question.question
            optionsGroup.removeAllViews()
            question.options.forEachIndexed { index, option ->
                val radioButton = RadioButton(this)
                radioButton.text = option
                radioButton.id = index
                optionsGroup.addView(radioButton)
            }
            Log.d("QuizActivity", "loadQuestion: Loaded question ${currentQuestionIndex + 1}")
        } else {
            Log.e("QuizActivity", "No questions available")
        }
    }

    private fun checkAnswer(selectedOption: String) {
        val question = questions[currentQuestionIndex]
        val correctAnswer = question.options[question.correctAnswerIndex]
        if (selectedOption == correctAnswer) {
            score++
            correctAnswers.add("Q: ${question.question}\nA: $correctAnswer")
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
            Log.d("QuizActivity", "checkAnswer: Correct answer selected")
        } else {
            incorrectAnswers.add("Q: ${question.question}\nYour answer: $selectedOption\nCorrect answer: $correctAnswer")
            Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show()
            Log.d("QuizActivity", "checkAnswer: Incorrect answer selected")
        }
    }

    private fun loadNextQuestion() {
        currentQuestionIndex++
        if (currentQuestionIndex < questions.size) {
            loadQuestion()
        } else {
            showResult()
        }
    }

    private fun showResult() {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("SCORE", score)
        intent.putStringArrayListExtra("CORRECT_ANSWERS", correctAnswers)
        intent.putStringArrayListExtra("INCORRECT_ANSWERS", incorrectAnswers)
        startActivity(intent)
        finish()
    }
}
