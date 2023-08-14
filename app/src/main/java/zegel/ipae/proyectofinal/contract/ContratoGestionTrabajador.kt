package zegel.ipae.proyectofinal.contract

import zegel.ipae.proyectofinal.data.Trabajador

interface ContratoGestionTrabajador {
    interface ViewList {
        fun showTrabajadores(trabajadores: List<Trabajador>)
    }

    interface PresenterList {
        fun obtenerTrabajadores()
        fun editarTrabajador(trabajador: Trabajador)
    }

    interface InteractorList {
        fun obtenerTrabajadores(callback: (List<Trabajador>) -> Unit)
        fun actualizarTrabajador(trabajador: Trabajador)
    }
}