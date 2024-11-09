package com.example.simplecalculatorapp

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children

class MainActivity : AppCompatActivity() {
    private lateinit var calculationField: TextView
    private lateinit var resultField: TextView
    private lateinit var calculatorLogic: CalculatorLogic
    private lateinit var buttonHandlers: ButtonHandlers

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        calculationField = findViewById(R.id.tvCalculation)
        resultField = findViewById(R.id.tvResult)

        calculatorLogic = CalculatorLogic(calculationField, resultField)
        buttonHandlers = ButtonHandlers(calculationField, resultField)

        var mainView = findViewById<ViewGroup>(R.id.main)

        val buttons = mainView.children.filter { it is Button && it.id.toString() != "btnEquals" }
        buttons.forEach { button ->
            button.setOnClickListener {
                buttonHandlers.onButtonClick(button as Button)
            }
        }

        val equalsButton = findViewById<Button>(R.id.btnEquals)
        equalsButton.setOnClickListener {
            calculatorLogic.calculate()
        }
    }
}
