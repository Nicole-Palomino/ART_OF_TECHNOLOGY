package zegel.ipae.proyectofinal.view.register

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.AndroidEntryPoint
import zegel.ipae.proyectofinal.R
import zegel.ipae.proyectofinal.contract.ContratoRegistroCliente
import zegel.ipae.proyectofinal.model.InteractorRegistroCliente
import zegel.ipae.proyectofinal.presenter.PresenterCliente
import zegel.ipae.proyectofinal.util.validation.Validaciones
import zegel.ipae.proyectofinal.view.login.LoginActivity

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity(), View.OnClickListener, ContratoRegistroCliente.ViewRegistroCliente, ContratoRegistroCliente.CompleteListener {

    private lateinit var presenter: PresenterCliente
    private lateinit var pass: TextInputEditText
    private lateinit var passConfirm: TextInputEditText
    private lateinit var email: TextInputEditText
    private lateinit var user: TextInputEditText
    private lateinit var txtLogin: TextView
    private lateinit var btnRegister: Button
    private lateinit var dialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        // Ocultar el Action Bar
        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initViews()
    }

    private fun initViews() {
        // inicializar las variables
        txtLogin = findViewById(R.id.txtSession)
        btnRegister = findViewById(R.id.btnNew)
        pass = findViewById(R.id.TextInputPassword)
        passConfirm = findViewById(R.id.TextInputConfirm)
        email = findViewById(R.id.TextInputCorreo)
        user = findViewById(R.id.TextInputUsuario)
    
        presenter = PresenterCliente(this, InteractorRegistroCliente(this))

        btnRegister.setOnClickListener(this)
        txtLogin.setOnClickListener(this)

        dialog = ProgressDialog(this)
        dialog.setMessage("Registrando usuario, espere por favor ...")
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnNew -> {
                // obtener los valores de los inputs
                val userEmail = email.text.toString().trim()
                val userPass = pass.text.toString().trim()
                val userPassConfirm = passConfirm.text.toString().trim()
                val userName = user.text.toString().trim()

                if (!Validaciones.isValidUsername(userName)) {
                    user.error = "Nombre de usuario inválido"
                    return
                }
                if (!Validaciones.isValidEmail(userEmail)) {
                    email.error = "Correo Electrónico inválido"
                    return
                }
                if (!Validaciones.isValidPass(userPass)) {
                    pass.error = "Contraseña inválida"
                    return
                }
                if (!Validaciones.isValidConfirm(userPass, userPassConfirm)) {
                    passConfirm.error = "Contraseña inválida"
                    return
                }

                dialog.show()

                presenter.registerWithEmailAndPassword(this, userEmail,userPass,userName)
            }
            R.id.txtSession -> {
                navigateToLogin()
            }
        }
    }

    override fun onSuccess(firebaseUser: FirebaseUser) {
        dialog.dismiss()
        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
        navigateToLogin()
    }

    override fun onFailure(message: String) {
        dialog.dismiss()
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun onRegistrationSuccess(firebaseUser: FirebaseUser) {
        Toast.makeText(this, "${firebaseUser.email}", Toast.LENGTH_SHORT).show()
    }

    override fun onRegistrationFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}