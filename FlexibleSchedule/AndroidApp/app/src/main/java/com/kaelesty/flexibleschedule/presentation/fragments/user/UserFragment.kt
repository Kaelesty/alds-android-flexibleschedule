package com.kaelesty.flexibleschedule.presentation.fragments.user

import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.kaelesty.flexibleschedule.databinding.FragmentUserBinding

class UserFragment : Fragment() {

	private val binding by lazy {
		FragmentUserBinding.inflate(layoutInflater)
	}

	private val viewModel: UserViewModel by lazy {
		UserViewModelFactory(requireActivity(), requireActivity().application).create(UserViewModel::class.java)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {

		with(binding) {

			registerLayoutButton.setOnClickListener {

				if (registerLayoutHidden.visibility == View.GONE) {
					TransitionManager.beginDelayedTransition(registerLayoutButton, AutoTransition())
					registerLayoutHidden.visibility = View.VISIBLE
					TransitionManager.beginDelayedTransition(registerLayoutHidden, AutoTransition())
				}
				else {
					TransitionManager.beginDelayedTransition(registerLayoutHidden, AutoTransition())
					registerLayoutHidden.visibility = View.INVISIBLE
					registerLayoutHidden.visibility = View.GONE
				}
			}

			loginLayoutButton.setOnClickListener {

				if (loginLayoutHidden.visibility == View.GONE) {
					TransitionManager.beginDelayedTransition(loginLayoutButton, AutoTransition())
					loginLayoutHidden.visibility = View.VISIBLE
					TransitionManager.beginDelayedTransition(loginLayoutHidden, AutoTransition())
				}
				else {
					TransitionManager.beginDelayedTransition(loginLayoutHidden, AutoTransition())
					loginLayoutHidden.visibility = View.INVISIBLE
					loginLayoutHidden.visibility = View.GONE
				}
			}

			registerTietName.addTextChangedListener {
				registerTilName.error = ""
			}

			registerTietEmail.addTextChangedListener {
				registerTilEmail.error = ""
			}

			registerTietPassword.addTextChangedListener {
				registerTilPassword.error = ""
			}

			loginTietEmail.addTextChangedListener {
				loginTilEmail.error = ""
			}

			loginTietPassword.addTextChangedListener {
				loginTilPassword.error = ""
			}

			registerButton.setOnClickListener {
				viewModel.register(
					registerTietEmail.text.toString(),
					registerTietName.text.toString(),
					registerTietPassword.text.toString(),
				)
			}

			loginButton.setOnClickListener {
				viewModel.login(
					loginTietEmail.text.toString(),
					loginTietPassword.text.toString()
				)
			}

			logoutButton.setOnClickListener {
				viewModel.logout()
			}
		}


		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		viewModel.userState.observe(requireActivity()) { state ->

			when(state) {
				is UserStateLoading -> {
					binding.progressBar.visibility = View.VISIBLE
				}
				is UserStateUnauthorized -> {
					with(binding) {
						progressBar.visibility = View.GONE
						registerLayoutButton.visibility = View.VISIBLE
						loginLayoutButton.visibility = View.VISIBLE
						logoutLayout.visibility = View.GONE
					}
				}

				is UserStateAuthorized -> {
					with(binding) {
						progressBar.visibility = View.GONE
						registerLayoutButton.visibility = View.GONE
						loginLayoutButton.visibility = View.GONE
						logoutLayout.visibility = View.VISIBLE

						logoutName.text = state.name
						logoutEmail.text = state.email
					}
				}

				is UserStateRegisterError -> {
					state.toastMessage?.let {
						Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
					}


					with(binding) {
						progressBar.visibility = View.GONE
						registerTilName.error = state.nameMessage
						registerTilEmail.error = state.emailMessage
						registerTilPassword.error = state.passwordMessage
					}
				}

				is UserStateLoginError -> {
					state.toastMessage?.let {
						Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
					}

					with(binding) {
						progressBar.visibility = View.GONE
						loginTilEmail.error = state.emailMessage
						loginTilPassword.error = state.passwordMessage
					}
				}
			}
		}
	}

	companion object {
		fun newInstance() = UserFragment()
	}
}