package com.example.urmood.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.urmood.databinding.ActivityTestBinding
import com.example.urmood.ml.Model
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer
import java.nio.ByteOrder

class TestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTestBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val model = Model.newInstance(this)

        binding.btProceed.setOnClickListener {
            val age = binding.etAnswerAge.text.toString().toFloat()
            val city = binding.etAnswerCity.text.toString()
            val occupation = binding.etAnswerOccupation.text.toString()
            val question1 = binding.etAnswer1.text.toString().toFloat()
            val question2 = binding.etAnswer2.text.toString().toFloat()
            val question3 = binding.etAnswer3.text.toString().toFloat()
            val question4 = binding.etAnswer4.text.toString().toFloat()
            val question5 = binding.etAnswer5.text.toString().toFloat()
            val question6 = binding.etAnswer6.text.toString().toFloat()
            val question7 = binding.etAnswer7.text.toString().toFloat()
            val question8 = binding.etAnswer8.text.toString().toFloat()
            val question9 = binding.etAnswer9.text.toString().toFloat()
            val question10 = binding.etAnswer10.text.toString().toFloat()

            val encodedCity = encodeCity(city)
            val encodedOccupation = encodeOccupation(occupation)

            val inputBuffer = ByteBuffer.allocateDirect(1 * 68 * 4)

            inputBuffer.order(ByteOrder.nativeOrder())

            val inputArray = FloatArray(32)

            inputArray[0] = age
            inputArray[1 + encodedCity] = 1.0f
            if (encodedOccupation >= 0 && encodedOccupation < (inputArray.size - 13)) {
                inputArray[13 + encodedOccupation] = 1.0f
            }
            inputArray[3] = question1
            inputArray[4] = question2
            inputArray[5] = question3
            inputArray[6] = question4
            inputArray[7] = question5
            inputArray[8] = question6
            inputArray[9] = question7
            inputArray[10] = question8
            inputArray[11] = question9
            inputArray[12] = question10

            for (i in 13 until inputArray.size) {
                inputArray[i] = 0.0f
            }

            inputBuffer.asFloatBuffer().put(inputArray)

            inputBuffer.rewind()

            val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 68), DataType.FLOAT32)
            inputFeature0.loadBuffer(inputBuffer)

            val outputs = model.process(inputFeature0)
            val outputFeature0 = outputs.outputFeature0AsTensorBuffer
            Log.d("TestActivity", "OutputFeature: $outputFeature0")


            val probabilities = outputFeature0.floatArray
            val maxIndex = probabilities.indices.maxByOrNull { probabilities[it] } ?: -1

            val stressLevels = arrayOf("Light Stress", "Medium Stress", "Heavy Stress")
            val predictedStressLevel = if (maxIndex >= 0 && maxIndex < stressLevels.size) {
                stressLevels[maxIndex]
            } else {
                "Stress levle is Unknown"
            }

            Log.d("TestActivity", "Predicted Stress Level: $predictedStressLevel")
        }
    }

    private fun encodeCity(city: String): Int {
        return when (city) {
            "Bandung" -> 26
            "Malang" -> 17
            "Jakarta" -> 8
            "Yogyakarta" -> 5
            "Klaten" -> 13
            "Banyuwangi" -> 18
            "Bekasi" -> 43
            "Bogor" -> 0
            "Semarang" -> 9
            else -> -1
        }
    }

    private fun encodeOccupation(occupation: String): Int {
        return when (occupation) {
            "Student" -> 39
            "Working" -> 17
            "Not working" -> 43
            else -> -1
        }
    }
}