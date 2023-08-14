package zegel.ipae.proyectofinal.view.gestionTrabajador

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import zegel.ipae.proyectofinal.R
import zegel.ipae.proyectofinal.contract.ContratoGestionTrabajador

class EditarTrabajadorActivity : AppCompatActivity(), ContratoGestionTrabajador.ViewEditTrabajador {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_trabajador)
    }

    override fun showEditTrabajador() {
        TODO("Not yet implemented")
    }
}