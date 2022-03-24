package uz.usoft.quizapp.utils.navigate

import android.animation.Animator
import android.view.View
import android.view.animation.Animation

fun View.startAnimation(animation: Animation, onEnd: () -> Unit) {
    animation.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(p0: Animation?) {

        }

        override fun onAnimationEnd(p0: Animation?) {
            onEnd( )
        }

        override fun onAnimationRepeat(p0: Animation?) = Unit

    })
    this.startAnimation(animation)
}