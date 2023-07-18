package zegel.ipae.proyectofinal.view.transaccion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import zegel.ipae.proyectofinal.R

class TransaccionActivity : AppCompatActivity() {
    private var listaClientes: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaccion)

        listaClientes = findViewById(R.id.listViewId)

        val adaptador = ArrayAdapter.createFromResource(
            this,
            R.array.arrayClientes,
            android.R.layout.simple_list_item_1
        )
        listaClientes?.adapter = adaptador
    }
}