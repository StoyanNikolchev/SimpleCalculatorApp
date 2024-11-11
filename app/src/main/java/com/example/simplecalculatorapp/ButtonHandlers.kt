package com.example.simplecalculatorapp

import android.widget.Button
import android.widget.TextView

class ButtonHandlers(private val calculationField: TextView, private val resultField: TextView) {

    private val operatorsRegex = Regex("[+\\-*/]")

    fun onButtonClick(button: Button) {
        when (button.tag) {
            "number" -> onNumberButtonClick(button)
            "operator" -> onOperatorButtonClick(button)
            "clear" -> onClearButtonClick()
            "floatingPoint" -> onFloatingPointButtonClick()
        }
    }

    fun onNumberButtonClick(button: Button) {
        //Stops the user from entering more than 10 digits before the operator
        if (!calculationField.text.contains(operatorsRegex) && calculationField.length() >= 10) {
            return
        }

        //Stops the user from entering more than 10 digits after the operator
        if (calculationField.text.contains(operatorsRegex) && calculationField.text.split(" ").last().length >= 10) {
            return
        }

        val buttonText = button.text.toString()
        calculationField.append(buttonText)
    }

    fun onOperatorButtonClick(button: Button) {
        val buttonText = button.text.toString()
        val currentText = calculationField.text.toString()

        if (currentText.isNotEmpty() && !currentText.contains(operatorsRegex)) {
            calculationField.append(" $buttonText ")
        }
    }

    fun onClearButtonClick() {
        calculationField.text = ""
        resultField.text = ""
    }

    fun onFloatingPointButtonClick() {
        val calculation = calculationField.text.toString()
        if (calculation.isNotEmpty() && !calculation.contains('.')) {
            calculationField.append(".")
        }
    }
}