package com.example.scorekeeper

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {
    private var mScore1 = 0
    private var mScore2 = 0
    private var mScoreText1: TextView? = null
    private var mScoreText2: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mScoreText1 = findViewById(R.id.score_1)
        mScoreText2 = findViewById(R.id.score_2)

        if (savedInstanceState != null) {
            mScore1 = savedInstanceState.getInt(STATE_SCORE_1)
            mScore2 = savedInstanceState.getInt(STATE_SCORE_2)

            mScoreText1?.text = mScore1.toString()
            mScoreText2?.text = mScore2.toString()
        }
    }

    fun decreaseScore(view: View) {
        val viewID = view.id
        when (viewID) {
            R.id.decreaseTeam1 -> {
                mScore1--

                if (mScore1 < 0) {
                    mScore1 = 0
                    mScoreText1!!.setText(R.string.initial_score)
                } else {
                    mScoreText1!!.text = mScore1.toString()
                }
            }
            R.id.decreaseTeam2 -> {
                mScore2--

                if (mScore2 < 0) {
                    mScore2 = 0
                    mScoreText2!!.setText(R.string.initial_score)
                } else {
                    mScoreText2!!.text = mScore2.toString()
                }
            }
        }
    }

    fun increaseScore(view: View) {
        val viewID = view.id

        when (viewID) {
            R.id.increaseTeam1 -> {
                mScore1++
                mScoreText1!!.text = mScore1.toString()
            }
            R.id.increaseTeam2 -> {
                mScore2++
                mScoreText2!!.text = mScore2.toString()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val nightMode = AppCompatDelegate.getDefaultNightMode()

        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode)
        } else {
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode)
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.night_mode) {
            val nightMode = AppCompatDelegate.getDefaultNightMode()

            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }

            // Recreate activity for changes to take effect
            recreate()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(STATE_SCORE_1, mScore1)
        outState.putInt(STATE_SCORE_2, mScore2)
        super.onSaveInstanceState(outState)
    }

    companion object {
        const val STATE_SCORE_1 = "Team 1 Score"
        const val STATE_SCORE_2 = "Team 2 Score"
    }
}