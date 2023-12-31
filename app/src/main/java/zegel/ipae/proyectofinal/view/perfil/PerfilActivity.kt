package zegel.ipae.proyectofinal.view.perfil

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import zegel.ipae.proyectofinal.R
import zegel.ipae.proyectofinal.contract.ContratoForgotPassword
import zegel.ipae.proyectofinal.contract.ContratoGetData
import zegel.ipae.proyectofinal.data.Cliente
import zegel.ipae.proyectofinal.model.InteractorForgotPassword
import zegel.ipae.proyectofinal.model.InteractorGetData
import zegel.ipae.proyectofinal.presenter.PresenterForgotPassword
import zegel.ipae.proyectofinal.presenter.PresenterGetData
import zegel.ipae.proyectofinal.view.login.LoginActivity
import zegel.ipae.proyectofinal.view.menuCliente.MenuClienteActivity

class PerfilActivity : AppCompatActivity(), View.OnClickListener, ContratoGetData.View, ContratoForgotPassword.View {

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var presenterForgotPassword: PresenterForgotPassword
    private lateinit var presenter: PresenterGetData
    private lateinit var textView: TextView
    private lateinit var textView2: TextView
    private lateinit var button: Button
    private lateinit var button2: Button
    private lateinit var dialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        // aparezca el icono de regresar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initViews()
    }

    private fun initViews() {
        textView = findViewById(R.id.txtUserName)
        textView2 = findViewById(R.id.txtUserEmail)
        button = findViewById(R.id.btnResetPass)
        button2 = findViewById(R.id.btnClose)

        presenter = PresenterGetData(this, InteractorGetData())
        presenterForgotPassword = PresenterForgotPassword(this, InteractorForgotPassword())

        button.setOnClickListener(this)
        button2.setOnClickListener(this)

        presenter.loadData()

        // Inicializar googleSignInClient
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        dialog = ProgressDialog(this)
        dialog.setMessage("Enviando correo electrónico, espere por favor ...")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            navigateToMenu()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun navigateToMenu() {
        startActivity(Intent(this, MenuClienteActivity::class.java))
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnResetPass -> {
                val userEmail = textView2.text.toString().trim()

                dialog.show()
                presenterForgotPassword.resetPassword(userEmail)
            }
            R.id.btnClose -> {
                signOut()
            }
        }
    }

    private fun signOut() {
        // cerrar sesion con firebase authentication
        FirebaseAuth.getInstance().signOut()
        // cerrar sesion con google
        googleSignInClient.signOut()

        redirectToLogin()
    }

    private fun redirectToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }

    override fun showUserData(userData: Cliente) {
        textView.text = userData.username
        textView2.text = userData.correo
    }

    override fun showEmailSentMessage() {
        val successMessage = "Se ha enviado un correo de restablecimiento a tu dirección de correo."
        Toast.makeText(this, successMessage, Toast.LENGTH_LONG).show()
    }

    override fun showErrorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}