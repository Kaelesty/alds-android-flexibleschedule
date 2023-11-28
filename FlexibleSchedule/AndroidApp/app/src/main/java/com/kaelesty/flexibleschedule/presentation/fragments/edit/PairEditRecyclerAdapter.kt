package com.kaelesty.flexibleschedule.presentation.fragments.edit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kaelesty.flexibleschedule.databinding.EditPairItemBinding
import com.kaelesty.flexibleschedule.domain.entities.Pair
import com.kaelesty.flexibleschedule.presentation.fragments.views.PairView

class PairEditRecyclerAdapter : Adapter<PairEditRecyclerAdapter.PairEditViewHolder>() {

	var pairs = mutableListOf<PairView>()

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PairEditViewHolder(
		EditPairItemBinding.inflate(
			LayoutInflater.from(parent.context), parent, false
		)
	)

	override fun onBindViewHolder(holder: PairEditViewHolder, position: Int) {
		val pair = pairs[position]

		with(holder.binding) {

			pairTietName.setText(pair.info)
			pairTietPlace.setText(pair.place)
			pairTietTeacher.setText(pair.place)
			pairTietTime.setText(pair.time)
		}
	}

	override fun getItemCount() = pairs.size

	class PairEditViewHolder(val binding: EditPairItemBinding) : ViewHolder(binding.root)
}