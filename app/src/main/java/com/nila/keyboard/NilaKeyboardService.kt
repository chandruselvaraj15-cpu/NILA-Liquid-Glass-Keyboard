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
