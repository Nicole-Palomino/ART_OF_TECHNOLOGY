package zegel.ipae.proyectofinal.contract

import zegel.ipae.proyectofinal.data.Trabajador

interface ContratoGestionTrabajador {
    interface View {
        fun showTrabajadores(trabajadores: List<Trabajador>)
    }

    interface Presenter {
        fun obtenerTrabajadores()
    }

    interface Interactor {
        fun obtenerTrabajadores(callback: (List<Trabajador>) -> Unit)
    }
}