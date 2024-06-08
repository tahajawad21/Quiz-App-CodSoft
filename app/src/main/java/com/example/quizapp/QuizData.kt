package com.example.quizapp

data class Question(
    val question: String,
    val options: List<String>,
    val correctAnswerIndex: Int
)

object QuizData {
    val englishQuestions = listOf(
        Question("What is the synonym of 'quick'?", listOf("Fast", "Slow", "Happy", "Sad"), 0),
        Question("What is the antonym of 'beautiful'?", listOf("Pretty", "Ugly", "Happy", "Sad"), 1),
        Question("Complete the sentence: He ____ to the store.", listOf("go", "goes", "gone", "going"), 1),
        Question("Which word is a noun?", listOf("Run", "Blue", "Happiness", "Quickly"), 2),
        Question("Choose the correct word: She is _____ than her brother.", listOf("tall", "taller", "tallest", "more tall"), 1),
        Question("Which is a verb?", listOf("Book", "Run", "Happy", "Bright"), 1)
    )

    val mathQuestions = listOf(
        Question("What is 2 + 2?", listOf("3", "4", "5", "6"), 1),
        Question("What is 5 * 3?", listOf("8", "15", "10", "20"), 1),
        Question("Solve: 10 / 2", listOf("2", "5", "10", "20"), 1),
        Question("What is 12 - 4?", listOf("6", "7", "8", "9"), 2),
        Question("Solve: 7 + 6", listOf("11", "12", "13", "14"), 2),
        Question("What is 9 * 9?", listOf("72", "81", "90", "99"), 1)
    )

    val generalQuestions = listOf(
        Question("What is the capital of France?", listOf("Paris", "London", "Berlin", "Madrid"), 0),
        Question("What is the largest planet in our solar system?", listOf("Earth", "Mars", "Jupiter", "Saturn"), 2),
        Question("Who wrote 'Romeo and Juliet'?", listOf("Mark Twain", "Jane Austen", "William Shakespeare", "Charles Dickens"), 2),
        Question("What is the longest river in the world?", listOf("Amazon", "Nile", "Yangtze", "Mississippi"), 1),
        Question("What is the smallest prime number?", listOf("0", "1", "2", "3"), 2),
        Question("Who painted the Mona Lisa?", listOf("Vincent van Gogh", "Pablo Picasso", "Leonardo da Vinci", "Claude Monet"), 2)
    )

    fun getQuestions(category: String): List<Question> {
        return when (category) {
            "English" -> englishQuestions
            "Mathematics" -> mathQuestions
            "General" -> generalQuestions
            "Random" -> getRandomQuestions()
            else -> listOf()
        }
    }

    private fun getRandomQuestions(): List<Question> {
        val allQuestions = englishQuestions + mathQuestions + generalQuestions
        return allQuestions.shuffled().take(6) // Adjust the number as needed
    }
}
