package com.vexdev.simplepic.screen.gallery

import android.content.Context
import android.os.Environment
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.vexdev.simplepic.core.utilities.FileDataSourceFactory
import java.io.File

open class GalleryRepository(private val context: Context) {

    open fun getPictures(): LiveData<PagedList<File>> =
        LivePagedListBuilder(FileDataSourceFactory(getPicDirectory()), 20).build()

    private fun getPicDirectory(): File? =
        context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)

}
