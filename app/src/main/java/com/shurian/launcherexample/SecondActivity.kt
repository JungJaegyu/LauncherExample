package com.shurian.launcherexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SecondActivity : AppCompatActivity() {
    private val tag = "SecondActivity"

    private val moveBackButton: Button by lazy { findViewById(R.id.btn_move_back) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        moveBackButton.setOnClickListener { moveToBack() }
    }

    /**
     * 돌아가기
     */
    private fun moveToBack() {
        val intent = Intent()
        intent.putExtra("string", "돌아왔숑")
        setResult(RESULT_OK, intent)
        finish()
    }
}