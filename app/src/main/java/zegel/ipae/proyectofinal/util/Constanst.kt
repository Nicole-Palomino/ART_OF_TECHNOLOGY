package zegel.ipae.proyectofinal.util

object Constantes {
    val DEFAULT_ROL_CLIENTE = "cliente"
    val DEFAULT_ROL_TRABAJADOR = "trabajador"

    const val SHARED_PREFS_NAME = "session_prefs"
    const val RC_GOOGLE_SIGN_IN = 123

    var KEY_USER_NAME: String? = null
    val KEY_USER_EMAIL: String? = null

    private var isGoogleSignIn: Boolean = false
    fun setIsGoogleSignIn(value: Boolean) {
        isGoogleSignIn = value
    }

    fun isGoogleSignIn(): Boolean {
        return isGoogleSignIn
    }
}