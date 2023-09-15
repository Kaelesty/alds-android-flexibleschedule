package com.kaelesty.flexibleschedule.presentation.fragments.schedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.kaelesty.flexibleschedule.databinding.FragmentScheduleBinding
import com.kaelesty.flexibleschedule.presentation.fragments.user.UserViewModelFactory

class ScheduleFragment : Fragment() {

	private val binding by lazy {
		FragmentScheduleBinding.inflate(layoutInflater)
	}

	private val viewModel by lazy {
		ScheduleViewModelFactory(requireActivity(), requireActivity().application)
			.create(ScheduleViewModel::class.java)
	}

	private val adapter = DayRecyclerAdapter()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.recyclerDays.adapter = adapter

		viewModel.timetable.observe(requireActivity()) { timetable ->
			if (timetable.days.isNotEmpty()) {
				binding.recyclerDays.visibility = View.VISIBLE
				adapter.submitList(timetable.days.toMutableList())
				binding.errorMessage.visibility = View.GONE
			}
			else {
				binding.errorMessage.visibility = View.VISIBLE
			}
		}
	}

	companion object {
		fun newInstance() = ScheduleFragment()
	}
}