package Atividade01

import Atividade02.*

fun main (args: Array<String>) {
//    val vetor: Array<Funcionario> = Array<Funcionario>(2) {
//        Professor("Professor1", 100f);
//        Funcionario("Funcionario1", 50f);
//    }
    val vetor = listOf(
        Professor("Professor1", 100f),
        Funcionario("Funcionario1", 50f))

    for (item: Funcionario in vetor){
        item.aumentar(2f)
    }
//
//    var professor: Professor = Professor("Professor1", 100f);
//    professor.aumentar(2f);
//
//    var funcionario = Funcionario("Funcionario1", 50f)
//    funcionario.aumentar(10f)
//
//    println(funcionario is Funcionario)
//    println(funcionario is Professor)
//    println(professor is Funcionario)
//    println(professor is Professor)
}

open class Funcionario {
    val nome: String
    var salario: Float

    constructor(nome: String, salario: Float) {
        this.nome = nome
        this.salario = salario
    }

    open fun aumentar(percentual: Float){
        println("Funcionario Antes: " + this.salario)
        this.salario = this.salario + (this.salario * (percentual/100));
        println("Funcionario Depois: " + this.salario)
        println()
    }
}

class Professor(nome: String, salario: Float) : Funcionario(nome, salario) {
    override fun aumentar(percentual: Float){
        println("Professor Antes: " + this.salario)
        this.salario = this.salario + (this.salario * (percentual/100));
        println("Professor Depois: " + this.salario)
    }
}