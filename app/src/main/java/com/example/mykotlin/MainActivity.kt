package com.example.mykotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mykotlin.ui.theme.MyKotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyKotlinTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HangmanGame(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun HangmanGame(modifier: Modifier = Modifier) {
    // ✅ Game state
    var wordToGuess by remember { mutableStateOf("KOTLIN") }
    var guessedLetters by remember { mutableStateOf(setOf<Char>()) }
    var attemptsLeft by remember { mutableStateOf(6) }
    var inputLetter by remember { mutableStateOf("") }

    // ✅ Display word with underscores
    val displayWord = wordToGuess.map {
        if (guessedLetters.contains(it)) it else '_'
    }.joinToString(" ")

    // ✅ Check win/lose
    val hasWon = !displayWord.contains('_')
    val hasLost = attemptsLeft <= 0

    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "🎮 Hangman Game", style = MaterialTheme.typography.titleLarge)
        Text(text = "Word: $displayWord")
        Text(text = "Attempts left: $attemptsLeft")

        if (hasWon) {
            Text("✅ You won!", color = MaterialTheme.colorScheme.primary)
        } else if (hasLost) {
            Text("❌ You lost! The word was $wordToGuess", color = MaterialTheme.colorScheme.error)
        } else {
            OutlinedTextField(
                value = inputLetter,
                onValueChange = { if (it.length <= 1) inputLetter = it.uppercase() },
                label = { Text("Enter a letter") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = true
            )

            Button(onClick = {
                if (inputLetter.isNotEmpty()) {
                    val letter = inputLetter[0]
                    if (!guessedLetters.contains(letter)) {
                        guessedLetters = guessedLetters + letter
                        if (!wordToGuess.contains(letter)) {
                            attemptsLeft--
                        }
                    }
                    inputLetter = ""
                }
            }) {
                Text("Guess")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            // Reset game
            wordToGuess = "ANDROID" // You can randomize later
            guessedLetters = emptySet()
            attemptsLeft = 6
            inputLetter = ""
        }) {
            Text("Restart Game")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHangmanGame() {
    MyKotlinTheme {
        HangmanGame()
    }
}
