package com.example.simplecalculatorapp

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.children

class AppLogic(val viewGroup: ViewGroup) {

    fun switchMode(switch: SwitchCompat, isOn: Boolean) {
        if (isOn) {
            enableLightMode(viewGroup)
            return
        }
        enableDarkMode(viewGroup)
    }

    private fun enableLightMode(viewGroup: ViewGroup) {
        (viewGroup as View).setBackgroundColor(Color.WHITE)

        viewGroup.children.forEach { child ->
            if (child is Button && child !is SwitchCompat) {
                child.setBackgroundColor(Color.LTGRAY)

                if (child.tag.toString() != "equals" && child.tag.toString() != "clear") {
                    child.setTextColor(Color.BLACK)
                }

            } else if (child is TextView) {
                child.setBackgroundColor(Color.LTGRAY)
                child.setTextColor(Color.BLACK)
            }
        }
    }

    private fun enableDarkMode(viewGroup: ViewGroup) {
        (viewGroup as View).setBackgroundColor(Color.BLACK)

        viewGroup.children.forEach { child ->
            if (child is Button && child !is SwitchCompat) {
                child.setBackgroundColor(Color.parseColor("#4b4b4b"))

                if (child.tag.toString() != "equals" && child.tag.toString() != "clear") {
                    child.setTextColor(Color.WHITE)
                }

            } else if (child is TextView) {
                child.setBackgroundColor(Color.parseColor("#4b4b4b"))
                child.setTextColor(Color.BLACK)
            }
        }
    }
}