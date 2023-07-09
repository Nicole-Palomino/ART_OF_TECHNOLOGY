package zegel.ipae.proyectofinal.util.animations

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import zegel.ipae.proyectofinal.R

object Animations {
    fun slideLeft(context: Context, view: View) {
        val animation = AnimationUtils.loadAnimation(context, R.anim.desplazamiento_izquierda)
        view.startAnimation(animation)
    }
}