package zegel.ipae.proyectofinal.view.gestionTrabajador

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseUser
import zegel.ipae.proyectofinal.R
import zegel.ipae.proyectofinal.contract.ContratoRegistroCliente
import zegel.ipae.proyectofinal.model.InteractorRegistroWork
import zegel.ipae.proyectofinal.presenter.PresenterRegistroWork
import zegel.ipae.proyectofinal.util.validation.Validaciones

class AddTrabjadorActivity : AppCompatActivity(), View.OnClickListener, ContratoRegistroCliente.ViewRegistroTrabajador, ContratoRegistroCliente.CompleteListenerTrabajador {

    private lateinit var presenter: PresenterRegistroWork
    private lateinit var input: TextInputEditText
    private lateinit var input2: TextInputEditText
    private lateinit var input3: TextInputEditText
    private lateinit var input4: TextInputEditText
    private lateinit var input5: TextInputEditText
    private lateinit var input6: TextInputEditText
    private lateinit var button2: Button
    private lateinit var dialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_trabjador)

        // aparezca el icono de regresar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initViews()
    }

    private fun initViews() {
        input = findViewById(R.id.TextInputDni)
        input2 = findViewById(R.id.TextInputUsuario)
        input3 = findViewById(R.id.TextInputLastName)
        input4 = findViewById(R.id.TextInputCorreo)
        input5 = findViewById(R.id.TextInputPassword)
        input6 = findViewById(R.id.TextInputTelefono)
        button2 = findViewById(R.id.btnTrabajador)

        button2.setOnClickListener(this)

        presenter = PresenterRegistroWork(this, InteractorRegistroWork(this))

        dialog = ProgressDialog(this)
        dialog.setMessage("Registrando trabajador, espere por favor ...")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            startActivity(Intent(this, GestionTrabajadorActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnTrabajador -> {
                val userDni = input.text.toString().trim()
                val userName = input2.text.toString().trim()
                val userLast = input3.text.toString().trim()
                val userEmail = input4.text.toString().trim()
                val userPass = input5.text.toString().trim()
                val userTel = input6.text.toString().trim()

                if (!Validaciones.isValidDni(userDni)) {
                    input.error = "DNI inválido"
                }
                if (!Validaciones.isValidUsername(userName)) {
                    input2.error = "Rellena el campo"
                    return
                }
                if (!Validaciones.isValidLastname(userLast)) {
                    input3.error = "Rellena el campo"
                }
                if (!Validaciones.isValidEmail(userEmail)) {
                    input4.error = "Correo Electrónico inválido"
                    return
                }
                if (!Validaciones.isValidPass(userPass)) {
                    input5.error = "Contraseña inválida"
                    return
                }
                if (!Validaciones.isValidTel(userTel)) {
                    input6.error = "Número de teléfono inválido"
                    return
                }
                dialog.show()

                presenter.registerWithEmailAndPassword(this, userEmail,userPass,userDni,userTel,userName,userLast)
            }
        }
    }

    override fun onRegistrationSuccess(firebaseUser: FirebaseUser) {
        Toast.makeText(this, "${firebaseUser.email}", Toast.LENGTH_SHORT).show()
    }

    override fun onRegistrationFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess(firebaseUser: FirebaseUser) {
        dialog.dismiss()
        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
        navigateToWork()
    }

    override fun onFailure(message: String) {
        dialog.dismiss()
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToWork() {
        startActivity(Intent(this, GestionTrabajadorActivity::class.java))
        finish()
    }
}