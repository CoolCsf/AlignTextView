package com.example.aligntextview

import android.view.ViewGroup

fun AutoAlignLinearLayout.setMaxLengthAndFontSize() {
    this.setSetAutoAlign(fontSize)
}

fun AutoAlignRelativeLayout.setMaxLengthAndFontSize() {
    this.setSetAutoAlign(fontSize)
}

private fun ViewGroup.setSetAutoAlign(fontSize: Float) {
    val maxLength = (0..childCount)
        .map { getChildAt(it) }
        .filterIsInstance(AlignTextView::class.java)
        .maxBy { it.getAllTextLength() }?.getAllTextLength() ?: 0
    (0..childCount)
        .map { getChildAt(it) }
        .filterIsInstance(AlignTextView::class.java)
        .forEach {
            it.totalLength = maxLength
            it.setFontSize(fontSize)
        }
}