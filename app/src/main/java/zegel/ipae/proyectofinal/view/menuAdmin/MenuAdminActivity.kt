package zegel.ipae.proyectofinal.view.menuAdmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.cardview.widget.CardView
import zegel.ipae.proyectofinal.R
import zegel.ipae.proyectofinal.view.comprobante.ComprobanteActivity

class MenuAdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Ocultar el Action Bar
        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_admin)

        val button = findViewById<CardView>(R.id.cardSales)

        button.setOnClickListener {
            startActivity(Intent(this@MenuAdminActivity, ComprobanteActivity::class.java))
        }
    }
}