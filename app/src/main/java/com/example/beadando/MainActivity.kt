package com.example.beadando

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.beadando.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    /*private lateinit var binding: ActivityMainBinding
    private val myAnswer: MyAnswer = MyAnswer("Hello there!")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //setContentView(R.layout.activity_main)                                        // E helyett a binding

        binding.myAnswer = myAnswer

        binding.doneButton.setOnClickListener { addName(it) }
        //findViewById<Button>(R.id.done_button).setOnClickListener { addName(it) }   // Gombnyomásra hívja fv-t

        binding.answerText.setOnClickListener { updateName(it) }
        //findViewById<TextView>(R.id.answerText).setOnClickListener { updateName(it) }   // TextView-ra bökéssel visszajön az input
    }

    private fun addName(view: View) {
        //val editText = findViewById<EditText>(R.id.nameEdit)
        //val answerTextView = findViewById<TextView>(R.id.answerText)

        //answerTextView.text = "General " + editText.text + "!"
        //editText.visibility = View.GONE
        //view.visibility = View.GONE
        //answerTextView.visibility = View.VISIBLE
        binding.apply {
            //answerText.text = "General " + nameEdit.text.toString() + "!"
            myAnswer?.answer = "General " + nameEdit.text.toString() + "!"
            invalidateAll()
            nameEdit.visibility = View.GONE
            doneButton.visibility = View.GONE
            answerText.visibility = View.VISIBLE
        }


        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager   // Billentyűzet eltüntetése
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }


    private fun updateName (view: View) {
        val editText = findViewById<EditText>(R.id.nameEdit)
        val doneButton = findViewById<Button>(R.id.done_button)

        editText.visibility = View.VISIBLE
        doneButton.visibility = View.VISIBLE
        view.visibility = View.GONE

        editText.requestFocus()                                                         // Billentyűzet megjelenítése
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, 0)
    }*/

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNUSED_VARIABLE")
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        drawerLayout = binding.drawerLayout
        val navController = this.findNavController(R.id.navHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.navHostFragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }
}