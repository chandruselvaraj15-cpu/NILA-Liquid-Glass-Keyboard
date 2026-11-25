package com.nila.keyboard
import android.animation.ValueAnimator
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.inputmethodservice.InputMethodService
import android.view.Gravity
import android.view.HapticFeedbackConstants
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.animation.LinearInterpolator
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import android.widget.TextView
class NilaKeyboardService : InputMethodService() {
private var shift = false
    private var symbols = false
    private val keyDrawables = mutableListOf<GradientDrawable>()
    private val letterKeys = mutableListOf<TextView>()
    private var animator: ValueAnimator? = null
 private fun dp(v: Int): Int = (v * resources.displayMetrics.density).toInt()
override fun onCreateInputView(): View = buildKeyboard()
 override fun onStartInputView(info: EditorInfo?, restarting: Boolean) {
        super.onStartInputView(info, restarting)
        startRgb()
    }
 override fun onFinishInputView(finishingInput: Boolean) {
        super.onFinishInputView(finishingInput)
        animator?.cancel()
    }
 override fun onDestroy() {
        animator?.cancel()
        super.onDestroy()
    }
    private fun startRgb() {
        animator?.cancel()
        animator = ValueAnimator.ofFloat(0f, 360f).apply {
            duration = 6000
            repeatCount = ValueAnimator.INFINITE
            interpolator = LinearInterpolator()
            addUpdateListener { va ->
                val h = va.animatedValue as Float
                keyDrawables.forEachIndexed { i, d ->
                    val c = Color.HSVToColor(210, floatArrayOf((h + i * 7f) % 360f, 0.75f, 1f))
                    d.setStroke(dp(1), c)
                }
            }
            start()
        }
    }
    private fun buildKeyboard(): View {
        keyDrawables.clear()
        letterKeys.clear()

        val screenW = resources.displayMetrics.widthPixels
        val keySize = (screenW - dp(12)) / 10 - dp(4)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(6), dp(8), dp(6), dp(10))
            background = GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                intArrayOf(Color.rgb(18, 15, 35), Color.rgb(8, 8, 20))
            )
        }
        val numberRow = "1234567890"
        val rows: List<String> = if (!symbols) {
            listOf("qwertyuiop", "asdfghjkl", "zxcvbnm")
        } else {
            listOf("@#₹_&-+()/", "=*\"':;!?%", "~<>[]{}")
        }
        root.addView(makeRow(numberRow.map { ch -> makeKey(ch.toString(), keySize, keySize, false) { type(ch.toString()) } }))
        root.addView(makeRow(rows[0].map { ch -> letterKey(ch, keySize) }))
        root.addView(makeRow(rows[1].map { ch -> letterKey(ch, keySize) }
     ) val row3 = mutableListOf<View>()
        row3.add(makeKey("⇧", keySize * 3 / 2, keySize, true) {
            shift = !shift
            updateLabels()
        })
        rows[2].forEach { ch -> row3.add(letterKey(ch, keySize)) }
        row3.add(makeKey("⌫", keySize * 3 / 2, keySize, true) { backspace() })
        root.addView(makeRow(row3))
  val bottom = listOf(
            makeKey(if (symbols) "ABC" else "?123", keySize * 3 / 2, keySize, true) {
                symbols = !symbols
                setInputView(buildKeyboard())
                startRgb()
            },
            makeKey(",", keySize, keySize, false) { type(",") },
            makeKey("NILA", keySize * 5, keySize, true) { type(" ") },
            makeKey(".", keySize, keySize, false) { type(".") },
            makeKey("↵", keySize * 3 / 2, keySize, true) { enter() }
        )
        root.addView(makeRow(bottom))
  updateLabels()
        return root
    }
    private fun makeRow(keys: List<View>): LinearLayout {
        return LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
 super.onDestroy()           gravity = Gravity.CENTER_HORIZONTAL
            keys.forEach { addView(it) }
        }
    }
 private fun letterKey(ch: Char, size: Int): TextView {
        val tv = makeKey(ch.toString(), size, size, false) {
            val out = if (shift && !symbols) ch.toString().uppercase() else ch.toString()
            type(out)
            if (shift && !symbols) {
                shift = false
                updateLabels()
            }
        }
        if (ch.isLetter()) letterKeys.add(tv)
        tv.tag = ch.toString()
        return tv
    }
  private fun updateLabels() {
        letterKeys.forEach { tv ->
            val base = tv.tag as String
            tv.text = if (shift) base.uppercase() else base
        }
    }
private fun makeKey(label: String, w: Int, h: Int, accent: Boolean, onClick: () -> Unit): TextView {
        val wide = w > h
        val bg = GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM,
            if (accent) intArrayOf(0x887B5CFF.toInt(), 0x334A2FBF)
            else intArrayOf(0x66FFFFFF, 0x1AFFFFFF)
        ).apply {
            if (wide) cornerRadius = h / 2f else shape = GradientDrawable.OVAL
            setStroke(dp(1), 0xAAFFFFFF.toInt())
        }
        keyDrawables.add(bg)
return TextView(this).apply {
            text = label
            gravity = Gravity.CENTER
            setTextColor(Color.WHITE)
            textSize = if (label.length > 1) 13f else 17f
            background = bg
            layoutParams = LinearLayout.LayoutParams(w, h).apply {
                setMargins(dp(2), dp(3), dp(2), dp(3))
            }
            setOnTouchListener { v, e ->
                when (e.action) {
                    MotionEvent.ACTION_DOWN -> {
                        v.animate().scaleX(0.88f).scaleY(0.88f).setDuration(60).start()
                        v.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
                    }
                    MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL ->
                        v.animate().scaleX(1f).scaleY(1f).setDuration(90).start()
                }
                false
            }
            setOnClickListener { onClick() }
        }
    }
    private fun type(text: String) {
        currentInputConnection?.commitText(text, 1)
    }
 private fun backspace() {
        val ic = currentInputConnection ?: return
        val selected = ic.getSelectedText(0)
        if (selected != null && selected.isNotEmpty()) {
            ic.commitText("", 1)
        } else {
            ic.deleteSurroundingText(1, 0)
        }
    }
    private fun enter() {
        val ei = currentInputEditorInfo
        val action = (ei?.imeOptions ?: 0) and EditorInfo.IME_MASK_ACTION
        val noEnterAction = ((ei?.imeOptions ?: 0) and EditorInfo.IME_FLAG_NO_ENTER_ACTION) != 0
        if (ei == null || noEnterAction ||
            action == EditorInfo.IME_ACTION_NONE ||
            action == EditorInfo.IME_ACTION_UNSPECIFIED
        ) {
            sendDownUpKeyEvents(KeyEvent.KEYCODE_ENTER)
        } else {
            currentInputConnection?.performEditorAction(action)
        }
    }
}
