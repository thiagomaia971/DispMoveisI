package Atividade01

fun main (args: Array<String>) {

}

interface Funcionario {
    val nome: String
    var salario: Float
}

class Professor(override val nome: String, override var salario: Float) : Funcionario {
    fun aumentar(percentual: Int){
        this.salario += this.salario * (percentual/100);
    }
}