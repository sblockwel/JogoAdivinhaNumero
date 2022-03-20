package com.example.jogoadivinhanumero

import kotlin.random.Random

class RandomGame {
    var numberAttempts = ArrayList<Number>()
    var sortedNumber: Int? = null

    fun checkNumber(number: Number): Boolean {
        if (sortedNumber == null) {
            sortedNumber = Random.nextInt(100)
        }
        return if (sortedNumber == number) {
            System.out.println("Numero valido")
            numberAttempts.clear()
            true
        } else {
            numberAttempts.add(number)
            false
        }
    }

    fun isValidNumber(numberString: String): Int? {
        var number: Int? = null

        try {
            number = numberString.toInt()
        } catch (ex: Exception) {

        }
        return number
    }
}