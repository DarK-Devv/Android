package com.example.e05_sumcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun numberInput(view: View) {
        // view is a button (pressed one) get text and convert to Int
        val digit = (view as Button).text.toString().toInt()
        // append a new string to textView
        textView.append(digit.toString())
    }

    fun clearButton(view: View) {
        textView.text = ("")
    }

    fun plusClicked(view: View) {
        textView.append("+")
    }

    fun minusClicked(view: View) {
        textView.append("-")
    }

    fun printResult(view: View) {
        val text = textView.text.toString()
        var numberStr = ""
        var result = 0

        for ( i in 0 until text.length ) {

            val c = text[i]

            if ( c in '0'..'9' ) {

                numberStr += c

                if ( i == text.length - 1 ) {

                    result += numberStr.toInt()
                }
            }
            else if ( !numberStr.isNullOrBlank() ) {

                result += numberStr.toInt()

                numberStr = ""
            }
        }

        textView.text = ("")
        textView.append(result.toString())

    }
}