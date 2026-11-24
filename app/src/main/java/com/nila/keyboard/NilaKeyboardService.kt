
package com.nila.keyboard

import android.inputmethodservice.InputMethodService
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout

class NilaKeyboardService : InputMethodService() {

    override fun onCreateInputView(): View {
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setBackgroundColor(Color.TRANSPARENT)
            setPadding(6, 12, 6, 12)
        }

        val rows = listOf(
            listOf("Q","W","E","R","T","Y","U","I","O","P"),
            listOf("A","S","D","F","G","H","J","K","L"),
            listOf("Z","X","C","V","B","N","M","⌫"),
            listOf("🌸", "123", "😊", "SPACE", "↵")
        )

        rows.forEach { keys ->
            val row = LinearLayout(this).apply {
                gravity = Gravity.CENTER
            }

            keys.forEach { key ->
                val button = Button(this).apply {
                    text = key
                    textSize = 15f
                    setTextColor(Color.WHITE)
                    setBackgroundColor(Color.rgb(45, 35, 75))
                    setOnClickListener {
                        val connection = currentInputConnection
                        when (key) {
                            "SPACE" -> connection?.commitText(" ", 1)
                            "↵" -> connection?.commitText("\n", 1)
                            "⌫" -> connection?.deleteSurroundingText(1, 0)
                            "🌸", "123", "😊" -> { }
                            else -> connection?.commitText(key, 1)
                        }
                    }
                }

                row.addView(button, LinearLayout.LayoutParams(
                    0, 54, 1f
                ).apply {
                    setMargins(2, 3, 2, 3)
                })
            }

            root.addView(row)
        }

        return root
    }
}
