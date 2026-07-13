package com.example.mykotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mykotlin.ui.theme.MyKotlinTheme

// ✅ Data class to represent a student
// Each student has a name and a module number
data class Student(val name: String, var module: Int)

class MainActivity : ComponentActivity() {
    // ✅ onCreate is the entry point of the activity
    // It sets up the Compose UI
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyKotlinTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    StudentInfoScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

// ✅ Composable function that builds the UI
// Displays student info, counter, and other Kotlin features
@Composable
fun StudentInfoScreen(modifier: Modifier = Modifier) {
    // Immutable variable (val)
    val greeting: String = "Welcome to Kotlin Module"

    // Mutable variable (var) with Compose state
    var counter by remember { mutableStateOf(0) }

    // ✅ Collection of students
    val students = remember {
        mutableListOf(
            Student("Oswell", 1),
            Student("Ama", 2),
            Student("Kwame", 3)
        )
    }

    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Display greeting
        Text(text = greeting, style = MaterialTheme.typography.titleLarge)

        // ✅ Expression using string interpolation
        Text(text = "Counter is ${counter + 1}")

        // Button to increase counter
        Button(onClick = { counter++ }) {
            Text("Increase Counter")
        }

        // ✅ Conditional statement
        Text(
            text = if (counter > 5) "Counter is large" else "Counter is small"
        )

        // ✅ Loop through students
        for (student in students) {
            Text(text = student.info())
        }

        // ✅ When keyword usage
        val moduleMessage = when (counter) {
            0 -> "No progress yet"
            in 1..3 -> "Getting started"
            else -> "Advanced progress"
        }
        Text(text = moduleMessage)

        // Call helper functions to demonstrate more features
        Text(text = progressMessage(counter))
        Text(text = moduleLevel(counter))
    }
}

// ✅ Extension function for Student class
// Returns formatted info about the student
fun Student.info(): String {
    return "$name is in Module $module"
}

// ✅ Function that returns a motivational message
// Uses conditionals to decide what to say
fun progressMessage(counter: Int): String {
    return if (counter < 3) "Keep going!" else "Great work!"
}

// ✅ Function that categorizes module number
// Demonstrates use of the when keyword
fun moduleLevel(module: Int): String {
    return when (module) {
        1 -> "Beginner"
        2 -> "Intermediate"
        3 -> "Advanced"
        else -> "Expert"
    }
}

// ✅ Function that prints numbers using a loop
// Demonstrates a simple for loop
fun printNumbers(): String {
    var result = ""
    for (i in 1..5) {
        result += "Number: $i\n"
    }
    return result
}

// ✅ Preview function for Android Studio
// Lets you see the UI without running the app
@Preview(showBackground = true)
@Composable
fun PreviewStudentInfo() {
    MyKotlinTheme {
        StudentInfoScreen()
    }
}
