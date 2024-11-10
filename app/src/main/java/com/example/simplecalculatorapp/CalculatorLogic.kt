package com.example.simplecalculatorapp

import android.widget.TextView
import net.objecthunter.exp4j.ExpressionBuilder

class CalculatorLogic(
    private val calculationField: TextView,
    private val resultField: TextView
) {

    fun calculate() {
        val calculation = calculationField.text.toString()
        if (calculation.isNotEmpty()) {
            val result = ExpressionBuilder(calculation).build().evaluate()

            var resultString = result.toString()

            if (resultString.contains(".")) {
                val decimalPart = resultString.substringAfter(".")
                if (decimalPart.length > 5) {
                    resultString = resultString.substring(0, resultString.indexOf('.') + 6) + "..."
                }
            }

            if (result % 1 == 0.0) {
                resultString = result.toInt().toString()
            }

            resultField.text = buildString{
                append("= ")
                append(resultString)
            }
        }
    }
}