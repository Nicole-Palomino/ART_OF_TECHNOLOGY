package zegel.ipae.proyectofinal.presenter

import zegel.ipae.proyectofinal.contract.ContratoGestionTrabajador
import javax.inject.Inject

class PresenterGestionTrabajador @Inject constructor(
    private val view: ContratoGestionTrabajador.View,
    private val interactor: ContratoGestionTrabajador.Interactor) : ContratoGestionTrabajador.Presenter{

    override fun obtenerTrabajadores() {
        interactor.obtenerTrabajadores { trabajadores ->
            view.showTrabajadores(trabajadores)
        }
    }
}