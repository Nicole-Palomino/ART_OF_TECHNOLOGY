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
<<<<<<< HEAD
            val usuariosTrabajadores = trabajadores.filter { it.rol == "trabajador" }
            view.showTrabajadores(usuariosTrabajadores)
=======
            val usuariosTrabajdores = trabajadores.filter { it.rol == "trabajador" }

            view.showTrabajadores(usuariosTrabajdores)
>>>>>>> 14684140d01f16a609f8b59c7798d13ff31e5b19
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