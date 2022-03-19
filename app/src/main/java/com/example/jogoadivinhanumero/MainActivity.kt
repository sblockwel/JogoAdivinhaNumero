package com.example.jogoadivinhanumero

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jogoadivinhanumero.ui.theme.JogoAdivinhaNumeroTheme
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JogoAdivinhaNumeroTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    Menu()
}

@Composable
fun ErrorScreen() {

}

@Composable
fun Menu() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(all = 8.dp)
            .fillMaxSize()

    ) {
        var numberState by remember {
            mutableStateOf("")
        }
        val context = LocalContext.current
        
        Text(text = "Adivinhe o número ", style = MaterialTheme.typography.h4)
        Text(text = "entre 1 e 100", style = MaterialTheme.typography.h4)

        OutlinedTextField(
            value = numberState,
            onValueChange = {numberState = it},
            label = { Text("Digite um número") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (numberState.isNotBlank()){
                    Toast.makeText(context, "Enviado", Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Text(text = "Jogar", style = MaterialTheme.typography.h6)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JogoAdivinhaNumeroTheme {
        App()
    }
}