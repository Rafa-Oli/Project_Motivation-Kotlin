package com.rafa.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.rafa.motivation.R
import com.rafa.motivation.infra.MotivationConstants
import com.rafa.motivation.infra.SecurityPreferences
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mSecurityPreferences: SecurityPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        mSecurityPreferences =
            SecurityPreferences(this) // inicializou depois, pq o contexto so começa msm nessa função
        if (supportActionBar != null) {
            supportActionBar!!.hide() //esconde a barrinha que tem o nome do app (Motivation)
        }
        ButtonSave.setOnClickListener(this)

        varifyName()
    }

    private fun varifyName(){
        val name= mSecurityPreferences.getString(MotivationConstants.KEY.PERSON_NAME)
        if(name != ""){
            val intent =Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // retira rastro do splash, isso pq estou navegando e ai retira o splash, tirando a possibilidade de voltar e dizer o nome novamente
        }
    }
    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.ButtonSave) {
            handleSave()
        }
    }

    private fun handleSave() {
        val name = editName.text.toString()
        if (name != "") {
            mSecurityPreferences.storeString(MotivationConstants.KEY.PERSON_NAME, name)

            val intent =Intent(this, MainActivity::class.java)
            startActivity(intent) // funciona para "navegar", ir para uma proxima pagina
            finish()
        } else {
            Toast.makeText(this, "Informe seu nome!", Toast.LENGTH_SHORT).show()
        }
    }
}