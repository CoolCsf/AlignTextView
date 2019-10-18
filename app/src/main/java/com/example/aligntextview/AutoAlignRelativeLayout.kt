package com.example.aligntextview

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.RelativeLayout

class AutoAlignRelativeLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {
    var fontSize: Float = 0.0f

    init {
        val attr = context.obtainStyledAttributes(attrs, R.styleable.AutoAlignRelativeLayout)
        attr?.let {
            fontSize =
                attr.getDimension(R.styleable.AutoAlignRelativeLayout_rl_child_font_size, 10f)
            attr.recycle()
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        this.setMaxLengthAndFontSize()
    }
}