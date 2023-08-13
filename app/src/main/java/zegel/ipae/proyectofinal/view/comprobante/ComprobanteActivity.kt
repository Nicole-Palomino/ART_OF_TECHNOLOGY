package zegel.ipae.proyectofinal.view.comprobante

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import zegel.ipae.proyectofinal.R
import zegel.ipae.proyectofinal.view.menuAdmin.MenuAdminActivity

class ComprobanteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comprobante)

        // aparezca el icono de regresar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            startActivity(Intent(this, MenuAdminActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}