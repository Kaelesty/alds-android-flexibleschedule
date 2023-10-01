package com.kaelesty.flexibleschedule.presentation.fragments.select

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.kaelesty.flexibleschedule.databinding.FragmentSelectBinding
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

		viewModel.connectState.observe(requireActivity()) {
			when(it) {
				is ConnectOK -> {
					binding.connectTietName.setText("")
					Toast.makeText(requireContext(), "Успешно!", Toast.LENGTH_SHORT).show()
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
	}

	companion object {
		@JvmStatic
		fun newInstance() = SelectFragment()
	}
}