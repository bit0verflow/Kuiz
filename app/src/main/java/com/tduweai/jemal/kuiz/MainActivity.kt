package com.tduweai.jemal.kuiz

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.IntegerRes
import android.text.Editable
import android.util.Log
import cn.pedant.SweetAlert.SweetAlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var questions= mutableListOf<QuestionModel>()
    var currentPos=0
    var correct_num=0
    //val engine = ScriptEngineManager().getEngineByExtension("kts")!!
    val operators=arrayOf("+","-",":","*")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //initQuestions()
        //setData()
        initRandomQuestion()
        submit.setOnClickListener({l->checkRandomQuestionAnswer()})
    }
    fun initRandomQuestion(){
        num1.text=(0..100).random().toString()
        operator.text=operators.get((0..(operators.size-1)).random()).toString()
        num2.text=(0..100).random().toString()
    }

    fun checkRandomQuestionAnswer(){
        val number1:Int=num1.text.toString().toInt()
        val oper=operator.text.toString()
        val number2:Int=num2.text.toString().toInt()
        val actual_answer=when(oper){
            "+"->number1+number2
            "-"->number1-number2
            ":"->number1/number2
            "*"->number1*number2
            else->0
        }
        val user_answer=answer.text.toString()
        if(user_answer.equals(actual_answer.toString())){
            SweetAlertDialog(this@MainActivity,SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Good job!")
                    .setContentText("Correct!")
                    .setConfirmClickListener {
                        it.dismiss()
                    }.show()
            Log.d("ANSWER","RIGHT!")
        }else{

            SweetAlertDialog(this@MainActivity,SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Sorry, Answer is incorrect!")
                    .setContentText("Dogry jogap ${actual_answer}")
                    .setConfirmClickListener {
                        it.dismiss()
                    }.show()
            Log.d("ANSWER","WRONG!")
        }
        answer.setText("")
        initRandomQuestion()
    }
    /*
    fun checkAnswer(){
        var user_answer:String=answer.text.trim().toString()
        if(questions.get(currentPos).answer.equals(user_answer)){
            correct_num++
            SweetAlertDialog(this@MainActivity,SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Berekella!")
                    .setContentText("Dogry jogap!")
                    .setConfirmClickListener {
                        it.dismiss()
                    }.show()
            Log.d("ANSWER","RIGHT!")
        }else{

            SweetAlertDialog(this@MainActivity,SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Jogap Yalnysh!")
                    .setContentText("Dogry jogap "+questions.get(currentPos).answer)
                    .setConfirmClickListener {
                        it.dismiss()
                    }.show()
            Log.d("ANSWER","WRONG!")
        }
        currentPos++
        setData()
        answer.setText("")
        var progress_=((currentPos)*100)/questions.size
        progress.progress=progress_
    }
*/
    fun initQuestions(){
        //  engine.eval("${}+")
        //val res = engine.eval("x + 2")
        questions.add(QuestionModel("2+4=?","6"))
        questions.add(QuestionModel("4+4=?","8"))
        questions.add(QuestionModel("6+4=?","10"))
        questions.add(QuestionModel("7+4=?","11"))
        questions.add(QuestionModel("9+6=?","15"))
    }
/*
    fun setData(){

        questionLabel.text=if(currentPos<questions.size){
            questions.get(currentPos).question
        }else{
            currentPos=0
            correct_num=0
            questions.get(currentPos).question
        }
        question_no.text="Sorag NO:${(currentPos+1).toString()}"
        score.text="Bal: $correct_num / ${questions.size}"

    }
*/
}
