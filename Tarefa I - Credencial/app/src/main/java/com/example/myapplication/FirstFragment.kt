package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController

object ClientRep {
    fun setClient(email: String, cliente: Cliente) {
        clientes.set(email, cliente)
    }
    val clientes: HashMap<String, Cliente> = HashMap()
}

class Cliente(nome: String, senha: String, email: String, disciplina: String, turma: String, tel: String) {
    var nome: String = nome
    var senha: String = senha
    var email: String = email
    var disciplina: String = disciplina
    var turma: String = turma
    var tel: String = tel
}

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.loginBtn).setOnClickListener {
            val email = view.findViewById<EditText>(R.id.emailTextView).text.toString()
            val senha = view.findViewById<EditText>(R.id.senhaTextView).text.toString()

            if (ClientRep.clientes.containsKey(email) && ClientRep.clientes.get(email)?.senha == senha)
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            Toast.makeText(activity, "Email ou senha inválidos!", Toast.LENGTH_SHORT).show()
        }
        view.findViewById<Button>(R.id.registrarBtn).setOnClickListener{
            val intent = Intent(activity, RegisterUser::class.java).apply {}
            startActivity(intent)
        }
    }
}
