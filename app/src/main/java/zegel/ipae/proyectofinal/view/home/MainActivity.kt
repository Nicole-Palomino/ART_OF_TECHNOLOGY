package zegel.ipae.proyectofinal.view.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import zegel.ipae.proyectofinal.R
import zegel.ipae.proyectofinal.util.animations.Animations
import zegel.ipae.proyectofinal.view.login.LoginActivity
import zegel.ipae.proyectofinal.view.register.RegisterActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        // Ocultar el Action Bar
        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        // animacion a la imagen
        imageView = findViewById(R.id.logo)
        Animations.slideLeft(this, imageView)

        // inicialización de los botones
        button1 = findViewById(R.id.btnHome)
        button2 = findViewById(R.id.btnCreate)

        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnHome -> {
                navigateToLogin()
            }
            R.id.btnCreate -> {
                navigateToRegister()
            }
        }
    }

    private fun navigateToLogin() {
        startActivity(Intent(this@MainActivity, LoginActivity::class.java))
        finish()
    }

    private fun navigateToRegister() {
        startActivity(Intent(this@MainActivity, RegisterActivity::class.java))
        finish()
    }
}