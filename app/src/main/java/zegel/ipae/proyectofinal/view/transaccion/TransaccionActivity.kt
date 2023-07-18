package zegel.ipae.proyectofinal.view.transaccion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import zegel.ipae.proyectofinal.R
import zegel.ipae.proyectofinal.view.menuAdmin.MenuAdminActivity

class TransaccionActivity : AppCompatActivity() {
    private var listaClientes: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaccion)

        // aparezca el icono de regresar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        listaClientes = findViewById(R.id.listViewId)

        val adaptador = ArrayAdapter.createFromResource(
            this,
            R.array.arrayClientes,
            android.R.layout.simple_list_item_1
        )
        listaClientes?.adapter = adaptador
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            startActivity(Intent(this, MenuAdminActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}