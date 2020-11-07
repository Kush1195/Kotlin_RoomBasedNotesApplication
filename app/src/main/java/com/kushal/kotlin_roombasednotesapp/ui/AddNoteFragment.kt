package com.kushal.kotlin_roombasednotesapp.ui

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.kushal.kotlin_roombasednotesapp.R
import com.kushal.kotlin_roombasednotesapp.db.Note
import com.kushal.kotlin_roombasednotesapp.db.NoteDatabase
import kotlinx.android.synthetic.main.fragment_add_note.*
import kotlinx.coroutines.launch

class AddNoteFragment : BaseFragment() {

    lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_add_note, container, false)
        mContext = requireContext()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        button_save.setOnClickListener {

            val noteTitle = edit_text_title.text.toString().trim()
            val noteBody = edit_text_note.text.toString().trim()

            if (noteTitle.isEmpty()) {
                edit_text_title.error = "Title Is Required"
                edit_text_title.requestFocus()
                return@setOnClickListener
            }

            if (noteBody.isEmpty()) {
                edit_text_note.error = "Note Is Required"
                edit_text_note.requestFocus()
                return@setOnClickListener
            }

            launch {

                val note = Note(noteTitle, noteBody)

                NoteDatabase(mContext).getNoteDao().addNote(note)
                Toast.makeText(mContext, "Note Saved", Toast.LENGTH_LONG).show()

                Handler().postDelayed(Runnable {

                    val action = AddNoteFragmentDirections.actionSaveNote()
                    Navigation.findNavController(it).navigate(action)

                },100)

            }


        }

    }


}