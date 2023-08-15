package zegel.ipae.proyectofinal.view.gestionCliente.gestionProductos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import zegel.ipae.proyectofinal.R
import zegel.ipae.proyectofinal.contract.ContratoGestionProducto

class EditarProductoActivity : AppCompatActivity(), ContratoGestionProducto.ViewEditProducto{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_producto)
    }
    override fun showEditProducto() {
        TODO("Not yet implemented")
    }
}