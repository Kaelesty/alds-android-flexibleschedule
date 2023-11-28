package com.kaelesty.flexibleschedule.presentation.fragments.schedule

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kaelesty.flexibleschedule.databinding.ScheduleDayBinding
import com.kaelesty.flexibleschedule.domain.entities.Day

class DayRecyclerAdapter: ListAdapter<Day, DayRecyclerAdapter.DayViewHolder>(
	object: DiffUtil.ItemCallback<Day>() {
		override fun areItemsTheSame(oldItem: Day, newItem: Day) = oldItem == newItem
		override fun areContentsTheSame(oldItem: Day, newItem: Day) = oldItem == newItem
	}
) {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
		return DayViewHolder(
			ScheduleDayBinding.inflate(
				LayoutInflater.from(parent.context),
				parent,
				false
			)
		)
	}

	override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
		val day = currentList[position]
		holder.binding.title.text = getDayTitle(position)
		holder.adapter.submitList(day.pairs)
	}

	private fun getDayTitle(num: Int): String = when(num) {
		0 -> "Понедельник"
		1 -> "Вторник"
		2 -> "Среда"
		3 -> "Четверг"
		4 -> "Пятница"
		5 -> "Суббота"
		else -> "Воскресенье"
	}

	class DayViewHolder(val binding: ScheduleDayBinding): ViewHolder(binding.root) {
		val adapter = PairRecylcerAdapter()
		init {
			binding.recyclerPairs.adapter = adapter
		}
	}
}