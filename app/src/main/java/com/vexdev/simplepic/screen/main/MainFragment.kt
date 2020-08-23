package com.vexdev.simplepic.screen.main

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.vexdev.simplepic.R
import com.vexdev.simplepic.core.utilities.copyFile
import kotlinx.android.synthetic.main.fragment_main.*
import java.io.File
import java.io.IOException


class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.navigationEvent.observe(this, Observer {
            findNavController().navigate(it)
        })
        fab.setOnClickListener {
            dispatchTakePictureIntent()
        }
        openGallery.setOnClickListener {
            viewModel.onOpenGalleryClicked()
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode != REQUEST_TAKE_PHOTO) return
        showFilenameDialog()
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val storageDir: File? = requireContext().cacheDir
        return File.createTempFile("picture", ".jpg", /* suffix */storageDir /* directory */)
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(requireContext().packageManager)?.also {
                // Create the File where the photo should go
                viewModel.cacheFile = try {
                    createImageFile()
                } catch (ex: IOException) {
                    Toast.makeText(requireContext(), R.string.file_error, Toast.LENGTH_SHORT).show()
                    null
                }
                // Continue only if the File was successfully created
                viewModel.cacheFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        requireContext(),
                        "com.vexdev.simplepic.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
                }
            }
        }
    }

    private fun showFilenameDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setTitle(R.string.filename_dialog_title)
        val input = EditText(requireContext())
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)
        builder.setPositiveButton(
            R.string.dialog_ok
        ) { _, _ -> renamePicture(input.text.toString()) }
        builder.setNegativeButton(
            R.string.dialog_cancel
        ) { dialog, _ -> dialog.cancel() }
        builder.show()
    }

    private fun renamePicture(newName: String) {
        val dir = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        copyFile(viewModel.cacheFile, File(dir, "$newName.jpeg"))
    }

    companion object {
        const val REQUEST_TAKE_PHOTO = 1
    }
}
