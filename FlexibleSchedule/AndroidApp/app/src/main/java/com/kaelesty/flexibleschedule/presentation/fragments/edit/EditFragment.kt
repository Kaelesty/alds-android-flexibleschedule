package com.kaelesty.flexibleschedule.presentation.fragments.edit

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.kaelesty.flexibleschedule.R
import com.kaelesty.flexibleschedule.databinding.EditDayItemBinding
import com.kaelesty.flexibleschedule.databinding.EditPairItemBinding
import com.kaelesty.flexibleschedule.databinding.FragmentEditBinding
import com.kaelesty.flexibleschedule.domain.entities.Day
import com.kaelesty.flexibleschedule.domain.entities.Timetable
import com.kaelesty.flexibleschedule.presentation.fragments.views.DayView

class EditFragment : Fragment() {

	private val viewModel by lazy {
		EditViewModelFactory(requireContext(), requireActivity().application).create(EditViewModel::class.java)
	}

	private var daysBindings = mutableListOf<EditDayItemBinding>()
	private var pairBindings = mutableListOf<Pair<Int, EditPairItemBinding>>()

	private val EMPTY_FIELD_MESSAGE = "Поле не может быть пустым"
	private val UNAUTH_MESSAGE = "Необходима авторизация"
	private val UNKWOWN_MESSAGE = "Ошибка на сервере"
	private val TOO_MANY_MESSAGE = "Нельзя добавить больше 2х расписаний"

	private val WEEK_DAYS = mutableListOf(
		"Понедельник", "Вторник",
		"Среда", "Четверг",
		"Пятница", "Суббота",
		"Воскресенье",
	)

	private val binding by lazy {
		FragmentEditBinding.inflate(layoutInflater)
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		with(binding) {

			createTietName.addTextChangedListener {
				createTilName.error = ""
			}

			createDaysList()

			createButton.setOnClickListener {
				collectTimetable()
			}
		}

		observeViewModel()
	}

	private fun createDaysList() {
		for (i in 0 until 7) {
			val dayBinding = EditDayItemBinding.inflate(
				LayoutInflater.from(requireContext()),
				binding.daysList,
				true
			)
			daysBindings.add(dayBinding)
			dayBinding.tvTitle.text = WEEK_DAYS[i]
			dayBinding.buttonAdd.setOnClickListener {
				pairBindings.add(
					Pair(
						i, EditPairItemBinding.inflate(
							LayoutInflater.from(requireContext()), dayBinding.listPairs, true
						).let {
							setErrorHider(it)
							it
						}
					)
				)
			}
		}
	}

	private fun setErrorHider(pair: EditPairItemBinding) {

		with(pair) {
			pairTietTime.addTextChangedListener {
				pairTilTime.error = ""
			}

			pairTietPlace.addTextChangedListener {
				pairTilPlace.error = ""
			}

			pairTietName.addTextChangedListener {
				pairTilName.error = ""
			}

			pairTietTeacher.addTextChangedListener {
				pairTilTeacher.error = ""
			}

		}
	}

	private fun observeViewModel() {
		viewModel.state.observe(requireActivity()) {
			when (it) {
				is EditStateOK -> {
					binding.createTietName.setText("")
					Toast.makeText(requireContext(), "Успешно!", Toast.LENGTH_SHORT).show()
					binding.daysList.removeAllViews()
					daysBindings = mutableListOf()
					pairBindings = mutableListOf()
					createDaysList()
				}

				is EditStateUnauth -> {
					binding.createTilName.error = UNAUTH_MESSAGE
				}

				is EditStateToMany -> {
					binding.createTilName.error = TOO_MANY_MESSAGE
				}

				else -> {
					binding.createTilName.error = UNKWOWN_MESSAGE
				}
			}
		}
	}

	private fun collectTimetable() {
		val name = binding.createTietName.text.toString()
		if (name == "") {
			binding.createTilName.error = EMPTY_FIELD_MESSAGE
			return
		}

		val days = collectDays()
		days?.let {
			viewModel.upload(
				name, Timetable(0, it)
			)
		}
	}

	private fun collectDays(): List<Day>? {
		val days = mutableListOf<Day>()
		val pairs = mutableListOf<Pair<Int, com.kaelesty.flexibleschedule.domain.entities.Pair>>()

		for (pairBinding in pairBindings) {
			with(pairBinding.second) {
				val time = pairTietTime.text.toString()
				if (time == "") {
					pairTilTime.error = EMPTY_FIELD_MESSAGE
					return null
				}
				val name = pairTietName.text.toString()
				if (name == "") {
					pairTilName.error = EMPTY_FIELD_MESSAGE
					return null
				}
				val place = pairTietPlace.text.toString()
				if (place == "") {
					pairTilPlace.error = EMPTY_FIELD_MESSAGE
					return null
				}
				val teacher = pairTietTeacher.text.toString()
				if (teacher == "") {
					pairTilTeacher.error = EMPTY_FIELD_MESSAGE
					return null
				}
				pairs.add(
					Pair(
						pairBinding.first, com.kaelesty.flexibleschedule.domain.entities.Pair(
							time, name, place, teacher
						)
					)
				)

			}
		}
		for (i in 0 until 7) {
			days.add(
				Day(
					0, pairs.filter { it.first == i }.map { it.second}
				)
			)
		}
		return days
	}

	companion object {
		fun newInstance() = EditFragment()
	}

}