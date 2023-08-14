package zegel.ipae.proyectofinal.data

data class Trabajador (
    val uid: String,
    val dni: String,
    val tel: String,
    val rol: String,
    val nombre: String,
    val apellido: String,
    val correo: String,
    val contrasena: String
) {
    constructor() : this("", "", "", "", "", "", "", "")
}