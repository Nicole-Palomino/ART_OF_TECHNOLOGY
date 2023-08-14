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
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import zegel.ipae.proyectofinal.R
import zegel.ipae.proyectofinal.contract.ContratoForgotPassword
import zegel.ipae.proyectofinal.contract.ContratoGetData
import zegel.ipae.proyectofinal.data.Cliente
import zegel.ipae.proyectofinal.data.Trabajador
import zegel.ipae.proyectofinal.model.InteractorForgotPassword
import zegel.ipae.proyectofinal.model.InteractorGetData
import zegel.ipae.proyectofinal.model.InteractorGetDataWork
import zegel.ipae.proyectofinal.presenter.PresenterForgotPassword
import zegel.ipae.proyectofinal.presenter.PresenterGetData
import zegel.ipae.proyectofinal.presenter.PresenterGetDataWork
import zegel.ipae.proyectofinal.view.login.LoginActivity
import zegel.ipae.proyectofinal.view.menuAdmin.MenuAdminActivity

class PerfilTrabajadorActivity : AppCompatActivity(), View.OnClickListener, ContratoGetData.ViewTrabajador {

    private lateinit var presenter: PresenterGetDataWork
    private lateinit var textView: TextView
    private lateinit var textView2: TextView
    private lateinit var button: Button
    private lateinit var button2: Button
    private lateinit var dialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_trabajador)

        // aparezca el icono de regresar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initViews()
    }

    private fun initViews() {
        textView = findViewById(R.id.txtUserName)
        textView2 = findViewById(R.id.txtUserEmail)
        button = findViewById(R.id.btnClose)

        presenter = PresenterGetDataWork(this, InteractorGetDataWork())

        button.setOnClickListener(this)

        presenter.loadData()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            navigateToMenu()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnClose -> {
                signOut()
            }
        }
    }

    private fun signOut() {
        // cerrar sesion con firebase authentication
        FirebaseAuth.getInstance().signOut()

        redirectToLogin()
    }

    private fun redirectToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }

    override fun showUserData(userData: Trabajador) {
        textView.text = userData.nombre
        textView2.text = userData.correo
    }

    override fun showErrorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun navigateToMenu() {
        startActivity(Intent(this, MenuAdminActivity::class.java))
    }
}