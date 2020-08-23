package com.vexdev.simplepic.screen.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.vexdev.simplepic.R
import com.vexdev.simplepic.screen.gallery.adapter.PictureAdapter
import kotlinx.android.synthetic.main.fragment_gallery.*

class GalleryFragment : Fragment() {
    private val viewModel: GalleryViewModel by viewModels { GalleryViewModelFactory { requireContext() } }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = GridLayoutManager(requireContext(), 2)
        val adapter = PictureAdapter(requireContext()) { file -> viewModel.onPictureClick(file) }
        viewModel.pictures.observe(viewLifecycleOwner, Observer { adapter.submitList(it) })
        galleryView.adapter = adapter
        galleryView.layoutManager = layoutManager
    }
}
