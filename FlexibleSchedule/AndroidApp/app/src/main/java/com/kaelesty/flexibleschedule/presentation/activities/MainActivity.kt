package com.kaelesty.flexibleschedule.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.kaelesty.flexibleschedule.R
import com.kaelesty.flexibleschedule.databinding.ActivityMainBinding
import com.kaelesty.flexibleschedule.presentation.fragments.edit.EditFragment
import com.kaelesty.flexibleschedule.presentation.fragments.schedule.ScheduleFragment
import com.kaelesty.flexibleschedule.presentation.fragments.select.SelectFragment
import com.kaelesty.flexibleschedule.presentation.fragments.user.UserFragment

class MainActivity : AppCompatActivity() {

	private val FRAGMENT_TAG_SCHEDULE = "Schedule"
	private val FRAGMENT_TAG_USER = "User"
	private val FRAGMENT_TAG_SELECT = "Select"
	private val FRAGMENT_TAG_EDIT = "Edit"

	private val binding by lazy {
		ActivityMainBinding.inflate(layoutInflater)
	}

	private val userFragment = UserFragment.newInstance()
	private val scheduleFragment = ScheduleFragment.newInstance()
	private val editFragment = EditFragment.newInstance()
	private val selectFragment = SelectFragment.newInstance()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

		supportFragmentManager.beginTransaction()
			.replace(R.id.fragment_container, userFragment)
			.commit()

		with(binding) {

			Glide.with(this@MainActivity)
				.load(R.drawable.bg)
				.into(ivBackground)

			buttonSchedule.setOnClickListener {
				supportFragmentManager.beginTransaction()
					.replace(R.id.fragment_container, scheduleFragment)
					.commit()
			}

			buttonSelect.setOnClickListener {
				supportFragmentManager.beginTransaction()
					.replace(R.id.fragment_container, selectFragment)
					.commit()
			}

			buttonEdit.setOnClickListener {
				supportFragmentManager.beginTransaction()
					.replace(R.id.fragment_container, editFragment)
					.commit()
			}

			buttonUser.setOnClickListener {
				supportFragmentManager.beginTransaction()
					.replace(R.id.fragment_container, userFragment)
					.commit()
			}

		}
	}
}