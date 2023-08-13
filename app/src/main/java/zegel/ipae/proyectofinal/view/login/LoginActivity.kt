package zegel.ipae.proyectofinal.view.login

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import zegel.ipae.proyectofinal.R
import zegel.ipae.proyectofinal.contract.ContratoLoginCliente
import zegel.ipae.proyectofinal.model.InteractorLoginCliente
import zegel.ipae.proyectofinal.presenter.PresenterLoginCliente
import zegel.ipae.proyectofinal.util.Constantes
import zegel.ipae.proyectofinal.util.validation.Validaciones
import zegel.ipae.proyectofinal.view.resetPassword.ForgotPasswordActivity
import zegel.ipae.proyectofinal.view.menuAdmin.MenuAdminActivity
import zegel.ipae.proyectofinal.view.menuCliente.MenuClienteActivity
import zegel.ipae.proyectofinal.view.register.RegisterActivity

class LoginActivity : AppCompatActivity(), View.OnClickListener, ContratoLoginCliente.ViewLoginCliente, ContratoLoginCliente.CompleteLoginCliente {

    private lateinit var presenter: PresenterLoginCliente
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth
    private lateinit var pass: TextInputEditText
    private lateinit var email: TextInputEditText
    private lateinit var btnLogin: Button
    private lateinit var registrar: TextView
    private lateinit var resetPass: TextView
    private lateinit var btnGmail: Button
    private lateinit var dialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        // Ocultar el Action Bar
        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferences = getSharedPreferences(Constantes.SHARED_PREFS_NAME, Context.MODE_PRIVATE)
        initViews()
    }

    private fun initViews() {
        auth = FirebaseAuth.getInstance()
        pass = findViewById(R.id.TextInputPass)
        email = findViewById(R.id.TextInputUser)
        btnLogin = findViewById(R.id.btnLogin)
        registrar = findViewById(R.id.txtRegister)
        resetPass = findViewById(R.id.txtReset)
        btnGmail = findViewById(R.id.btnGmail)

        // Inicializar el googleSignInClient
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        presenter = PresenterLoginCliente(this, InteractorLoginCliente(this))

        btnLogin.setOnClickListener(this)
        registrar.setOnClickListener(this)
        resetPass.setOnClickListener(this)
        btnGmail.setOnClickListener(this)

        dialog = ProgressDialog(this)
        dialog.setMessage("Iniciando Sesión, espere por favor ...")
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnLogin -> {
                val emailUser = email.text.toString().trim()
                val passUser = pass.text.toString().trim()

                if (!Validaciones.isValidEmail(emailUser)) {
                    email.error = "Correo electrónico inválido"
                    return
                }
                if (!Validaciones.isValidPass(passUser)) {
                    pass.error = "Contraseña inválida"
                    return
                }

                dialog.show()
                presenter.login(this, emailUser,passUser, this)
            }
            R.id.txtRegister -> {
                redirectToRegister()
            }
            R.id.btnGmail -> {
                signInWithGoogle()
            }
            R.id.txtReset -> {
                redirectToForgot()
            }
        }
    }

    override fun onSuccess(message: String) {
        dialog.dismiss()
        Toast.makeText(this, message , Toast.LENGTH_LONG).show()
    }

    override fun onFailure(message: String) {
        dialog.dismiss()
        Toast.makeText(this, message , Toast.LENGTH_LONG).show()
    }

    override fun onLoginSuccess(message: String) {
        Toast.makeText(this, message , Toast.LENGTH_LONG).show()
    }

    override fun onLoginFailure(message: String) {
        Toast.makeText(this, message , Toast.LENGTH_LONG).show()
    }

    private fun redirectToMenuCliente() {
        startActivity(Intent(this, MenuClienteActivity::class.java))
        finish()
    }

    private fun redirectToRegister() {
        startActivity(Intent(this, RegisterActivity::class.java))
        finish()
    }

    private fun redirectToForgot() {
        startActivity(Intent(this, ForgotPasswordActivity::class.java))
        finish()
    }

    // inicio de sesion con google
    private fun signInWithGoogle() {
        dialog.show()
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, Constantes.RC_GOOGLE_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Constantes.RC_GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                // Autenticación con Firebase
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Manejar el error
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                dialog.dismiss()

                if (task.isSuccessful) {
                    redirectToMenuCliente()
                } else {
                    Toast.makeText(this, "Error al iniciar sesión con google", Toast.LENGTH_LONG).show()
                }
            }
    }
}