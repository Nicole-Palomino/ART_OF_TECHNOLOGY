package zegel.ipae.proyectofinal.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import zegel.ipae.proyectofinal.R
import zegel.ipae.proyectofinal.view.menuAdmin.MenuAdminActivity
import zegel.ipae.proyectofinal.view.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // Ocultar el Action Bar
        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val button1 = findViewById<Button>(R.id.btnLogin)
        val text1 = findViewById<TextView>(R.id.txtRegister)

        button1.setOnClickListener {
            startActivity(Intent(this@LoginActivity, MenuAdminActivity::class.java))
            finish()
        }

        text1.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            finish()
        }
    }
}