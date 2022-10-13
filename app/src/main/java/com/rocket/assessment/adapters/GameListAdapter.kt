package com.rocket.assessment.adapters

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rocket.assessment.databinding.RowGameItemBinding
import com.rocket.assessment.entities.Response
import com.rocket.assessment.utility.convertToHighlightedHtmlString
import com.rocket.assessment.utility.getDateOnly
import com.rocket.assessment.utility.onEndLoading
import com.rocket.assessment.utility.swapVisibility

/**
 * [ListAdapter] for the game item list
 */
class GameListAdapter: ListAdapter<Response.GameItem, GameListAdapter.ViewHolder>(DiffCallback) {

    private var keyword: String = ""
    private var itemClickListener: ((item: Response.GameItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: RowGameItemBinding = RowGameItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
        holder.recycle()
    }

    fun updateKeyword(text: String) {
        this.keyword = text
    }

    fun setItemClickListener(listener: ((item: Response.GameItem) -> Unit)) {
        this.itemClickListener = listener
    }

    /**
     * [DiffUtil.ItemCallback] for [Response.GameItem]
     */
    object DiffCallback: DiffUtil.ItemCallback<Response.GameItem>() {
        override fun areItemsTheSame(
            oldItem: Response.GameItem,
            newItem: Response.GameItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Response.GameItem,
            newItem: Response.GameItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    /**
     * [ViewHolder] for the [Response.GameItem]
     */
    inner class ViewHolder(private val binding: RowGameItemBinding): RecyclerView.ViewHolder(binding.root) {
        /**
         * Update view
         */
        fun bind(item: Response.GameItem) {
            // Update text contents
            binding.name.text = item.name.convertToHighlightedHtmlString(keyword)
            binding.date.text = item.dateLastUpdated.getDateOnly()

            // Update image
            Glide.with(binding.root.context)
                .load(item.image.smallUrl)
                .error(ColorDrawable(Color.GREEN))
                .fallback(ColorDrawable(Color.GREEN))
                .onEndLoading {
                    swapVisibility(
                        500,
                        binding.image,
                        binding.loadingLayout
                    )
                }
                .into(binding.image)

            binding.root.setOnClickListener {
                itemClickListener?.invoke(item)
            }
        }

        /**
         * Clear memory when the view holder is recycled
         */
        fun recycle() {
            Glide.with(binding.root.context).clear(binding.image)
        }
    }

}