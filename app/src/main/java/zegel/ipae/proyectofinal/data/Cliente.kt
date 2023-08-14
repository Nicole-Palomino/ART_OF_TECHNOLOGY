package zegel.ipae.proyectofinal.data

data class Cliente (
        val uid: String,
        val username: String,
        val correo: String,
        val rol: String
) {
        constructor() : this("", "", "", "")
}