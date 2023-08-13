package zegel.ipae.proyectofinal.util.validation

object Validaciones {
    fun isValidUsername(username: String): Boolean {
        return username.isNotBlank()
    }

    fun isValidEmail(email: String): Boolean {
        return email.isNotBlank() && email.contains("@") && email.contains(".")
    }

    fun isValidPass(pass: String): Boolean {
        return  pass.isNotBlank() && pass.length >= 8
    }

    fun isValidConfirm(pass: String, confirm: String): Boolean {
        return confirm.isNotBlank() && pass == confirm
    }
}