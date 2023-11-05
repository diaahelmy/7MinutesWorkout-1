package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.a7minutesworkout.databinding.ActivityBimBinding
import java.math.BigDecimal
import java.math.RoundingMode

class ActivityBIM : AppCompatActivity() {
    companion object {
        private const val Metric_unit_view = "Metric_unit_view"
        private const val US_UNIT = "US UNIT"
    }

    private var currentVisibleView: String = Metric_unit_view
    lateinit var binding: ActivityBimBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBimBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarbim)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "CALCULATE BMI"
        }
        binding.toolbarbim.setNavigationOnClickListener {
            onBackPressed()

        }
        veiwMetric_unit()
        binding.groupRadio.setOnCheckedChangeListener { _, checkedId: Int ->
            if (checkedId == R.id.rbmetric_unit) {
                veiwMetric_unit()

            } else {
                veiwUS_unit()

            }

        }



        with(binding) {
            calculate.setOnClickListener {

                calculate()

            }
        }
    }

    private fun vaildmetricunite(): Boolean {
        var isvaled = true
        if (binding.editTextHeight.text!!.toString().isEmpty()) {
            isvaled = false
        } else if (binding.editTextWeight.text!!.toString().isEmpty()) {
            isvaled = false
        }
        return isvaled

    }

    private fun vaildUSunite(): Boolean {
        var isvaled = true
        if (binding.feetUSUnit.text!!.toString().isEmpty()) {
            isvaled = false
        } else if (binding.editTextWeightUSUnit.text!!.toString().isEmpty()) {
            isvaled = false
        } else if (binding.inchUSUnit.text!!.toString().isEmpty()) {
            isvaled = false
        }
        return isvaled

    }

    private fun valuebmi(bmi: Float) {
        val bminame: String
        val bmidescrption: String
        if (bmi.compareTo(15f) <= 0) {
            bminame = "Very severely underweight"

            bmidescrption = "Oops! you really need to take better care of yourself! Eat more!"

        } else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16) <= 0) {
            bminame = "Severely underweight"

            bmidescrption = "Oops! you really need to take better care of yourself! Eat more!"

        } else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5) <= 0) {
            bminame = "Underweight"

            bmidescrption = "Oops! you really need to take better care of yourself! Eat more!"

        } else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25) <= 0) {
            bminame = "Normal"

            bmidescrption = "Congratulation! you are in a good shape!"

        } else if (bmi.compareTo(25f) > 0 && bmi.compareTo(30) <= 0) {
            bminame = "Overweight"

            bmidescrption = "Oops! you really need to take better care of yourself! Workout  "

        } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35) <= 0) {
            bminame = "Obese Class |"

            bmidescrption = "Oops! you really need to take better care of yourself! Workout  "

        } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40) <= 0) {
            bminame = "Obese Class ||"

            bmidescrption = "OMG! you are in a very dangerous condition! Act now! "

        } else {
            bminame = "Obese Class |||"

            bmidescrption = "OMG! you are in a very dangerous condition! Act now! "
        }

        val bmivalue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
        with(binding) {
            linerDisplayValue.visibility = View.VISIBLE
            bmiName.text = bminame
            bmiValue.text = bmivalue
            bimDescription.text = bmidescrption

        }
    }

    private fun veiwUS_unit() {
        currentVisibleView = US_UNIT
        with(binding) {

            feetUSUnit1.visibility = View.VISIBLE
            inchUSUnit1.visibility = View.VISIBLE
            editTextWeight1USUnit.visibility = View.VISIBLE

            editTextWeight1.visibility = View.GONE
            editTextHeight1.visibility = View.GONE
            linerDisplayValue.visibility = View.INVISIBLE

            feetUSUnit.text?.clear()
            editTextWeightUSUnit.text?.clear()
            inchUSUnit.text?.clear()


        }

    }

    private fun veiwMetric_unit() {
        currentVisibleView == Metric_unit_view
        with(binding) {

            feetUSUnit1.visibility = View.GONE
            inchUSUnit1.visibility = View.GONE
            editTextWeight1USUnit.visibility = View.GONE

            linerDisplayValue.visibility = View.INVISIBLE
            editTextWeight1.visibility = View.VISIBLE
            editTextHeight1.visibility = View.VISIBLE

            editTextHeight.text?.clear()
            editTextWeight.text?.clear()


        }

    }

    private fun calculate() {
        with(binding) {
            if (currentVisibleView == Metric_unit_view) {

                if (vaildmetricunite()) {
                    val height: Float = editTextHeight.text.toString().toFloat() / 100

                    val weight: Float = editTextWeight.text.toString().toFloat()

                    val bmi = weight / (height * height)

                    valuebmi(bmi)
                } else {
                    Toast.makeText(this@ActivityBIM, "Please enter valid values",
                        Toast.LENGTH_SHORT)
                        .show()
                }
            } else {

                if (vaildUSunite()) {
                    val feet: Float = feetUSUnit.text.toString().toFloat()
                    val inch: Float = inchUSUnit.text.toString().toFloat()

                    val weight: Float = editTextWeightUSUnit.text.toString().toFloat()

                    val height: Float = inch + feet * 12

                    val bmi = 703 * (weight / (height * height))
                    valuebmi(bmi)
                } else {
                    Toast.makeText(this@ActivityBIM, "Please enter valid values",
                        Toast.LENGTH_SHORT)
                        .show()

                }
            }


        }

    }

}