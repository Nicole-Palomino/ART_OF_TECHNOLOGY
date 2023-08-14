package zegel.ipae.proyectofinal.view.gestionTrabajador

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import zegel.ipae.proyectofinal.R
import zegel.ipae.proyectofinal.contract.ContratoGestionTrabajador
import zegel.ipae.proyectofinal.data.Trabajador
import zegel.ipae.proyectofinal.model.InteractorGestionTrabajador

class EditarTrabajadorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_trabajador)

        val trabajadorDni = intent.getStringExtra("trabajadorDni")
        val trabajadorNombre = intent.getStringExtra("trabajadorNombre")
        val trabajadorApellido = intent.getStringExtra("trabajadorApellido")
        val trabajadorCorreo = intent.getStringExtra("trabajadorCorreo")
        val trabajadorEstado = intent.getStringExtra("trabajadorEstado")
        val trabajadorRol = intent.getStringExtra("trabajadorRol")
        val trabajadorTelefono = intent.getStringExtra("trabajadorTelefono")

        val nombreEditText = findViewById<EditText>(R.id.TextInputUsuario2)
        val apeEditText = findViewById<EditText>(R.id.TextInputLastName2)
        val emailEditText = findViewById<EditText>(R.id.TextInputCorreo2)
        val estadoEditText = findViewById<EditText>(R.id.TextInputEstado)
        val telEditText = findViewById<EditText>(R.id.TextInputTelefono2)
        val dniEditText = findViewById<EditText>(R.id.TextInputDni2)

        nombreEditText.setText(trabajadorNombre)
        apeEditText.setText(trabajadorApellido)
        emailEditText.setText(trabajadorCorreo)
        estadoEditText.setText(trabajadorEstado)
        telEditText.setText(trabajadorTelefono)
        dniEditText.setText(trabajadorDni)

        val guardarButton = findViewById<Button>(R.id.btnTrabajador)
        guardarButton.setOnClickListener {
            val nuevoTel = telEditText.text.toString()
            val nuevoEstado = estadoEditText.text.toString()

            val trabajadorUid = intent.getStringExtra("trabajadorUid")

            // Lógica para actualizar los campos en Firestore
            val interactor = InteractorGestionTrabajador(applicationContext)
            val nuevoTrabajador = Trabajador(trabajadorUid?: "", trabajadorDni?: "", nuevoTel?: "", trabajadorRol?: "", trabajadorNombre?: "", trabajadorApellido?: "", trabajadorCorreo?: "", nuevoEstado?: "")

            interactor.actualizarTrabajador(nuevoTrabajador)

            mostrarMensajeExitoso("Trabajador actualizado exitosamente")
            navigateToGestion()
        }
    }

    private fun navigateToGestion() {
        startActivity(Intent(this, GestionTrabajadorActivity::class.java))
        finish()
    }

    private fun mostrarMensajeExitoso(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }
}