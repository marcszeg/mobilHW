package com.example.beadando

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import com.example.beadando.databinding.FragmentAppMainBinding


class AppMainFragment : Fragment() {
    private lateinit var binding: FragmentAppMainBinding
    private val myAnswer: MyAnswer = MyAnswer("Hello there!")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //binding = DataBindingUtil.setContentView<FragmentAppMainBinding>(this, R.layout.fragment_app_main)

        //binding = DataBindingUtil.setContentView(this, R.layout.fragment_app_main)
        //setContentView(R.layout.fragment_app_main)                                        // E helyett a binding

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


        //val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager   // Billentyűzet eltüntetése
        //inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }


    private fun updateName (view: View) {
        //val editText = findViewById<EditText>(R.id.nameEdit)
        //val doneButton = findViewById<Button>(R.id.done_button)

        //editText.visibility = View.VISIBLE
        //doneButton.visibility = View.VISIBLE
        view.visibility = View.GONE

        //editText.requestFocus()                                                         // Billentyűzet megjelenítése
        //val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        //imm.showSoftInput(editText, 0)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_app_main, container, false)
    }
}