package zegel.ipae.proyectofinal.view.gestionCliente

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import zegel.ipae.proyectofinal.R
import zegel.ipae.proyectofinal.view.menuAdmin.MenuAdminActivity

class GestionClienteActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestion_cliente)

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
