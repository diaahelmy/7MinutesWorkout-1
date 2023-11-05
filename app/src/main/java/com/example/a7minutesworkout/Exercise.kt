package com.example.a7minutesworkout

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast

import com.example.a7minutesworkout.databinding.ActivityExerciseBinding
import com.example.a7minutesworkout.databinding.DialogCustomBackBinding
import java.util.*
import kotlin.collections.ArrayList

class Exercise : AppCompatActivity(), TextToSpeech.OnInitListener {
    private var coundtowntime: CountDownTimer? = null
    private var coundtowntime2: CountDownTimer? = null

    private var exercises_List: ArrayList<exercises>? = null
    private var currentposition = -1

    lateinit var tts: TextToSpeech
    private var player: MediaPlayer? = null

    private var adapter: exercise_adapter? = null

    var millishin: Long = 11000
    private var pause: Long = 0

    private var pausedown: Long = 0
    var millishin1: Long = 31000

    private var binding: ActivityExerciseBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbarE)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "EXERCISE"


        }
        binding?.toolbarE?.setNavigationOnClickListener {

            customDIalogFORBACKGROUND()
        }
        exercises_List = Constante.defoltexercies()

        tts = TextToSpeech(this, this)
        try {

            val sound =
                Uri.parse("android.resource://com.example.a7minutesworkout/" + R.raw.ringtone_its_for_you_man)
            player = MediaPlayer.create(applicationContext, sound)
            player?.isLooping = false
            player?.start()
        } catch (e: Exception) {

            e.printStackTrace()
        }

        page_change()
        setuprestview()
        puse()
        setupstatues_recycle()

    }

    override fun onBackPressed() {
        customDIalogFORBACKGROUND()

    }

    private fun customDIalogFORBACKGROUND() {
        val custom_dialog = Dialog(this)
        val dialogbinding = DialogCustomBackBinding.inflate(layoutInflater)
        custom_dialog.setContentView(dialogbinding.root)
        custom_dialog.setCanceledOnTouchOutside(false)
        dialogbinding.yes.setOnClickListener {
            this@Exercise.finish()
            custom_dialog.dismiss()
        }
        dialogbinding.no.setOnClickListener {
            custom_dialog.dismiss()
        }
        custom_dialog.show()
    }

    private fun setEXerciseprogresspar(pausel1: Long) {

        coundtowntime2 = object : CountDownTimer(millishin1 - pausel1, 1000) {

            override fun onTick(millisUntilFinished1: Long) {

                pausedown = millishin1 - millisUntilFinished1

                binding?.progBar?.progress = (millisUntilFinished1 / 1000).toInt()
                binding?.number?.text = (millisUntilFinished1 / 1000).toString()
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onFinish() {

                if (currentposition < exercises_List?.size!! - 1) {
                    exercises_List!![this@Exercise.currentposition].set_selected(false)
                    exercises_List!![this@Exercise.currentposition].set_complete(true)
                    adapter!!.notifyDataSetChanged()
                    setuprestview()

                } else {
                    val intent = Intent(this@Exercise, finish::class.java)
                    startActivity(intent)

                }
            }
        }.start()
    }

    private fun setRestProgresspar(pausel: Long) {
//10s
        coundtowntime = object : CountDownTimer(millishin - pausel, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                pause = millishin - millisUntilFinished

                binding?.progBar2?.progress = (millisUntilFinished / 1000).toInt()
                binding?.number2?.text = (millisUntilFinished / 1000).toString()


            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onFinish() {

                currentposition++
                exercises_List!![this@Exercise.currentposition].set_selected(true)

                adapter!!.notifyDataSetChanged()

                setupEXerciseview()

            }

        }.start()
    }

    private fun setupstatues_recycle() {

        adapter = exercise_adapter(exercises_List!!)
        binding?.recylLevel?.adapter = adapter

    }

    private fun setupEXerciseview() {

        with(binding!!) {
            imageButton6?.visibility = View.VISIBLE
            imageButton7?.visibility = View.GONE
            backPage?.visibility = View.VISIBLE
            nextPage?.visibility = View.VISIBLE
            textView3?.visibility = View.INVISIBLE
            frameLayout2?.visibility = View.INVISIBLE
            frameLayout?.visibility = View.VISIBLE
            image?.visibility = View.VISIBLE
            tvnext?.visibility = View.INVISIBLE

            if (coundtowntime != null) {
                coundtowntime?.cancel()
                coundtowntime = null
                pause = 0

            }

            speak(exercises_List!![currentposition].get_name())
            image?.setImageResource(exercises_List!![currentposition].get_image())
            text?.text = exercises_List!![currentposition].get_name()

            setEXerciseprogresspar(pausedown)
        }
    }

    private fun setuprestview() {
        with(binding!!) {
            imageButton6.visibility = View.VISIBLE
            imageButton7.visibility = View.GONE
            textView3.visibility = View.VISIBLE
            frameLayout2.visibility = View.VISIBLE
            frameLayout.visibility = View.INVISIBLE
            image.visibility = View.INVISIBLE
            tvnext.visibility = View.VISIBLE

            if (coundtowntime2 != null) {
                coundtowntime2?.cancel()
                coundtowntime2 = null
                pausedown = 0
            }


            text.text = exercises_List!![currentposition + 1].get_name()

            setRestProgresspar(pause)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (coundtowntime != null) {
            coundtowntime?.cancel()
            pause = 0
        }
        if (coundtowntime2 != null) {
            coundtowntime2?.cancel()
            pausedown = 0
        }
        if (tts != null) {
            tts.stop()
            tts.shutdown()
        }
        if (player!= null)
        {
            player!!.stop()
        }
        binding = null
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_MISSING_DATA ||
                result == TextToSpeech.LANG_NOT_SUPPORTED
            ) Log.e("TTS", "not support lan")
        } else {
            Log.e("TTS", "faild")
        }
    }

    private fun speak(text: String) {

        tts.speak(text, TextToSpeech.QUEUE_ADD, null, "")
    }

    private fun page_change() {

        binding?.nextPage?.setOnClickListener {
            binding?.backPage?.isEnabled = true
            if (coundtowntime != null) {

                coundtowntime!!.cancel()

                coundtowntime = null
                pause = 0
                currentposition++
                if (currentposition < exercises_List?.size!! - 1) {
                    binding?.nextPage?.isEnabled = true
                    binding?.text?.text = exercises_List!![currentposition + 1].get_name()
                    setuprestview()
                } else {

                    val intent = Intent(this@Exercise, finish::class.java)
                    startActivity(intent)

                }
            }

            if (coundtowntime2 != null) {

                exercises_List!![this@Exercise.currentposition].set_selected(false)

                adapter!!.notifyDataSetChanged()

                coundtowntime2!!.cancel()

                coundtowntime2 = null
                pausedown = 0
                currentposition++

                if (currentposition < exercises_List?.size!! - 1) {
                    binding?.nextPage?.isEnabled = true
                    binding?.text?.text = exercises_List!![currentposition + 1].get_name()
                    exercises_List!![this@Exercise.currentposition].set_selected(true)
                    adapter!!.notifyDataSetChanged()
                    setupEXerciseview()
                } else {
                    binding?.nextPage?.isEnabled = false
                    exercises_List!![this@Exercise.currentposition].set_selected(true)
                    adapter!!.notifyDataSetChanged()
                    speak(exercises_List!![currentposition].get_name())
                    binding?.image?.setImageResource(exercises_List!![currentposition].get_image())
                    binding?.text?.text = exercises_List!![currentposition].get_name()
                    setEXerciseprogresspar(pausedown)
                }
            }
        }
        binding?.backPage?.setOnClickListener {
            binding?.nextPage?.isEnabled = true
            if (currentposition == -1) {
                binding?.backPage?.isEnabled = false
            } else {

                if (coundtowntime != null) {
                    coundtowntime!!.cancel()
                    binding?.backPage?.isEnabled = true
                    coundtowntime = null
                    pause = 0
                    binding?.text?.text = exercises_List!![currentposition + 1].get_name()

                    currentposition--
                    setuprestview()
                }
                if (currentposition == 0) {
                    binding?.backPage?.isEnabled = false
                } else {
                    binding?.backPage?.isEnabled = true
                    if (coundtowntime2 != null) {

                        coundtowntime2!!.cancel()
                        exercises_List!![this@Exercise.currentposition].set_selected(false)

                        adapter!!.notifyDataSetChanged()
                        coundtowntime2 = null
                        pausedown = 0
                        currentposition--
                        exercises_List!![this@Exercise.currentposition].set_selected(true)

                        adapter!!.notifyDataSetChanged()
                        binding?.text?.text = exercises_List!![currentposition + 1].get_name()
                        setupEXerciseview()
                    }
                }
            }
        }
    }

    private fun puse() {

        binding?.imageButton6?.setOnClickListener {

            binding?.imageButton6?.visibility = View.GONE
            binding?.imageButton7?.visibility = View.VISIBLE
            if (coundtowntime != null) {
                coundtowntime!!.cancel()

            }

            if (coundtowntime2 != null) {
                coundtowntime2!!.cancel()

            }
        }
        binding?.imageButton7?.setOnClickListener {
            if (coundtowntime != null) {


                binding?.imageButton6?.visibility = View.VISIBLE
                binding?.imageButton7?.visibility = View.GONE
                setRestProgresspar(pause)
            }
            if (coundtowntime2 != null) {


                binding?.imageButton6?.visibility = View.VISIBLE
                binding?.imageButton7?.visibility = View.GONE
                setEXerciseprogresspar(pausedown)

            }
        }


    }

}
