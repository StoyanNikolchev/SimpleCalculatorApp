package com.example.simplecalculatorapp

import android.content.res.Resources
import android.util.DisplayMetrics
import android.util.TypedValue
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
        val maxDigits = calculateMaxDigitsToFitScreen(calculationField.textSize)

        //Stops the user from entering more than 10 digits before the operator
        if (!calculationField.text.contains(operatorsRegex) && calculationField.length() >= maxDigits) {
            return
        }

        //Stops the user from entering more than 10 digits after the operator
        if (calculationField.text.contains(operatorsRegex) && calculationField.text.split(" ")
                .last().length >= maxDigits
        ) {
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

    private fun calculateMaxDigitsToFitScreen(textSizePx: Float): Int {
        val textSizeSp = convertPixelsToSp(textSizePx)
        val displayMetrics = Resources.getSystem().displayMetrics
        val screenWidth = Resources.getSystem().displayMetrics.widthPixels

        val charWidth = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            textSizeSp,
            displayMetrics
        )

        return (screenWidth / charWidth).toInt()
    }

    private fun convertPixelsToSp(px: Float) : Float {
        val densityDpi = Resources.getSystem().displayMetrics.densityDpi.toFloat()
        return px * (DisplayMetrics.DENSITY_DEFAULT / densityDpi)
    }
}