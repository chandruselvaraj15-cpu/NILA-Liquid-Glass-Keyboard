package com.nila.keyboard
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.provider.Settings
import android.view.Gravity
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
class MainActivity : Activity() {
    private fun dp(v: Int): Int = (v * resources.displayMetrics.density).toInt()
    private fun glassButton(label: String, onClick: () -> Unit): Button {
        return Button(this).apply {
            text = label
            isAllCaps = false
            setTextColor(Color.WHITE)
            textSize = 16f
            background = GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT,
                intArrayOf(0xAA7B5CFF.toInt(), 0xAA3A8DFF.toInt())
            ).apply {
                cornerRadius = dp(28).toFloat()
                setStroke(dp(1), 0xAAFFFFFF.toInt())
            }
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, dp(56)
            ).apply { setMargins(0, dp(10), 0, dp(10)) }
            setOnClickListener { onClick() }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(dp(28), dp(28), dp(28), dp(28))
            background = GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                intArrayOf(Color.rgb(24, 18, 52), Color.rgb(6, 6, 16))
            )
        }
        val title = TextView(this).apply {
            text = "NILA"
            textSize = 44f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
            letterSpacing = 0.3f
        }
        val subtitle = TextView(this).apply {
            text = "Your Liquid Glass AI Keyboard"
            textSize = 15f
            setTextColor(Color.LTGRAY)
            gravity = Gravity.CENTER
            setPadding(0, dp(4), 0, dp(36))
        }
        val step1 = glassButton("1. Enable NILA Keyboard") {
            startActivity(Intent(Settings.ACTION_INPUT_METHOD_SETTINGS))
        }
        val step2 = glassButton("2. Select NILA Keyboard") {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showInputMethodPicker()
        }
        val testBox = EditText(this).apply {
            hint = "Type here to test NILA..."
            setHintTextColor(Color.GRAY)
            setTextColor(Color.WHITE)
            background = GradientDrawable().apply {
                cornerRadius = dp(20).toFloat()
                setColor(0x33FFFFFF)
                setStroke(dp(1), 0x66FFFFFF)
            }
            setPadding(dp(18), dp(14), dp(18), dp(14))
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, dp(24), 0, 0) }
        }
        layout.addView(title)
        layout.addView(subtitle)
        layout.addView(step1)
        layout.addView(step2)
        layout.addView(testBox)
        setContentView(layout)
    }
}
