package com.vexdev.simplepic.core.utilities

import androidx.paging.DataSource
import androidx.paging.PositionalDataSource
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.nio.channels.FileChannel

@Throws(IOException::class)
fun copyFile(src: File?, dst: File?) {
    val inChannel: FileChannel = FileInputStream(src).channel
    val outChannel: FileChannel = FileOutputStream(dst).channel
    try {
        inChannel.transferTo(0, inChannel.size(), outChannel)
    } finally {
        inChannel.close()
        outChannel.close()
    }
}

class FileDataSourceFactory(private val directory: File?) : DataSource.Factory<Int, File>() {
    override fun create(): DataSource<Int, File> = FileSimpleDataSource(directory)
}

/**
 * This special [PositionalDataSource] is based on files and abstracts away a provider of such files
 * allowing for paged loading.
 * It may be switched in the future for a different implementation (Database based for example, as
 * Room comes already with a default implementation)
 */
class FileSimpleDataSource(private val directory: File?) : PositionalDataSource<File>() {

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<File>) {
        callback.onResult(
            directory?.listFiles()?.toList()?.subList(
                params.startPosition,
                params.startPosition + params.loadSize
            ) ?: emptyList()
        )
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<File>) {
        val files = directory?.listFiles()?.toList() ?: emptyList()
        val totalCount: Int = files.size

        val position = computeInitialLoadPosition(params, totalCount)
        val loadSize =
            computeInitialLoadSize(params, position, totalCount)

        val sublist: List<File> = files.subList(position, position + loadSize)
        callback.onResult(sublist, position, totalCount)
    }

}
