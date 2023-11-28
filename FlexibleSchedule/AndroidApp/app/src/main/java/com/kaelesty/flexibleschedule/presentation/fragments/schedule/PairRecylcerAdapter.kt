package com.kaelesty.flexibleschedule.presentation.fragments.schedule

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kaelesty.flexibleschedule.databinding.SchedulePairBinding
import com.kaelesty.flexibleschedule.domain.entities.Pair

class PairRecylcerAdapter : ListAdapter<Pair, PairRecylcerAdapter.PairViewHolder>(
	object : DiffUtil.ItemCallback<Pair>() {
		override fun areItemsTheSame(oldItem: Pair, newItem: Pair) = oldItem == newItem
		override fun areContentsTheSame(oldItem: Pair, newItem: Pair) = oldItem == newItem
	}
) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PairViewHolder {
		return PairViewHolder(
			SchedulePairBinding.inflate(
				LayoutInflater.from(parent.context),
				parent,
				false
			)
		)
	}

	override fun onBindViewHolder(holder: PairViewHolder, position: Int) {
		val pair = currentList[position]
		with(holder.binding) {
			tvInfo.text = pair.info
			tvPlace.text = pair.place
			tvTeacher.text = pair.teacher
			tvTime.text = pair.time
		}
	}

	class PairViewHolder(val binding: SchedulePairBinding) : ViewHolder(binding.root)

}