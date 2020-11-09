package com.kushal.kotlin_roombasednotesapp.ui

import android.app.AlertDialog
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.navigation.Navigation
import com.kushal.kotlin_roombasednotesapp.R
import com.kushal.kotlin_roombasednotesapp.db.Note
import com.kushal.kotlin_roombasednotesapp.db.NoteDatabase
import kotlinx.android.synthetic.main.fragment_add_note.*
import kotlinx.coroutines.launch

class AddNoteFragment : BaseFragment() {

    lateinit var mContext: Context
    private var note : Note? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)
        val view = inflater.inflate(R.layout.fragment_add_note, container, false)
        mContext = requireContext()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {

            note = AddNoteFragmentArgs.fromBundle(it).note

            edit_text_title.setText(note?.title)
            edit_text_note.setText(note?.note)

        }

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

                val mNote = Note(noteTitle, noteBody)

                if (note == null) {
                    NoteDatabase(mContext).getNoteDao().addNote(mNote)
                    Toast.makeText(mContext, "Note Saved", Toast.LENGTH_LONG).show()
                } else {
                    mNote.id = note!!.id
                    NoteDatabase(mContext).getNoteDao().updateNote(mNote)
                    Toast.makeText(mContext, "Note Updated", Toast.LENGTH_LONG).show()
                }

                Handler().postDelayed(Runnable {

                    val action = AddNoteFragmentDirections.actionSaveNote()
                    Navigation.findNavController(it).navigate(action)

                },100)

            }


        }

    }

    @Override
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.delete ->
                if (note != null) {
                    deleteNote()
                } else {
                    Toast.makeText(mContext, "Can Not Delete Before Save...", Toast.LENGTH_SHORT).show()
                }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteNote() {

        AlertDialog.Builder(mContext).apply {
            setTitle("Are You Sure?")
            setMessage("You Can Not Undo This Operation")
            setPositiveButton("Yes"){_,_->
                launch {

                    NoteDatabase(mContext).getNoteDao().deleteNote(note!!)
                    val action = AddNoteFragmentDirections.actionSaveNote()
                    Navigation.findNavController(requireView()).navigate(action)

                }
            }
            setNegativeButton("No"){_,_->

            }
        }.create().show()
    }

    @Override
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }


}