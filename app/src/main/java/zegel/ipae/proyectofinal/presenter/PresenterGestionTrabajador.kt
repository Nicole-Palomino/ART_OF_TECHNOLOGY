package zegel.ipae.proyectofinal.presenter

import android.content.Context
import android.content.Intent
import zegel.ipae.proyectofinal.contract.ContratoGestionTrabajador
import zegel.ipae.proyectofinal.data.Trabajador
import zegel.ipae.proyectofinal.view.gestionTrabajador.EditarTrabajadorActivity
import javax.inject.Inject

class PresenterGestionTrabajador @Inject constructor(
    private val view: ContratoGestionTrabajador.ViewList,
    private val interactor: ContratoGestionTrabajador.InteractorList,
    private val context: Context) : ContratoGestionTrabajador.PresenterList {

    override fun obtenerTrabajadores() {
        interactor.obtenerTrabajadores { trabajadores ->
            val usuariosTrabajdores = trabajadores.filter { it.rol == "trabajador" }

            view.showTrabajadores(usuariosTrabajdores)
        }
    }

    override fun editarTrabajador(trabajador: Trabajador) {
        val intent = Intent(context, EditarTrabajadorActivity::class.java)
        intent.putExtra("trabajadorUid", trabajador.uid)
        intent.putExtra("trabajadorNombre", trabajador.nombre)
        intent.putExtra("trabajadorApellido", trabajador.apellido)
        intent.putExtra("trabajadorDni", trabajador.dni)
        intent.putExtra("trabajadorTelefono", trabajador.tel)
        intent.putExtra("trabajadorEstado", trabajador.estado)
        intent.putExtra("trabajadorRol", trabajador.rol)
        intent.putExtra("trabajadorCorreo", trabajador.correo)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}