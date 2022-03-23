package com.example.jogoadivinhanumero

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class RandomScreen {
    var gameState: GameState = GameState.Idle
    var context: Context? = null
    var game = RandomGame()
    var informedNumber: Int? = null

    fun btnPlayOnClick(numberState: String) {
        if (numberState.isNotBlank()) {
            val number = game.isValidNumber(numberState)
            if (number == null) {
                context?.showToast("Informe um número válido e tente novamente!")
                gameState = GameState.Error
            } else if (game.checkNumber(number)) {
                context?.showToast("Número válido!")
                gameState = GameState.Success
            } else {
                context?.showToast("Número errado!")
                gameState = GameState.Error
                informedNumber = number
            }
        }
    }

    @Composable
    fun showGameScreen() {
        context = LocalContext.current
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

            Text(text = "Adivinhe o número ", style = MaterialTheme.typography.h4)
            Text(text = "entre 1 e 100", style = MaterialTheme.typography.h4)

            OutlinedTextField(
                value = numberState,
                onValueChange = { numberState = it },
                label = { Text("Digite um número") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    btnPlayOnClick(numberState)
                    numberState = ""
                }
            ) {
                Text("Jogar", style = MaterialTheme.typography.h6)
            }

        }
        when (gameState) {
            GameState.Error -> {
                gameState = GameState.Idle
                showErrorScreen()
            }
            GameState.Success -> {
                gameState = GameState.Idle
                showSuccessScreen()
            }
        }

    }

    @Composable
    fun showSuccessScreen() {
        var canNavigateState by remember {
            mutableStateOf(false)
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(all = 8.dp)
                .fillMaxSize()
                .background(Color(14, 82, 32))
        ) {
            Text(text = "Parábens você acertou o número", fontSize = 40.sp, color = Color.White)
            Button(onClick = { canNavigateState = true })
            {
                Text("Novo Jogo", style = MaterialTheme.typography.h6)
            }
        }
        if (canNavigateState)
            showGameScreen()
    }

    @Composable
    fun showErrorScreen() {
        var canNavigateState by remember {
            mutableStateOf(false)
        }
        var tipNumber = if (game.sortedNumber!! < informedNumber!!) "menor" else "maior"

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(all = 8.dp)
                .fillMaxSize()
                .background(Color(140, 16, 4))
        ) {
            Text(text = "Você errou o número", fontSize = 40.sp)
            Text(text = "Dica: O número é $tipNumber que o informado", fontSize = 20.sp, color = Color.White)
            Button(onClick = { canNavigateState = true })
            {
                Text("Jogar novamente", style = MaterialTheme.typography.h6)
            }
        }
        if (canNavigateState)
            showGameScreen()
    }

    @Composable
    fun showAlertDialog(message: String, onCancel: () -> Unit = {}, onOK: () -> Unit = {}) {
        AlertDialog(onDismissRequest = onCancel, text = { Text(message) }, confirmButton = {
            Button(
                onClick = onOK
            ) {
                Text("OK")
            }
        })
    }

    fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }
}