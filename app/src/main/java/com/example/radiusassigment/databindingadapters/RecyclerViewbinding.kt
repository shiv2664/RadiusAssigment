package com.example.radiusassigment.databindingadapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.*
import com.example.radiusassigment.model.Facility
import com.example.radiusassigment.model.Option
import com.example.radiusassigment.recyclerview_adapters.MoviesAdapter


@BindingAdapter("bindmovierecyclerview")
fun bindMovieRecyclerView(view: RecyclerView, dataList: List<Option>?) {

    val linearLayoutManager = LinearLayoutManager(view.context)
    val layoutManager = view.layoutManager
    if (layoutManager == null) {
        view.layoutManager = linearLayoutManager
    }

    view.setHasFixedSize(true)

    var adapter: MoviesAdapter? = view.adapter as MoviesAdapter?

    if (adapter == null) {
        if (dataList != null) {
            adapter = MoviesAdapter(view.context, dataList.toMutableList())
            view.adapter = adapter
        }
    }

    if (dataList != null) {
        adapter?.updateData(dataList)
    }

}
