package com.example.conversion

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val spinner = findViewById<Spinner>(R.id.item)
        val enter = findViewById<EditText>(R.id.enterno)
        val textView = findViewById<TextView>(R.id.result)
        val btn = findViewById<Button>(R.id.button)
        val adapter = ArrayAdapter.createFromResource(this, R.array.option, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        btn.setOnClickListener {
            val input = enter.text.toString()
            val selectedOption = spinner.selectedItem.toString()
            when (selectedOption) {
                "decimal" -> {
                    val decimal = input.toInt()
                    textView.text = "Binary: ${decimalToBinary(decimal)}\n" +
                            "Octal: ${decimalToOctal(decimal)}\n" +
                            "Hexadecimal: ${decimalToHexadecimal(decimal)}"
                }
                "binary" -> {
                    val binary = input
                    textView.text = "Decimal: ${binaryToDecimal(binary)}\n" +
                            "Octal: ${binaryToOctal(binary)}\n" +
                            "Hexadecimal: ${binaryToHexadecimal(binary)}"
                }
                "hexadecimal" -> {
                    val hex = input
                    textView.text = "Decimal: ${hexadecimalToDecimal(hex)}\n" +
                            "Octal: ${hexadecimalToOctal(hex)}\n" +
                            "Binary: ${hexadecimalToBinary(hex)}"
                }
                "octal" -> {
                    val octal = input
                    textView.text = "Decimal: ${octalToDecimal(octal)}\n" +
                            "Binary: ${octalToBinary(octal)}\n" +
                            "Hexadecimal: ${octalToHexadecimal(octal)}"
                }
            }
        }
    }

    // Decimal
    fun decimalToBinary(num: Int): String {
        return Integer.toBinaryString(num)
    }

    fun decimalToOctal(num: Int): String {
        return Integer.toOctalString(num)
    }

    fun decimalToHexadecimal(num: Int): String {
        return Integer.toHexString(num)
    }

    // Binary
    fun binaryToDecimal(num: String): String {
        return Integer.parseInt(num, 2).toString()
    }

    fun binaryToOctal(num: String): String {
        val decimal = Integer.parseInt(num, 2)
        return Integer.toOctalString(decimal)
    }

    fun binaryToHexadecimal(num: String): String {
        val decimal = Integer.parseInt(num, 2)
        return Integer.toHexString(decimal)
    }

    // Hexadecimal
    fun hexadecimalToOctal(num: String): String {
        val decimal = Integer.parseInt(num, 16)
        return Integer.toOctalString(decimal)
    }

    fun hexadecimalToBinary(num: String): String {
        val decimal = Integer.parseInt(num, 16)
        return Integer.toBinaryString(decimal)
    }

    fun hexadecimalToDecimal(num: String): String {
        return Integer.parseInt(num, 16).toString()
    }

    // Octal
    fun octalToBinary(num: String): String {
        val decimal = Integer.parseInt(num, 8)
        return Integer.toBinaryString(decimal)
    }

    fun octalToDecimal(num: String): String {
        return Integer.parseInt(num, 8).toString()
    }

    fun octalToHexadecimal(num: String): String {
        val decimal = Integer.parseInt(num, 8)
        return Integer.toHexString(decimal)
    }
}