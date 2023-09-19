package com.kaelesty.flexibleschedule.presentation.fragments.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kaelesty.flexibleschedule.databinding.FragmentEditBinding
import com.kaelesty.flexibleschedule.presentation.fragments.views.DayView

class EditFragment : Fragment() {

	private val binding by lazy {
		FragmentEditBinding.inflate(layoutInflater)
	}

	private val adapter by lazy {
		DayEditRecyclerAdapter()
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		adapter.days = mutableListOf(
			DayView("Понедельник", mutableListOf()),
			DayView("Вторник", mutableListOf()),
			DayView("Среда", mutableListOf()),
			DayView("Четверг", mutableListOf()),
			DayView("Пятница", mutableListOf()),
			DayView("Суббота", mutableListOf()),
			DayView("Воскресенье", mutableListOf()),
		)

		with(binding) {
			recyclerDays.adapter = adapter
		}
	}

	companion object {
		fun newInstance() = EditFragment()
	}

}