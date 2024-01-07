package com.test.app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.test.androidtemplate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.testText.setOnClickListener {
            onClicked()
        }
    }

    private fun onClicked() {
        try {
            binding.testText.text = NativeMain().stringFromJNI()
        } catch (throwable: Throwable) {
            Log.d("test", "throw: ${throwable.javaClass.simpleName}", throwable)
        }
    }
}