package com.example.databindingtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.databindingtest.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel2 : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //바인딩 생성
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,
            R.layout.activity_main)

        //liveData를 활용하기 위해서
        binding.lifecycleOwner = this

        //액티비티에 뷰모델 생성
//        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel2


        //databinding으로 지움
//        // UI 갱신
//        viewModel.getAll().observe(this, Observer {
//           binding. resultText.text = it.toString()
//        })

        // 버튼 클릭스 DB에 insert
        binding. addButton.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                viewModel2.insert(Todo(binding.todoEdit.text.toString()))
            }
        }
    }
}