package zegel.ipae.proyectofinal.data

data class Producto (
    val uid: String,
    val nombre: String,
    val precio: Double,
    val stock: Int
){
    constructor() : this("", "", 0.0,0)
}