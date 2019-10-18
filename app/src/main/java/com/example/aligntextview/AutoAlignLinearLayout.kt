package com.example.aligntextview

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

class AutoAlignLinearLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    var fontSize: Float = 0.0f

    init {
        val attr = context.obtainStyledAttributes(attrs, R.styleable.AutoAlignLinearLayout)
        attr?.let {
            fontSize = attr.getDimension(R.styleable.AutoAlignLinearLayout_ll_child_font_size, 10f)
            attr.recycle()
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        this.setMaxLengthAndFontSize()
    }
}