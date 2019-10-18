package com.example.aligntextview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import kotlin.math.ceil
import kotlin.math.max

class AlignTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private lateinit var text: String
    private var preText: String? = null
    private var textColor: Int = 0
    private var preTextColor: Int = 0
    var fontSize: Float = 0.0f
        private set
    var totalLength: Int = 0
    private var textPaint: Paint
    private var preTextPaint: Paint
    private var singleBlankSize = 10

    init {
        val attr = context.obtainStyledAttributes(attrs, R.styleable.AlignTextView)
        attr?.let {
            text = attr.getString(R.styleable.AlignTextView_text)
                ?: throw NullPointerException("文字不能为空")
            preText = attr.getString(R.styleable.AlignTextView_pre_text)
            textColor = attr.getColor(R.styleable.AlignTextView_color, Color.BLACK)
            preTextColor = attr.getColor(R.styleable.AlignTextView_pre_color, Color.BLACK)
            fontSize = attr.getDimension(R.styleable.AlignTextView_font_size, 10f)
            totalLength = attr.getInt(R.styleable.AlignTextView_max_length, 100)
            attr.recycle()
        }
        textPaint = TextPaint().apply {
            this.color = textColor
            this.textSize = fontSize
        }
        preTextPaint = TextPaint().apply {
            this.color = preTextColor
            this.textSize = fontSize
        }
    }

    fun setFontSize(fontSize: Float) {
        this.fontSize = fontSize
        textPaint.textSize = fontSize
        preTextPaint.textSize = fontSize
    }

    fun getAllTextLength(): Int {
        return (preText?.length ?: 0) + text.length
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val totalWidth = textPaint.measureText(text[0].toString()) * totalLength
        singleBlankSize =
            ((totalWidth - textPaint.measureText(text) - (preText?.let {
                preTextPaint.measureText(
                    preText
                )
            } ?: 0f)) / (text.length - 1)).toInt()
        val fm = textPaint.fontMetrics
        val height = ceil(fm.descent - fm.ascent.toDouble()) // 获得文字的高度
        setMeasuredDimension(
            max(
                totalWidth.toInt(),
                textPaint.measureText(text).toInt() + (preText?.let { textPaint.measureText(preText) }?.toInt()
                    ?: 0)
            ), height.toInt()
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val preTextWidth = preText?.also {
            canvas.drawText(it, 0f, height.toFloat() - 10f, preTextPaint) //todo -10是为了垂直居中，可优化
        }?.let {
            textPaint.measureText(it)
        } ?: 0f

        val letterWidth = textPaint.measureText(text[0].toString())
        text.forEachIndexed { index, it ->
            if (index == 0) {
                canvas.drawText(
                    it.toString(),
                    preTextWidth,
                    height.toFloat() - 10f,
                    textPaint
                )  //todo -10是为了垂直居中，可优化
            } else {
                val startX = index * (letterWidth + singleBlankSize) + preTextWidth
                canvas.drawText(
                    it.toString(),
                    startX,
                    height.toFloat() - 10f,
                    textPaint
                )  //todo  -10是为了垂直居中，可优化
            }
        }
    }
}