package com.nila.keyboard
import android.app.Activity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.content.Context
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.graphics.Color
import android.view.Gravity

class MainActivity : Activity() {
override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
 val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(30, 30, 30, 30)
            setBackgroundColor(Color.rgb(18, 15, 35))
        }
val title = TextView(this).apply {
            text = "NILA"
            textSize = 38f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
        }
 val subtitle = TextView(this).apply {
            text = "Your Liquid Glass AI Keyboard"
            textSize = 16f
            setTextColor(Color.LTGRAY)
            gravity = Gravity.CENTER
        }
  val enableButton = Button(this).apply {
            text = "Enable NILA Keyboard"
            setOnClickListener {
                val manager = getSystemService(
                    Context.INPUT_METHOD_SERVICE
                ) as InputMethodManager
                manager.showInputMethodPicker()
            }
        }
    layout.addView(title)
        layout.addView(subtitle)
        layout.addView(enableButton)
 setContentView(layout)
    }
}
