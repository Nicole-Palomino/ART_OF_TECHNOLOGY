package zegel.ipae.proyectofinal.view.gestionCliente

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import zegel.ipae.proyectofinal.R
import zegel.ipae.proyectofinal.contract.ContratoGestionCliente

class EditarClienteActivity : AppCompatActivity(), ContratoGestionCliente.ViewEditClient {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_cliente)
    }

    override fun showEditClient() {
        TODO("Not yet implemented")
    }
}