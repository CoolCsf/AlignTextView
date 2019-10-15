package com.example.aligntextview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import java.lang.NullPointerException
import kotlin.math.ceil

class AlignTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private lateinit var text :String
    private var preText:String?=null
    private var textColor:Int = 0
    private var preTextColor:Int=0
    var fontSize : Float = 0.0f
    var totalLength :Float = 0.0f
    private var textPaint :Paint
    private var preTextPaint :Paint
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
        textPaint = Paint().apply {
            this.color=textColor
            this.textSize=fontSize
        }
        preTextPaint = Paint().apply {
            this.color=preTextColor
            this.textSize=fontSize
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val fm = textPaint.fontMetrics
        val height = ceil(fm.descent-fm.ascent.toDouble()) // 获得文字的高度
        setMeasuredDimension(totalLength.toInt(),height.toInt())
    }

//    private fun getWiidth():Int{
//        return totalLength
//    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

    }
}