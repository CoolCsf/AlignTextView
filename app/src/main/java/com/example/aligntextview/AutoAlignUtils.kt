package com.example.aligntextview

import android.view.View
import android.view.ViewGroup
import kotlin.math.max

fun AutoAlignLinearLayout.setMaxLengthAndFontSize() {
    this.setSetAutoAlign(fontSize)
}

fun AutoAlignRelativeLayout.setMaxLengthAndFontSize() {
    this.setSetAutoAlign(fontSize)
}

private fun ViewGroup.setSetAutoAlign(fontSize: Float) {
    val maxLength = this.getMaxLength()
    this.setAlignTextViewSize(fontSize, maxLength)
}

private fun ViewGroup.getMaxLength(): Int {
    var maxLength = 0
    (0..childCount)
        .map { getChildAt(it) }
        .forEach {
            if (it is ViewGroup) {
                maxLength = it.getMaxLength()
            } else if (it is AlignTextView) {
                maxLength = max(it.getAllTextLength(), maxLength)
            }
        }
    return maxLength
}

private fun ViewGroup.setAlignTextViewSize(fontSize: Float, maxLength: Int) {
    (0..childCount)
        .map { getChildAt(it) }
        .forEach {
            if (it is ViewGroup) {
                it.setAlignTextViewSize(fontSize, maxLength)
            } else if (it is AlignTextView) {
                it.totalLength = maxLength
                it.setFontSize(fontSize)
            }
        }
}
