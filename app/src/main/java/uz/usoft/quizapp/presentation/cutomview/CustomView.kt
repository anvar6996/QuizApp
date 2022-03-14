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


class CustomView @JvmOverloads constructor(
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


    override fun onDraw(canvas: Canvas) {
        createPath()
        canvas.drawPath(path, paint)
    }

    private fun createPath() {
        path.reset()
        paint.color = Color.parseColor("#1da6f9")
//        path.fillType = Path.FillType.EVEN_ODD
//      path.moveTo(height.toFloat(), height.toFloat())
//      path.lineTo(0f, amplitude.toFloat())
        var i = 0
        var j = true
        while (i < width + 1000000) {
            val wx = i.toFloat()
            val wy = amplitude * 2 + amplitude * sin((i + Math.PI) * Math.PI / 400).toFloat()
            path.lineTo(wy.toFloat(), wx)
            i += 25
        }
        path.close()
    }


    private fun createPathLine(i: Int) {
        path.reset()
        paint.color = Color.parseColor("#1da6f9")
        val wx = i.toFloat()
        val wy = amplitude * 2 + amplitude * sin((i + Math.PI) * Math.PI / 400).toFloat()
        path.lineTo(wy.toFloat(), wx + 50f)
        path.close()
    }

    private fun createPathTransparent(i: Int) {
        path.reset()
        paint.color = Color.parseColor("#00000000")
        val wx = i.toFloat()
        val wy = amplitude * 2 + amplitude * sin((i + Math.PI) * Math.PI / 400).toFloat()
        path.lineTo(wy.toFloat(), wx + 50f)
        path.close()
    }

    companion object {
        const val WAVE_SPEED = 0.3f
        const val WAVE_AMOUNT_ON_SCREEN = 350
    }

    fun getScreenWidth(): Int {
        return Resources.getSystem().displayMetrics.widthPixels
    }

    fun getScreenHeight(): Int {
        return Resources.getSystem().displayMetrics.heightPixels
    }

}