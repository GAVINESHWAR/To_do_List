package com.example.to_do_app

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.to_do_app.ui.theme.To_Do_AppTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            To_Do_AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun Greeting() {
    var todoList = mutableListOf<String>()
    var newTodoText by remember { mutableStateOf("") }
    val currentdateTime= LocalDateTime.now()
    val formattedDateTime = currentdateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "To-Do App") }
            )
        },
        content = {
            Column(modifier = Modifier
                .padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        modifier = Modifier.weight(1f),
                        value = newTodoText,
                        onValueChange = { newTodoText = it },
                        label = { Text("Enter a new task") }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {
                            if (newTodoText.isNotEmpty()) {
                                todoList.add(newTodoText)
                                newTodoText = ""
                            }
                        }
                    ) {
                        Text("Add")
                    }
                }
                Spacer(modifier= Modifier.height(20.dp) )
                Text(
                    text = "Todo List",
                    style = MaterialTheme.typography.h5,
                    color = Color.Magenta
                )
                Text(text="Date: ${formattedDateTime.substringBefore("T")}",color = Color.Blue)
                Spacer(modifier = Modifier.height(10.dp))
                LazyVerticalGrid(cells = GridCells.Fixed(1), modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .background(color = Color.Green)) {
                    items(todoList.size) {
                        Card(
                            onClick = {
                                /**/
                            },
                            modifier = Modifier
                                .padding(10.dp)
                                .background(Color.Yellow),
                            elevation = 15.dp
                        ) {
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(2.dp)
                                    .weight(1f)
                            ) {
                                Text("Task "+todoList[it],color = Color.Blue)
                                Spacer(modifier = Modifier.width(3.dp))
                                IconButton(onClick = { todoList.remove(todoList[it]) }
                               ) {
                                    Icon(Icons.Default.Clear, contentDescription = null)
                                }
                            }
                        }

                    }
                }
            }
        }
    )
}
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, showSystemUi = true
)
@Composable
fun DefaultPreview() {
    To_Do_AppTheme {
        Greeting()
    }
}