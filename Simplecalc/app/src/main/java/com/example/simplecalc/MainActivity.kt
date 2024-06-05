package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private lateinit var displayScreen: TextView
    private var currentNumber = ""
    private var firstOperand = ""
    private var operator = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        displayScreen = findViewById(R.id.displayScreen)

        val buttons = arrayOf(
            findViewById<Button>(R.id.button0),
            findViewById<Button>(R.id.button1),
            findViewById<Button>(R.id.button2),
            findViewById<Button>(R.id.button3),
            findViewById<Button>(R.id.button4),
            findViewById<Button>(R.id.button5),
            findViewById<Button>(R.id.button6),
            findViewById<Button>(R.id.button7),
            findViewById<Button>(R.id.button8),
            findViewById<Button>(R.id.button9),
            findViewById<Button>(R.id.buttonPlus),
            findViewById<Button>(R.id.buttonMinus),
            findViewById<Button>(R.id.buttonMultiply),
            findViewById<Button>(R.id.buttonDivide),
            findViewById<Button>(R.id.buttonClear),
            findViewById<Button>(R.id.buttonEquals)  // Added the equals button
        )

        for (button in buttons) {
            button.setOnClickListener { onButtonClick(button.text.toString()) }
        }
    }

    private fun onButtonClick(text: String) {
        when (text) {
            "C" -> resetCalculator()
            "+" -> setOperator("+")
            "-" -> setOperator("-")
            "×" -> setOperator("*")  // Changed to × for display
            "÷" -> setOperator("/")  // Changed to ÷ for display
            "=" -> calculateResult()
            else -> appendNumber(text)
        }
    }

    private fun appendNumber(number: String) {
        currentNumber += number
        displayScreen.text = currentNumber
    }

    private fun setOperator(op: String) {
        if (firstOperand.isEmpty()) {
            firstOperand = currentNumber
            operator = op
            currentNumber = ""
        } else if (operator.isNotEmpty()) {
            calculateIntermediateResult()
            operator = op
        }
    }

    private fun calculateIntermediateResult() {
        val result = when (operator) {
            "+" -> firstOperand.toDouble() + currentNumber.toDouble()
            "-" -> firstOperand.toDouble() - currentNumber.toDouble()
            "*" -> firstOperand.toDouble() * currentNumber.toDouble()
            "/" -> firstOperand.toDouble() / currentNumber.toDouble()
            else -> 0.0
        }
        firstOperand = result.toString()
        currentNumber = ""
        displayScreen.text = result.toString()
    }

    private fun calculateResult() {
        if (operator.isNotEmpty() && currentNumber.isNotEmpty()) {
            val result = when (operator) {
                "+" -> firstOperand.toDouble() + currentNumber.toDouble()
                "-" -> firstOperand.toDouble() - currentNumber.toDouble()
                "*" -> firstOperand.toDouble() * currentNumber.toDouble()
                "/" -> firstOperand.toDouble() / currentNumber.toDouble()
                else -> 0.0
            }
            displayScreen.text = result.toString()
            currentNumber = result.toString()
            firstOperand = ""
            operator = ""
        }
    }

    private fun resetCalculator() {
        currentNumber = ""
        firstOperand = ""
        operator = ""
        displayScreen.text = "0"
    }
}

