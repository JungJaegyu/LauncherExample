package com.shurian.launcherexample

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val tag = "MainActivity"

    private val cameraButton: Button by lazy { findViewById(R.id.btn_call_camera) }
    private val galleryButton: Button by lazy { findViewById(R.id.btn_call_gallery) }
    private val audioButton: Button by lazy { findViewById(R.id.btn_call_audio) }
    private val moveActivityButton: Button by lazy { findViewById(R.id.btn_move_activity) }

    private val cameraPermissions = arrayOf(Manifest.permission.CAMERA)
    private val galleryPermissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
    private val audioPermissions = arrayOf(Manifest.permission.RECORD_AUDIO)

    /**
     * 액티비티 런처
     */
    private val activityLauncher = registerForActivityResult(
        StartActivityForResult()
    ) { activityResult: ActivityResult ->
        if (activityResult.resultCode == RESULT_OK) {
            val data = activityResult.data
            val string = data?.getStringExtra("string")
            Log.d(tag, "string: $string")
            Toast.makeText(this, "$string", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * 단일 퍼미션 런처
     */
    private val permissionLauncher = registerForActivityResult(
        RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {

        } else {

        }
    }

    /**
     * 카메라 멀티 퍼미션 런처
     */
    private val cameraPermissionLauncher = registerForActivityResult(
        RequestMultiplePermissions()
    ) {
        if (it.all { permission -> permission.value }) {
            callToCamera(true)
        } else {
            callToCamera(false)
        }
    }

    /**
     * 갤러리 멀티 퍼미션 런처
     */
    private val galleryPermissionLauncher = registerForActivityResult(
        RequestMultiplePermissions()
    ) {
        if (it.all { permission -> permission.value }) {
            callToGallery(true)
        } else {
            callToGallery(false)
        }
    }

    /**
     * 오디오 멀티 퍼미션 런처
     */
    private val audioPermissionLauncher = registerForActivityResult(
        RequestMultiplePermissions()
    ) {
        if (it.all { permission -> permission.value }) {
            callToAudio(true)
        } else {
            callToAudio(false)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    /**
     * 뷰 초기화
     */
    private fun initView() {
        cameraButton.setOnClickListener { cameraPermissionLauncher.launch(cameraPermissions) }

        galleryButton.setOnClickListener { galleryPermissionLauncher.launch(galleryPermissions) }

        audioButton.setOnClickListener { audioPermissionLauncher.launch(audioPermissions) }

        moveActivityButton.setOnClickListener { activityLauncher.launch(Intent(this, SecondActivity::class.java)) }
    }

    /**
     * 카메라 실행
     */
    private fun callToCamera(permission: Boolean) {
        if (permission) {
            Toast.makeText(this, "카메라 실행", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "카메라 권한을 허용해야합니다.", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * 갤러리 실행
     */
    private fun callToGallery(permission: Boolean) {
        if (permission) {
            Toast.makeText(this, "갤러리 실행", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "갤러리 권한을 허용해야합니다.", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * 오디오 실행
     */
    private fun callToAudio(permission: Boolean) {
        if (permission) {
            Toast.makeText(this, "오디오 실행", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "오디오 권한을 허용해야합니다.", Toast.LENGTH_SHORT).show()
        }
    }
}