package com.example.blurimage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.blurimage.Worker.BlurWorker
import com.example.blurimage.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val request: OneTimeWorkRequest = OneTimeWorkRequest.Builder(BlurWorker::class.java)
            .build()
        binding.apply {
            click.setOnClickListener {
                WorkManager.getInstance(getApplicationContext()).enqueue(request);
                image2.setImageURI(BlurWorker.outputUri);
            }
        }
    }
}