package com.example.myapplication123

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication123.ui.theme.MyApplicationTheme
import com.google.firebase.database.*

class MainActivity : ComponentActivity() {

    // Deklaracja referencji do bazy danych Firebase
    private lateinit var database: DatabaseReference

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicjalizacja Firebase
        database = FirebaseDatabase.getInstance().reference

        // Ustawienie widoku Compose jako zawartości aktywności
        setContent {
            MyApplicationTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    content = { // Dodano prawidłowe przekazanie content
                        FirebaseMessageUI(database)
                    }
                )
            }
        }
    }
}

@Composable
fun FirebaseMessageUI(database: DatabaseReference) {
    // Przechowywanie stanu wiadomości
    var messages by remember { mutableStateOf(listOf<String>()) }

    // Pobierz dane z węzła "messages" w Firebase
    LaunchedEffect(Unit) {
        database.child("messages").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Odczytaj wszystkie wiadomości jako listę
                val messageList = mutableListOf<String>()
                for (childSnapshot in snapshot.children) {
                    val message = childSnapshot.getValue(String::class.java)
                    if (message != null) {
                        messageList.add(message)
                    }
                }
                messages = messageList
            }

            override fun onCancelled(error: DatabaseError) {
                // Obsługa błędu podczas odczytu
                messages = listOf("Failed to fetch messages: ${error.message}")
            }
        })
    }

    // Wyświetl pobrane wiadomości w UI
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Wyświetlanie każdej wiadomości z listy
            messages.forEach { message ->
                Text(text = message, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FirebaseMessagePreview() {
    MyApplicationTheme {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Hello World", style = MaterialTheme.typography.bodyLarge)
        }
    }
}
