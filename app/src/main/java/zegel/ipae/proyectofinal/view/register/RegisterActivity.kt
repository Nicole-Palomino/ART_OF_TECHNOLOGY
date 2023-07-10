package zegel.ipae.proyectofinal.view.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import zegel.ipae.proyectofinal.R
import zegel.ipae.proyectofinal.view.login.LoginActivity
import zegel.ipae.proyectofinal.view.menuAdmin.MenuAdminActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Ocultar el Action Bar
        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val text1 = findViewById<TextView>(R.id.txtSession)

        text1.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }
    }
}