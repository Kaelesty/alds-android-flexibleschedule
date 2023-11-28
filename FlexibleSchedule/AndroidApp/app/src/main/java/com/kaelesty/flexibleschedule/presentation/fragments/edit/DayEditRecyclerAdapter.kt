package com.kaelesty.flexibleschedule.presentation.fragments.edit

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kaelesty.flexibleschedule.databinding.EditDayItemBinding
import com.kaelesty.flexibleschedule.domain.entities.Day
import com.kaelesty.flexibleschedule.presentation.fragments.views.DayView
import com.kaelesty.flexibleschedule.presentation.fragments.views.PairView

class DayEditRecyclerAdapter: Adapter<DayEditRecyclerAdapter.DayEditViewHolder>(){

	var days: MutableList<DayView> = mutableListOf()
		set(value) {
			field = value
			notifyDataSetChanged()
		}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DayEditViewHolder(
		EditDayItemBinding.inflate(
			LayoutInflater.from(parent.context),
			parent, false
		)
	)

	override fun onBindViewHolder(holder: DayEditViewHolder, position: Int) {
		val day = days[position]
		with(holder) {
			adapter.pairs = day.pairs.toMutableList()
			binding.tvTitle.text = day.name
		}
	}

	override fun getItemCount() = days.size

	class DayEditViewHolder(val binding: EditDayItemBinding): ViewHolder(binding.root) {

		val adapter = PairEditRecyclerAdapter()

		init {
			//binding.recyclerPairs.adapter = adapter
			binding.buttonAdd.setOnClickListener {
				adapter.pairs.add(
					PairView("", "", "", "")
				)
				adapter.notifyDataSetChanged()
			}
		}
	}
}