package com.kushal.kotlin_roombasednotesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.kushal.kotlin_roombasednotesapp.R
import com.kushal.kotlin_roombasednotesapp.db.Note
import com.kushal.kotlin_roombasednotesapp.ui.HomeFragmentDirections
import kotlinx.android.synthetic.main.note_layout.view.*

class NotesAdapter(val notes : List<Note>) : RecyclerView.Adapter<NotesAdapter.NoteHolder>() {

    class NoteHolder(val view : View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        return NoteHolder(LayoutInflater.from(parent.context).inflate(R.layout.note_layout, parent, false))
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {

        holder.view.text_view_title.text = notes[position].title
        holder.view.text_view_note.text = notes[position].note

        holder.view.setOnClickListener {

            val action = HomeFragmentDirections.actionAddNote()
            action.note = notes[position]
            Navigation.findNavController(it).navigate(action)

        }
    }

    override fun getItemCount() = notes.size

}