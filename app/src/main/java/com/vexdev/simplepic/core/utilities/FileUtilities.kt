package com.vexdev.simplepic.core.utilities

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
