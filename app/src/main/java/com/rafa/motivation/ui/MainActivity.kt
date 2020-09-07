package com.rafa.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.rafa.motivation.R
import com.rafa.motivation.infra.MotivationConstants
import com.rafa.motivation.infra.SecurityPreferences
import com.rafa.motivation.repository.Mock
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mSecurityPreferences: SecurityPreferences
    private var mphraseFilter: Int = MotivationConstants.PHASEFILTER.ALL
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mSecurityPreferences = SecurityPreferences(this)
        val name = mSecurityPreferences.getString(MotivationConstants.KEY.PERSON_NAME) // obteve o nome e atribuiu ao id do nome
        textName.text ="Olá, $name"
             // obteve o nome e atribuiu ao id do nome
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        // logica inical da seleção
        imageAll.setColorFilter(resources.getColor(R.color.colorAccent))
        handleNewPhase()
        ButtonNewPhase.setOnClickListener(this)
        imageAll.setOnClickListener(this)
        imageHappy.setOnClickListener(this)
        imageMorning.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val id = view.id
        val listFilter = listOf(R.id.imageAll, R.id.imageHappy, R.id.imageMorning)
        if (id == R.id.ButtonNewPhase) {
            handleNewPhase()
        } else if (id in listFilter) {
            handleFilter(id)
        }
    }

    private fun handleNewPhase() {
        val phrase = Mock().getPhrase(mphraseFilter)
        textPhase.text = phrase
    }

    private fun handleFilter(id: Int) {
        imageAll.setColorFilter(resources.getColor(R.color.white))
        imageHappy.setColorFilter(resources.getColor(R.color.white))
        imageMorning.setColorFilter(resources.getColor(R.color.white))


        when (id) {
            R.id.imageAll -> {
                imageAll.setColorFilter(resources.getColor(R.color.colorAccent))
                mphraseFilter= MotivationConstants.PHASEFILTER.ALL
            }
            R.id.imageHappy -> {
                imageHappy.setColorFilter(resources.getColor(R.color.colorAccent))
                mphraseFilter= MotivationConstants.PHASEFILTER.HAPPY
            }
            R.id.imageMorning -> {
                imageMorning.setColorFilter(resources.getColor(R.color.colorAccent))
                mphraseFilter= MotivationConstants.PHASEFILTER.MORNING
            }
        }
    }
}