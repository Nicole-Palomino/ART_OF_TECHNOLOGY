package zegel.ipae.proyectofinal.contract

import zegel.ipae.proyectofinal.data.Trabajador

interface ContratoGestionTrabajador {
    interface View {
        fun showTrabajadores(trabajadores: List<Trabajador>)
        fun showEditSuccess()
        fun showEditFailure()
        fun showDeleteSuccess()
        fun showDeleteFailure()
    }

    interface Presenter {
        fun obtenerTrabajadores()
        fun editarTrabajadores(trabajador: Trabajador)
        fun eliminarTrabajadores(trabajador: Trabajador)
    }

    interface Interactor {
        fun obtenerTrabajadores(callback: (List<Trabajador>) -> Unit)
        fun editarTrabajadores(trabajador: Trabajador, callback: (Boolean) -> Unit)
        fun eliminarTrabajadores(trabajador: Trabajador, callback: (Boolean) -> Unit)
    }

    interface ViewEditTrabajador {
        fun showEditTrabajador()
    }
}