package com.example.minlifecalculator

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_select_date.setOnClickListener {

            clickDatePicker()
        }

    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun clickDatePicker() {

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        var dtPicker = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener {view, selected_year, selected_month, selected_dayOfMonth ->

                var selectedDate = "$selected_dayOfMonth/${selected_month+1}/$selected_year"
                set_date_txt?.text = selectedDate

                var sdf = SimpleDateFormat("dd/MM/yyyy")
                val finalDate = sdf.parse(selectedDate) //verificar o erro de parse
                finalDate?.let {
                    val selectDateInMinutes = finalDate.time / 6000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateInMinutes = currentDate.time / 6000
                        val differenceInMinutes = currentDateInMinutes - selectDateInMinutes
                        set_minutes_txt?.text = differenceInMinutes.toString()
                    }

                }

        },
            year,
            month,
            day)

            dtPicker.datePicker.maxDate = System.currentTimeMillis() - 8640000
            dtPicker.show()
    }

}