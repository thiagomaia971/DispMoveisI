package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController

import kotlinx.android.synthetic.main.activity_register_user.*
import kotlinx.android.synthetic.main.content_register_user.*

class RegisterUser : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)
        supportActionBar?.setTitle(this.getResources().getString(R.string.registar_page_name))

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_register -> {
                val cliente = getClient()
                if (validar(cliente)){
                    registrar(cliente)
                    goToMainActivity()
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java).apply {}
        startActivity(intent)
    }

    private fun validar(cliente: Cliente): Boolean {
        SenhaUser.addTextChangedListener( object: TextWatcher {
            override fun afterTextChanged( content: Editable) {
                SenhaUser.error =
                    if( content.length > 5 )
                        null
                    else
                        "Senha Invalida"
            }
            override fun beforeTextChanged(
                content: CharSequence?,
                start: Int,
                count: Int,
                after: Int ) {}
            override fun onTextChanged(
                content: CharSequence?,
                start: Int,
                before: Int,
                count: Int) {}
        } )

        var isValid = true

        if (cliente.nome.length == 0){
            NomeUser.setError("Por favor preencher campo obrigatório")
            isValid= false
        }
        if (cliente.email.length == 0){
            EmailUser.setError("Por favor preencher campo obrigatório")
            isValid = false
        }
        if (cliente.disciplina.length == 0){
            DisciplinaUser.setError("Por favor preencher campo obrigatório")
            isValid = false
        }
        if (cliente.turma.length == 0){
            TurmaUser.setError("Por favor preencher campo obrigatório")
            isValid = false
        }
        if (rdbMas.isChecked == false && rdbFem.isChecked == false ){
            Toast.makeText(getApplicationContext(), "Selecionar Genero", Toast.LENGTH_SHORT).show()
            isValid = false
        }
        return isValid
    }

    private fun registrar(cliente: Cliente) {
        ClientRep.setClient(cliente.email, cliente)
    }

    private fun getClient(): Cliente {
        val nome = findViewById<EditText>(R.id.NomeUser).text.toString()
        val senha = findViewById<EditText>(R.id.SenhaUser).text.toString()
        val email = findViewById<EditText>(R.id.EmailUser).text.toString()
        val disciplina = findViewById<EditText>(R.id.DisciplinaUser).text.toString()
        val turma = findViewById<EditText>(R.id.TurmaUser).text.toString()
        val tel = findViewById<EditText>(R.id.NumeroUser).text.toString()

        return Cliente(nome, senha, email, disciplina, turma, tel)
    }
}
