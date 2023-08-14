package zegel.ipae.proyectofinal.presenter

import zegel.ipae.proyectofinal.contract.ContratoGestionTrabajador
import zegel.ipae.proyectofinal.data.Trabajador
import javax.inject.Inject

class PresenterGestionTrabajador @Inject constructor(
    private val view: ContratoGestionTrabajador.View,
    private val interactor: ContratoGestionTrabajador.Interactor) : ContratoGestionTrabajador.Presenter{

    override fun obtenerTrabajadores() {
        interactor.obtenerTrabajadores { trabajadores ->
            val usuariosTrabajadores = trabajadores.filter { it.rol == "trabajador" }
            view.showTrabajadores(usuariosTrabajadores)
        }
    }

    override fun editarTrabajadores(trabajador: Trabajador) {
        interactor.editarTrabajadores(trabajador) { success ->
            if (success) {
                // Notificar a la vista que la edición fue exitosa
                view.showEditSuccess()
            } else {
                // Notificar a la vista que la edición falló
                view.showEditFailure()
            }
        }
    }

    override fun eliminarTrabajadores(trabajador: Trabajador) {
        interactor.eliminarTrabajadores(trabajador) { success ->
            if (success) {
                // Notificar a la vista que la eliminación fue exitosa
                view.showDeleteSuccess()
            } else {
                // Notificar a la vista que la eliminación falló
                view.showDeleteFailure()
            }
        }
    }
}