package zegel.ipae.proyectofinal.view.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import zegel.ipae.proyectofinal.R
import zegel.ipae.proyectofinal.util.animations.Animations
import zegel.ipae.proyectofinal.view.login.LoginActivity
import zegel.ipae.proyectofinal.view.register.RegisterActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Ocultar el Action Bar
        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // animacion a la imagen
        val imageView = findViewById<ImageView>(R.id.logo)
        Animations.slideLeft(this, imageView)

        val button1 = findViewById<Button>(R.id.btnHome)
        val button2 = findViewById<Button>(R.id.btnCreate)

        button1.setOnClickListener {
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            finish()
        }

        button2.setOnClickListener {
            startActivity(Intent(this@MainActivity, RegisterActivity::class.java))
            finish()
        }


    }
}