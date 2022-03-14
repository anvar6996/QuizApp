package uz.usoft.quizapp.presentation.cutomview

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.graphics.drawable.shapes.Shape
import android.util.AttributeSet
import android.view.Gravity.apply
import android.view.View
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.GravityCompat.apply
import com.tozny.crypto.android.AesCbcWithIntegrity.PrngFixes.apply
import uz.usoft.quizapp.R
import kotlin.math.sign
import kotlin.math.sin
import kotlin.math.sqrt


class DashCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attrs, defStyle) {

    var path = Path()
    var lineWith = 10f

    var colorFigure = Color.GREEN

    private var amplitude = getScreenWidth() / 3.7
    /// scale

    @SuppressLint("WrongConstant")
    val paint = Paint().apply {
        this.isLinearText = true
//        this.flags=11000
//        this.strokeMiter=0f
        this.isStrikeThruText = true
        this.style = Paint.Style.STROKE
        strokeWidth = lineWith
        this.letterSpacing = 15f
    }
    val paintB = Paint().apply {
        this.isLinearText = true
//        this.flags=11000
//        this.strokeMiter=0f
        color=Color.TRANSPARENT
        this.isStrikeThruText = true
        this.style = Paint.Style.STROKE
        strokeWidth = lineWith
        this.letterSpacing = 15f
    }


    override fun onDraw(canvas: Canvas) {
        paintLines(canvas)
    }

    @SuppressLint("ResourceAsColor")
    private fun paintLines(canvas: Canvas) {
        var x1=0.0
        var helper=5
        var y1=0.0
        for (i in 0 until getScreenWidth()) {
//            var y = 16*sqrt(i.toDouble())+70f
            val y = i*i/100
            val x = i
            if(helper<=5)
            {
                paint.color = Color.RED
                canvas.drawLine(x1.toFloat(), y1.toFloat(), x.toFloat(), y.toFloat(), paint)
            }
            if (helper==10)
            {
                helper=0
            }
            else {
                paint.color = Color.RED
                canvas.drawLine(x1.toFloat(), y1.toFloat(), x.toFloat(), y.toFloat(), paintB)
            }
            helper++
            y1= y.toDouble()
            x1= x.toDouble()
        }
    }

    fun getScreenWidth(): Int {
        return Resources.getSystem().displayMetrics.widthPixels
    }

    fun getScreenHeight(): Int {
        return Resources.getSystem().displayMetrics.heightPixels
    }
}
