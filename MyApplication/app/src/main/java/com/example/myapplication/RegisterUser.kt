package com.example.myapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_register_user.*
import kotlinx.android.synthetic.main.content_register_user.*

class RegisterUser : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        //Para validacao da senha
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

        val nome = NomeUser.text.toString()
        val email = EmailUser.text.toString()
        val tel = NumeroUser.text.toString()
        val disciplina = DisciplinaUser.text.toString()
        val turma = TurmaUser.text.toString()
        if (nome.length == 0){NomeUser.setError("Por favor preencher campo obrigatório")}
        if (email.length == 0){EmailUser.setError("Por favor preencher campo obrigatório")}
        if (disciplina.length == 0){DisciplinaUser.setError("Por favor preencher campo obrigatório")}
        if (turma.length == 0){TurmaUser.setError("Por favor preencher campo obrigatório")}
        if (rdbMas.isChecked == false && rdbFem.isChecked == false ){
            Toast.makeText(getApplicationContext(), "Selecionar Genero", Toast.LENGTH_SHORT).show()
        }


    }





}
