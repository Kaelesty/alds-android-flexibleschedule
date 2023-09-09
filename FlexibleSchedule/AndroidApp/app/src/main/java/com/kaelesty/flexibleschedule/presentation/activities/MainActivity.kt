package com.kaelesty.flexibleschedule.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kaelesty.flexibleschedule.R
import com.kaelesty.flexibleschedule.databinding.ActivityMainBinding
import com.kaelesty.flexibleschedule.presentation.fragments.EditFragment
import com.kaelesty.flexibleschedule.presentation.fragments.ScheduleFragment
import com.kaelesty.flexibleschedule.presentation.fragments.SelectFragment
import com.kaelesty.flexibleschedule.presentation.fragments.UserFragment

class MainActivity : AppCompatActivity() {

	private val FRAGMENT_TAG_SCHEDULE = "Schedule"
	private val FRAGMENT_TAG_USER = "User"
	private val FRAGMENT_TAG_SELECT = "Select"
	private val FRAGMENT_TAG_EDIT = "Edit"

	private val binding by lazy {
		ActivityMainBinding.inflate(layoutInflater)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

		with(binding) {

			imageSchedule.setOnClickListener {
				Log.d("MainActivity", "Clicked!")
				supportFragmentManager.beginTransaction()
					.add(R.id.fragment_container, ScheduleFragment.newInstance(), FRAGMENT_TAG_SCHEDULE)
					.commit()
			}

			buttonSelect.setOnClickListener {
				supportFragmentManager.beginTransaction()
					.add(R.id.fragment_container, SelectFragment.newInstance(), FRAGMENT_TAG_SELECT)
					.commit()
			}

			buttonEdit.setOnClickListener {
				supportFragmentManager.beginTransaction()
					.add(R.id.fragment_container, EditFragment.newInstance(), FRAGMENT_TAG_EDIT)
					.commit()
			}

			buttonUser.setOnClickListener {
				supportFragmentManager.beginTransaction()
					.add(R.id.fragment_container, UserFragment.newInstance(), FRAGMENT_TAG_USER)
					.commit()
			}

		}
	}
}