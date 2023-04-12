package com.example.talkback.poc.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.*
import androidx.recyclerview.widget.RecyclerView
import com.example.talkback.poc.R
import com.example.talkback.poc.models.ComponentRenderer
import com.example.talkback.poc.views.ContentGridRowBinder

// TODO move those parameters in one class
internal class PageComponentAdapter(
    private val context: Context,
    private val componentRenderers: List<ComponentRenderer>
) : RecyclerView.Adapter<PageComponentAdapter.ComponentRendererViewHolder>(), LifecycleObserver {

    private val nestedRecyclersPool = RecyclerView.RecycledViewPool()

    override fun onBindViewHolder(holder: ComponentRendererViewHolder, position: Int) {
        holder.bind(componentRenderers[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComponentRendererViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.tv_row, parent, false)
       return ComponentRendererViewHolder(
            ContentGridRowBinder(
                context,
                view,
                parent
            )
        )
    }

    override fun getItemCount(): Int = componentRenderers.size

    override fun getItemViewType(position: Int): Int = componentRenderers[position].viewType

    override fun getItemId(position: Int): Long = componentRenderers[position].id.toLong()



    class ComponentRendererViewHolder(val contentGridRowBinder: ContentGridRowBinder)
        : RecyclerView.ViewHolder(contentGridRowBinder.view) {

        fun bind(componentRenderer: ComponentRenderer) {
            contentGridRowBinder.bind(componentRenderer)
        }
    }

}
