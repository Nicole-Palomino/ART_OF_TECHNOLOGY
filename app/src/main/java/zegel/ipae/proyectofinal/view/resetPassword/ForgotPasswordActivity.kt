package zegel.ipae.proyectofinal.view.resetPassword

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import zegel.ipae.proyectofinal.R
import zegel.ipae.proyectofinal.contract.ContratoForgotPassword
import zegel.ipae.proyectofinal.model.InteractorForgotPassword
import zegel.ipae.proyectofinal.presenter.PresenterForgotPassword
import zegel.ipae.proyectofinal.util.validation.Validaciones
import zegel.ipae.proyectofinal.view.login.LoginActivity

class ForgotPasswordActivity : AppCompatActivity(), View.OnClickListener, ContratoForgotPassword.View {

    private lateinit var presenter: PresenterForgotPassword
    private lateinit var email: TextInputEditText
    private lateinit var button: Button
    private lateinit var textView: TextView
    private lateinit var dialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        // Ocultar el Action Bar
        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        initViews()
    }

    private fun initViews() {
        // inicializar las variables
        email = findViewById(R.id.TextInputEmailForgot)
        button = findViewById(R.id.btnForgot)
        textView = findViewById(R.id.txtCancel)

        presenter = PresenterForgotPassword(this, InteractorForgotPassword())

        button.setOnClickListener(this)
        textView.setOnClickListener(this)

        dialog = ProgressDialog(this)
        dialog.setMessage("Enviando correo electrónico, espere por favor ...")
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnForgot -> {
                val userEmail = email.text.toString().trim()

                if (!Validaciones.isValidEmail(userEmail)) {
                    email.error = "Correo Electrónico inválido"
                    return
                }

                dialog.show()
                presenter.resetPassword(userEmail)
            }
            R.id.txtCancel -> {
                navigateToLogin()
            }
        }
    }

    override fun showEmailSentMessage() {
        val successMessage = "Se ha enviado un correo de restablecimiento a tu dirección de correo."
        Toast.makeText(this, successMessage, Toast.LENGTH_LONG).show()
        navigateToLogin()
    }

    override fun showErrorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}