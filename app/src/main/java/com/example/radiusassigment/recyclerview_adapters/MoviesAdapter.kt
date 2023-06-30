package com.example.radiusassigment.recyclerview_adapters
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.radiusassigment.databinding.ItemMovieBinding
import com.example.radiusassigment.interfaces.IMainActivity
import com.example.radiusassigment.model.Facility
import com.example.radiusassigment.BR
import com.example.radiusassigment.model.Option

class MoviesAdapter(private val context: Context, private var facility: MutableList<Option>) :
    RecyclerView.Adapter<MoviesAdapter.BindingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {

        val rooView: ViewDataBinding =
            ItemMovieBinding.inflate(LayoutInflater.from(context), parent, false)
        return BindingViewHolder(rooView)
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {

        val movieCurrent = facility[position]
        holder.itemBinding.setVariable(BR.movieItem, movieCurrent)
        holder.itemBinding.setVariable(BR.listener, context as IMainActivity)
        holder.itemBinding.executePendingBindings()

        // can't use glide in xml so setting image here
//        var thumbnailImage: String? = null
//        CoroutineScope(Dispatchers.IO).launch {
//
//            thumbnailImage = Facility[position].MoviePoster
//            withContext(Dispatchers.Main) {
//                if (thumbnailImage != null) {
//                    Glide.with(context)
//                        .load(thumbnailImage)
//                        .into(holder.itemBinding.root.img)
//
//                }
//
//            }
//
//        }

//        Log.d("MyTag","Image is "+thumbnailImage.toString())


    }

    override fun getItemCount(): Int {
        Log.d("MyTag","notification list size : "+facility.size)
        return facility.size
    }

    fun updateData(newDataList: List<Option>) {
        val oldList = facility
        val diffUtil: DiffUtil.DiffResult = DiffUtil.calculateDiff(
            MovieItemDiffCallback(
                oldList, newDataList
            )
        )
        facility = newDataList.toMutableList()
        diffUtil.dispatchUpdatesTo(this)
    }

    class MovieItemDiffCallback(
        var oldFacility: List<Option>,
        var newFacility: List<Option>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldFacility.size
        }

        override fun getNewListSize(): Int {
            return newFacility.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return (oldFacility[oldItemPosition].name == newFacility[newItemPosition].name)
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return (oldFacility[oldItemPosition] == newFacility[newItemPosition])
        }
    }

    class BindingViewHolder(val itemBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {}

}