package zegel.ipae.proyectofinal.model

import com.google.firebase.firestore.FirebaseFirestore
import zegel.ipae.proyectofinal.contract.ContratoGestionTrabajador
import zegel.ipae.proyectofinal.data.Trabajador
import javax.inject.Inject

class InteractorGestionTrabajador @Inject constructor(): ContratoGestionTrabajador.Interactor{
    val firestore = FirebaseFirestore.getInstance()
    override fun obtenerTrabajadores(callback: (List<Trabajador>) -> Unit) {
        val trabajadores = mutableListOf<Trabajador>()

        firestore.collection("usuarios")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val trabajador = document.toObject(Trabajador::class.java)
                    trabajadores.add(trabajador)
                }
                callback(trabajadores)
            }
            .addOnFailureListener { exception ->
                // Manejar error
            }
    }

    override fun editarTrabajadores(trabajador: Trabajador, callback: (Boolean) -> Unit) {
        firestore.collection("usuarios")
            .document(trabajador.uid)
            .set(trabajador)
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener { exception ->
                callback(false)
                // Manejar error
            }
    }

    override fun eliminarTrabajadores(trabajador: Trabajador, callback: (Boolean) -> Unit) {
        firestore.collection("usuarios")
            .document(trabajador.uid)
            .delete()
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener { exception ->
                callback(false)
                // Manejar error
            }
    }
}