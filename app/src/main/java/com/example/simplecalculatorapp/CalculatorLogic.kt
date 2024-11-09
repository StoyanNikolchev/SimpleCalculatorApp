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
            resultField.text = buildString {
                append("= ")
                append(result)
            }
        }
    }
}