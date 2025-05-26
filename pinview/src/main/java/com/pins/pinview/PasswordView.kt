package com.pins.pinview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.graphics.RectF
import android.text.InputFilter
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.util.DisplayMetrics
import androidx.appcompat.widget.AppCompatEditText

class PasswordView : AppCompatEditText {

    private val PIN_LENGTH = 4
    private val PIN_RADIUS = 200f
    private var PIN_MARGIN = 10f

    private lateinit var boxPaint: Paint
    private lateinit var cursorPaint: Paint
    private lateinit var strokePaint: Paint

    private var BOX_WIDTH:Float = 0f
    private var BOX_HEIGHT:Float = 0f

    private val CURSOR_WIDTH = 2
    private val CURSOR_HEIGHT = 40

    private var pinText:CharSequence?=null
    private val BOX_TOP = 10

    private val centerPoint: Point = Point()

    private val NO_FILTERS: Array<InputFilter?> = arrayOfNulls(0)

    var mWidth:Float = 0f
    var mHeight:Float = 0f

    var MARGIN_START = 10
    var MARGIN_END = 10


    var c:Context?=null



    private constructor(c: Context) : super(c) {


    }

    constructor(c: Context , a:AttributeSet) : super(c,a) {

        this.c = c

        init()

    }

    constructor(c: Context , a:AttributeSet , defStyle:Int) : super(c,a,defStyle) {

        this.c = c

        init()



    }

//

    private fun init() {

        strokePaint = Paint()
        strokePaint.isAntiAlias = true
        strokePaint.style = Paint.Style.STROKE
        strokePaint.color = Color.BLACK
        strokePaint.strokeWidth = 4f




        boxPaint = Paint()
        boxPaint.isAntiAlias = true
        boxPaint.color = Color.WHITE


        cursorPaint = Paint()
        cursorPaint.isAntiAlias = true
        cursorPaint.color = Color.BLACK
        cursorPaint.textAlign = Paint.Align.CENTER


        isFocusable = true
        isFocusableInTouchMode = true
        isCursorVisible = false

        isLongClickable = false
        setTextIsSelectable(false)
        transformationMethod = android.text.method.PasswordTransformationMethod.getInstance()
        inputType = android.text.InputType.TYPE_CLASS_NUMBER or android.text.InputType.TYPE_NUMBER_VARIATION_PASSWORD
        customSelectionActionModeCallback = object : android.view.ActionMode.Callback {
            override fun onActionItemClicked(mode: android.view.ActionMode?, item: android.view.MenuItem?) = false
            override fun onCreateActionMode(mode: android.view.ActionMode?, menu: android.view.Menu?) = false
            override fun onPrepareActionMode(mode: android.view.ActionMode?, menu: android.view.Menu?) = false
            override fun onDestroyActionMode(mode: android.view.ActionMode?) {}
        }

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        var dsp = resources.displayMetrics

        mWidth = dsp.widthPixels.toFloat()
        mHeight = dsp.heightPixels.toFloat()

        BOX_WIDTH = mWidth*0.15f
        BOX_HEIGHT = BOX_WIDTH

        PIN_MARGIN = BOX_WIDTH * 0.05f

        cursorPaint.textSize = BOX_WIDTH*0.80f

        val desiredWidth = PIN_LENGTH * BOX_WIDTH + (PIN_LENGTH - 1) * PIN_MARGIN
        val desiredHeight = BOX_TOP + BOX_HEIGHT

        val width = resolveSize(desiredWidth.toInt(), widthMeasureSpec)
        val height = resolveSize(desiredHeight.toInt(), heightMeasureSpec)

        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        //super.onDraw(canvas)

        for (i in 0 until PIN_LENGTH) {

            var left = i*(BOX_WIDTH+PIN_MARGIN)
            var top = BOX_TOP
            var right = left+BOX_WIDTH
            var bottom = top+BOX_HEIGHT

            left+=MARGIN_START
            right-=MARGIN_START
            top+=MARGIN_START
            bottom-=MARGIN_START




            val rect:RectF = RectF(left.toFloat()
                ,top.toFloat()
                ,right.toFloat()
                ,bottom.toFloat())



            canvas.drawRoundRect(rect,PIN_RADIUS,PIN_RADIUS,boxPaint)
            canvas.drawRoundRect(rect,PIN_RADIUS,PIN_RADIUS,strokePaint)


            pinText?.let {

                if (pinText!!.length>0) {

                    if (i<pinText!!.length) {


                        val midX = rect.centerX()
                        val midY = rect.centerY()

                        val fm = cursorPaint.fontMetrics
                        val baseline = midY - (fm.ascent + fm.descent) / 2

                        canvas.drawText(pinText!!
                            ,i
                            ,i+1
                            ,midX.toFloat()
                            ,baseline,cursorPaint)


                    }


                }


            }




        }

    }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {


        //pinText = text
        val transformation = transformationMethod
        if (transformation == null) {
            pinText = getText().toString()
        } else {
            pinText = transformation.getTransformation(getText(), this).toString()
        }
        invalidate()

    }

    private fun drawTextAndCursor() {


    }

    private fun dpToPx(dp:Int):Float {

        //val scaleFactor = c!!.resources.displayMetrics.densityDpi/DisplayMetrics.DENSITY_DEFAULT
        return (dp*c!!.resources.displayMetrics.density).toFloat()



    }

    public fun getOTP():String? {

        //val tr =


        pinText?.let {

            if (!pinText!!.isBlank()) {

                if (pinText!!.length == 4) {

                    transformationMethod = null

                    var otp = pinText

                    transformationMethod = PasswordTransformationMethod()



                    return otp.toString()

                }

            }
        }

        return null

    }


}