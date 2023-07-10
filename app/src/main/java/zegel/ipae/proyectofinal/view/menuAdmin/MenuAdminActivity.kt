package zegel.ipae.proyectofinal.view.menuAdmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import zegel.ipae.proyectofinal.R

class MenuAdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Ocultar el Action Bar
        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_admin)
    }
}