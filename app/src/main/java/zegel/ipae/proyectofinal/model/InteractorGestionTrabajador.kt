package zegel.ipae.proyectofinal.model

import android.content.Context
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import zegel.ipae.proyectofinal.contract.ContratoGestionTrabajador
import zegel.ipae.proyectofinal.data.Trabajador
import javax.inject.Inject

class InteractorGestionTrabajador @Inject constructor(
    private val context: Context
): ContratoGestionTrabajador.InteractorList{

    private val firestore = FirebaseFirestore.getInstance()

    override fun obtenerTrabajadores(callback: (List<Trabajador>) -> Unit) {
        val trabajadores = mutableListOf<Trabajador>()

        firestore.collection("usuarios")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val user = document.toObject(Trabajador::class.java)
                    trabajadores.add(user)
                }
                callback(trabajadores)
            }
            .addOnFailureListener { exception ->
                // Manejar error
            }
    }

    override fun actualizarTrabajador(
        trabajador: Trabajador
    ) {
        val trabajadorRef = firestore.collection("usuarios").document(trabajador.uid)

        val datosActualizados = mapOf(
            "dni" to trabajador.dni,
            "tel" to trabajador.tel,
            "rol" to trabajador.rol,
            "nombre" to trabajador.nombre,
            "apellido" to trabajador.apellido,
            "correo" to trabajador.correo,
            "estado" to trabajador.estado,
            "uid" to trabajador.uid
        )

        trabajadorRef.update(datosActualizados)
            .addOnSuccessListener {
                mostrarMensajeExitoso("Trabajador actualizado exitosamente")
            }
            .addOnFailureListener { exception ->
                mostrarMensajeError("Error al actualizar trabajador: ${exception.message}")
            }
    }

    private fun mostrarMensajeExitoso(mensaje: String) {
        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
    }

    private fun mostrarMensajeError(mensaje: String) {
        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
    }
}