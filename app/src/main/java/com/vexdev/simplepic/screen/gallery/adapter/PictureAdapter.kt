package com.vexdev.simplepic.screen.gallery.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vexdev.simplepic.R
import com.vexdev.simplepic.core.utilities.timestampToString
import java.io.File

class PictureAdapter(
    private val context: Context,
    private val clickListener: (File) -> Unit
) :
    PagedListAdapter<File, PictureAdapter.ViewHolder?>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        i: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_item_picture_layout, viewGroup, false)
        return ViewHolder(view)
    }

    /**
     * Gets the image from the adapter and passes it to Glide API to load it
     */
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val file = getItem(position)
        Glide.with(context).load(file).into(viewHolder.img)
        viewHolder.title.text = file?.name
        viewHolder.time.text = file?.let { timestampToString(file.lastModified()) }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img: ImageView = view.findViewById(R.id.imageView)
        val title: TextView = view.findViewById(R.id.title)
        val time: TextView = view.findViewById(R.id.time)
    }

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<File>() {
            override fun areItemsTheSame(
                oldFile: File,
                newFile: File
            ) = oldFile.name == newFile.name

            override fun areContentsTheSame(
                oldFile: File,
                newFile: File
            ) = oldFile == newFile
        }
    }

}
