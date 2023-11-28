package com.kaelesty.flexibleschedule.presentation.fragments.select

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.kaelesty.flexibleschedule.databinding.FragmentSelectBinding
import com.kaelesty.flexibleschedule.databinding.SelectUsergroupBinding
import com.kaelesty.flexibleschedule.domain.entities.UserGroup
import com.kaelesty.flexibleschedule.presentation.fragments.edit.EditViewModel
import com.kaelesty.flexibleschedule.presentation.fragments.edit.EditViewModelFactory

class SelectFragment : Fragment() {

	private val EMPTY_FIELD_MESSAGE = "Поле не может быть пустым"
	private val BAD_GROUP_MESSAGE = "Группа не найдена"

	private val viewModel by lazy {
		SelectViewModelFactory(requireContext(), requireActivity().application).create(SelectViewModel::class.java)
	}

	private val binding by lazy {
		FragmentSelectBinding.inflate(layoutInflater)
	}

	private var groupBindings = mutableListOf<SelectUsergroupBinding>()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.connectButton.setOnClickListener {
			if (binding.connectTietName.text.toString() == "") {
				binding.connectTilName.error = EMPTY_FIELD_MESSAGE
			}
			else {
				viewModel.connectGroup(binding.connectTietName.text.toString())
			}
		}

		binding.connectTietName.addTextChangedListener {
			binding.connectTilName.error = ""
		}

		binding.priorityButton.setOnClickListener {

			groupBindings.forEach { vb ->
				viewModel.groups.value?.forEach { group ->
					if (vb.tvGroup.text == group.code) {
						if (vb.tietPriority.text.toString() != "") {
							viewModel.changePriority(
								group.copy(
									priority = vb.tietPriority.text.toString().toInt()
								)
							)
						}
					}
				}
			}
		}

		viewModel.connectState.observe(requireActivity()) {
			when(it) {
				is ConnectOK -> {
					binding.connectTietName.setText("")
					Toast.makeText(requireContext(), "Успешно!", Toast.LENGTH_SHORT).show()
					// TODO Hide keyboard
				}

				is ConnectUnauth -> {
					Toast.makeText(requireContext(), "Необходима авторизация", Toast.LENGTH_SHORT).show()
				}

				is ConnectNotfound -> {
					binding.connectTilName.error = BAD_GROUP_MESSAGE
				}
				
				is ConnectUnknown -> {
					Toast.makeText(requireContext(), "Ошибка на сервере", Toast.LENGTH_SHORT).show()
				}
			}
		}

		viewModel.groups.observe(requireActivity()) {

			binding.priorityLv.removeAllViews()

			groupBindings = mutableListOf()

			it.forEach {  group ->
				val viewBinding = SelectUsergroupBinding.inflate(
					layoutInflater,
					binding.priorityLv,
					false
				)

				viewBinding.tvGroup.text = group.code
				viewBinding.tvPriority.text = group.priority.toString()

				binding.priorityLv.addView(viewBinding.root)

				viewBinding.deleteButton.setOnClickListener {
					viewModel.deleteGroup(group)
				}

				groupBindings.add(viewBinding)
			}
		}
	}

	companion object {
		@JvmStatic
		fun newInstance() = SelectFragment()
	}
}