package com.example.linearlayout

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.EditText
import android.widget.Toast
import com.example.linearlayout.database.BancoDeDadosHelper
import com.example.linearlayout.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var database: BancoDeDadosHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar o binding
        // Inflate: Criar a view a partir do Layout
        binding = ActivityLoginBinding.inflate(layoutInflater)

        database = BancoDeDadosHelper(this)
        database.readableDatabase

        enableEdgeToEdge()
        // Carrego minha interface a partir do objeto binding
        setContentView(binding.main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left + binding.main.paddingStart,
                systemBars.top,
                systemBars.right + binding.main.paddingEnd,
                systemBars.bottom
            )
            insets
        }
        // Usar camel case
        // snake_case - separo_as_palavras_com_underscore
        // camelCase - cadaPalavraComecaComMaiuscula
        binding.buttonLogin.setOnClickListener(this)
    }

    override fun onClick(view: View){
        when(view.id){
            binding.buttonLogin.id -> { // ou R.id.buttonLogin
                // Validação de campos vazios
                val emailText: String = binding.edittextEmail.text.toString()
                val passwordText: String = binding.edittextPassword.text.toString()

                if (emailText.isNotEmpty() && passwordText.isNotEmpty()) {
                    // Prossegue para fazer o processo de login
                    // ::class.java -> Referencia
                    val intent = Intent(this, HomeActivity::class.java)
                    val bundle = Bundle()
                    bundle.putString("EMAIL_KEY", emailText)
//                    bundle.putString("PASSWORD_KEY", passwordText)
                    intent.putExtras(bundle)
                    //intent.putExtra("EMAIL_KEY", emailText)
                    //intent.putExtra("PASSWORD_KEY", passwordText)
                    startActivity(intent)
                } else {
                    // Mandar um alerta...
                    // Primeiro parâmetro -> Contexto:
                    // - Recursos
                    // - Estados
                    // - Funcionamento
                    // Segundo parâmetro -> Mensagem
                    // Terceiro parâmetro -> Duração
                    // - Short
                    // - Long
                    Toast.makeText(this, R.string.login_empty_field, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}