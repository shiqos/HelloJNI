package com.test.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.androidtemplate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.testText.setOnClickListener {
            binding.testText.text = NativeMain().stringFromJNI()
        }
    }
}