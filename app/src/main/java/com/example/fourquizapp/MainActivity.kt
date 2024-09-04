package com.example.fourquizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // 2) タイトルの配列と、選択肢の2次配列（正解、選択肢1、選択肢2、選択肢3の順番）
    private val quizTitle = arrayOf("東京から一番遠い国は？","現代で使われている言語はいくつ？","地球から太陽まで光速で何秒？","人類誕生は何年前？")
    private val quizData = arrayOf(
        arrayOf("アルゼンチン","チリ","ウルグアイ","ペルー"),
        arrayOf("約7000種類","約5000種類","約9000種類","約3000種類"),
        arrayOf("約8分20秒","約8分00秒","約8分40秒","約7分50秒"),
        arrayOf("約30万年前","約20万年前","約40万年前","約10万年前"),
    )
    // 3) カウント変数を用意
    private var i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1) viewの取得
        val tvCount:TextView = findViewById(R.id.tvCount)
        val tvQuestion:TextView = findViewById(R.id.tvQuestion)
        val btn0:Button = findViewById(R.id.btn0)
        val btn1:Button = findViewById(R.id.btn1)
        val btn2:Button = findViewById(R.id.btn2)
        val btn3:Button = findViewById(R.id.btn3)
        val btnNext:Button = findViewById(R.id.btnNext)

        // 4-1) カウント数と、最初の問題を表示
        tvCount.text = "あと4問"
        tvQuestion.text = quizTitle[0]

        // 5) 0~3までのリストを用意→シャッフルして表示
        val list = listOf(0,1,2,3)
        val num = list.shuffled()

        // 4-2) ボタンにquizDataを表示+NEXTボタンは無効化
        btn0.text = quizData[0][num[0]]
        btn1.text = quizData[0][num[1]]
        btn2.text = quizData[0][num[2]]
        btn3.text = quizData[0][num[3]]
        btnNext.isEnabled = false

        // 6) btn0を押した時の正誤判定
        btn0.setOnClickListener {
            if (btn0.text == quizData[i][0]){
                correctAns()
            }else{
                incorrectAns()
            }
        }

        // 8) btn1~3も同じようにする
        btn1.setOnClickListener {
            if (btn1.text == quizData[i][0]){
                correctAns()
            }else{
                incorrectAns()
            }
        }

        btn2.setOnClickListener {
            if (btn2.text == quizData[i][0]){
                correctAns()
            }else{
                incorrectAns()
            }
        }

        btn3.setOnClickListener {
            if (btn3.text == quizData[i][0]){
                correctAns()
            }else{
                incorrectAns()
            }
        }

        // 9) NEXTボタンを押したらi問目を表示する
        btnNext.setOnClickListener {
            i++
            //もう一回シャッフル
            val numNext = list.shuffled()
            //i問目のタイトルと問題を表示
            tvCount.text = "あと" + (4-i) + "問"
            tvQuestion.text = quizTitle[i]
            btn0.text = quizData[i][numNext[0]]
            btn1.text = quizData[i][numNext[1]]
            btn2.text = quizData[i][numNext[2]]
            btn3.text = quizData[i][numNext[3]]
            //ボタンを有効化
            btn0.isEnabled = true
            btn1.isEnabled = true
            btn2.isEnabled = true
            btn3.isEnabled = true
            btnNext.isEnabled = false
        }

    }

    // 7) 正解の関数、不正解の関数を作ってまとめる

    private fun incorrectAns(){
        //不正解＋ボタン無効化
        val tvQuestion:TextView = findViewById(R.id.tvQuestion)
        val btn0:Button = findViewById(R.id.btn0)
        val btn1:Button = findViewById(R.id.btn1)
        val btn2:Button = findViewById(R.id.btn2)
        val btn3:Button = findViewById(R.id.btn3)
        val btnNext:Button = findViewById(R.id.btnNext)

        tvQuestion.text = "不正解！！Game Over"
        btn0.isEnabled = false
        btn1.isEnabled = false
        btn2.isEnabled = false
        btn3.isEnabled = false
        btnNext.isEnabled = false
    }

    private fun correctAns(){
        //正解アラートダイアログ
        val tvQuestion:TextView = findViewById(R.id.tvQuestion)
        val btn0:Button = findViewById(R.id.btn0)
        val btn1:Button = findViewById(R.id.btn1)
        val btn2:Button = findViewById(R.id.btn2)
        val btn3:Button = findViewById(R.id.btn3)
        val btnNext:Button = findViewById(R.id.btnNext)

        // 10) 全問正解したらGameclear画面へ
        if (i == 3){
            val intent = Intent(this,MainActivity2::class.java)
            startActivity(intent)
            finish()
        }else{
            AlertDialog.Builder(this)
                .setTitle("正解！！")
                .setMessage(quizData[i][0])
                .setPositiveButton("OK",null)
                .show()
            btn0.isEnabled = false
            btn1.isEnabled = false
            btn2.isEnabled = false
            btn3.isEnabled = false
            btnNext.isEnabled = true
        }

    }

}