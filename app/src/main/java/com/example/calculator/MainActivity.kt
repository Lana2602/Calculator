package com.example.myapplication

import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R


class MainActivity : AppCompatActivity() {

    private var currentInput = StringBuilder()
    private var operand1: Double = 0.0
    private var operator = ""

    private lateinit var editText: EditText
    private lateinit var btn0: Button
    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button
    private lateinit var btn4: Button
    private lateinit var btn5: Button
    private lateinit var btn6: Button
    private lateinit var btn7: Button
    private lateinit var btn8: Button
    private lateinit var btn9: Button
    private lateinit var btnAddition: Button
    private lateinit var btnSubtraction: Button
    private lateinit var btnMultiplication: Button
    private lateinit var btnDivision: Button
    private lateinit var btnClear: Button
    private lateinit var btnEqual: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)
        btn0 = findViewById(R.id.btn0)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        btn4 = findViewById(R.id.btn4)
        btn5 = findViewById(R.id.btn5)
        btn6 = findViewById(R.id.btn6)
        btn7 = findViewById(R.id.btn7)
        btn8 = findViewById(R.id.btn8)
        btn9 = findViewById(R.id.btn9)
        btnAddition = findViewById(R.id.btnAddition)
        btnSubtraction = findViewById(R.id.btnSubtraction)
        btnMultiplication = findViewById(R.id.btnMultiplication)
        btnDivision = findViewById(R.id.btnDivision)
        btnClear = findViewById(R.id.btnClear)
        btnEqual = findViewById(R.id.btnEqual)

        setupNumberButtons()
        setupOperatorButtons()
        setupClearButton()
        setupEqualButton()
    }

    private fun setupNumberButtons() {
        val numberButtons = listOf(btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9)

        for (button in numberButtons) {
            button.setOnClickListener {
                onNumberButtonClick(button)
            }
        }
    }

    private fun setupOperatorButtons() {
        val operatorButtons = listOf(btnAddition, btnSubtraction, btnMultiplication, btnDivision)

        for (button in operatorButtons) {
            button.setOnClickListener {
                onOperatorButtonClick(button)
            }
        }
    }

    private fun setupClearButton() {
        btnClear.setOnClickListener {
            onClearButtonClick()
        }
    }

    private fun setupEqualButton() {
        btnEqual.setOnClickListener {
            onEqualButtonClick()
        }
    }

    private fun onNumberButtonClick(button: Button) {
        currentInput.append(button.text)
        updateEditText()
    }

    private fun onOperatorButtonClick(button: Button) {
        if (currentInput.isNotEmpty()) {
            operand1 = currentInput.toString().toDouble()
            currentInput.clear()
            operator = button.text.toString()
            updateEditText()
        }
    }

    private fun onClearButtonClick() {
        currentInput.clear()
        operand1 = 0.0
        operator = ""
        updateEditText()
    }

    private fun onEqualButtonClick() {
        if (currentInput.isNotEmpty() && operator.isNotEmpty()) {
            val operand2 = currentInput.toString().toDouble()
            val result = performOperation(operand1, operand2, operator)
            currentInput.clear()
            currentInput.append(result)
            updateEditText()
            operator = ""
        }
    }

    private fun performOperation(operand1: Double, operand2: Double, operator: String): Double {
        return when (operator) {
            "+" -> operand1 + operand2
            "-" -> operand1 - operand2
            "*" -> operand1 * operand2
            "/" -> operand1 / operand2
            else -> throw IllegalArgumentException("Invalid operator")
        }
    }
    private fun updateEditText() {
        editText.text = Editable.Factory.getInstance().newEditable(currentInput)
    }


}
