package com.areeb.mainactivity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.areeb.mainactivity.ui.theme.MainActivityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainActivityTheme {
                // A surface container using the 'background' color from the theme
                init()
            }
        }
    }

    @Preview
    @Composable
    private fun init() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
        ) {
            header()
            Spacer(modifier = Modifier.height(10.dp))
            takeNotes()
        }
    }

    @Composable
    private fun header() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    16.dp,
                )
                .background(Color.White),
        ) {
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Compose Basic App",
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Cursive,
                    textDecoration = TextDecoration.Underline,
                )
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    painter = painterResource(id = R.drawable.baseline_compost_24),
                    contentDescription = null,
                    Modifier
                        .size(26.dp)
                        .clickable {
                            Toast
                                .makeText(this@MainActivity, "this is icon", Toast.LENGTH_SHORT)
                                .show()
                        },
                    tint = Color.Red,

                )
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun takeNotes() {
        var name by remember {
            mutableStateOf("")
        }
        val nameList = remember { mutableStateListOf<String>() }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            OutlinedTextField(value = name, onValueChange = { textChanged ->
                name = textChanged
            })

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                modifier = Modifier
                    .width(120.dp)
                    .height(60.dp),
                onClick = {
                    if (name.isNotBlank()) {
                        nameList.add(name)
                        name = ""
                    } else {
                        Toast.makeText(this@MainActivity, "please add name", Toast.LENGTH_SHORT)
                            .show()
                    }
                },
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,

                ) {
                    Text(text = "Add", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_person_add_alt_24),
                        contentDescription = null,
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Spacer(modifier = Modifier.height(16.dp))

            // Display the added names using LazyColumn
            LazyColumn(horizontalAlignment = Alignment.Start) {
                items(nameList) { currentName ->

                    Text(text = currentName, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}
