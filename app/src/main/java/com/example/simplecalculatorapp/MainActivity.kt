package com.example.simplecalculatorapp

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    private val operatorsRegex = Regex("[+\\-*/]")
    private lateinit var calculationField: TextView
    private lateinit var resultField: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        calculationField = findViewById(R.id.tvCalculation)
        resultField = findViewById(R.id.tvResult)

        var mainView = findViewById<ViewGroup>(R.id.main)

        val numberButtons = mainView.children.filter { it.tag == "number" }
        numberButtons.forEach { numberButton ->
            numberButton.setOnClickListener {
                onNumberButtonClick(numberButton)
            }
        }

        val operatorButtons = mainView.children.filter { it.tag == "operator" }
        operatorButtons.forEach { operatorButton ->
            operatorButton.setOnClickListener {
                onOperatorButtonClick(operatorButton)
            }
        }

        val clearButton = findViewById<Button>(R.id.btnClear)
        clearButton.setOnClickListener {
            onClearButtonClick()
        }

        val equalsButton = findViewById<Button>(R.id.btnEquals)
        equalsButton.setOnClickListener {
            onEqualsButtonClick()
        }

        val floatingPointButton = findViewById<Button>(R.id.btnFloatingPoint)
        floatingPointButton.setOnClickListener {
            onFloatingPointButtonClick()
        }
    }

    private fun onClearButtonClick() {
        calculationField.text = ""
        resultField.text = ""
    }

    private fun onNumberButtonClick(view: View) {
        val buttonText = (view as Button).text.toString()
        calculationField.append(buttonText)
    }

    private fun onOperatorButtonClick(view: View) {
        val buttonText = (view as Button).text.toString()

        if (calculationField.text.isNotEmpty() && !calculationField.text.contains(operatorsRegex)) {
            calculationField.append(" $buttonText ")
        }
    }

    private fun onEqualsButtonClick() {
        val calculation = calculationField.text.toString()
        if (calculation.isNotEmpty()) {
            val result = ExpressionBuilder(calculation).build()
            resultField.text = buildString {
                append("= ")
                append(result.evaluate())
            }
        }
    }

    private fun onFloatingPointButtonClick() {
        val calculation = calculationField.text.toString()
        if (calculation.isNotEmpty() && !calculation.contains('.')) {
            calculationField.append(".")
        }
    }
}
