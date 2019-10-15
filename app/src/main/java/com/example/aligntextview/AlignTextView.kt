package com.example.aligntextview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import java.lang.NullPointerException

class AlignTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private lateinit var text :String
    private var preText:String?=null
    private var textColor:Int = 0
    private var preTextColor:Int=0
    var fontSize : Float = 0.0f
    var totalLength :Float = 0.0f

    init {
        val attr =  context.obtainStyledAttributes(attrs,R.styleable.AlignTextView)
        attr?.let {
            text = attr.getString(R.styleable.AlignTextView_text)?:throw NullPointerException("文字不能为空")
            preText = attr.getString(R.styleable.AlignTextView_pre_text)
            textColor = attr.getColor(R.styleable.AlignTextView_color,Color.BLACK)
            preTextColor = attr.getColor(R.styleable.AlignTextView_pre_color,Color.BLACK)
            fontSize = attr.getDimension(R.styleable.AlignTextView_font_size,10f)
            totalLength = attr.getDimension(R.styleable.AlignTextView_total_length,100f)
            attr.recycle()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

    }
}