package com.kushal.kotlin_roombasednotesapp.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kushal.kotlin_roombasednotesapp.R
import com.kushal.kotlin_roombasednotesapp.adapter.NotesAdapter
import com.kushal.kotlin_roombasednotesapp.db.NoteDatabase
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment() {

    lateinit var mContext: Context
    lateinit var button_add: FloatingActionButton


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        mContext = requireContext()
        button_add = view.findViewById(R.id.button_add)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recycler_view_notes.setHasFixedSize(true)
        recycler_view_notes.layoutManager =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

        launch {

            val notes = NoteDatabase(mContext).getNoteDao().getAllNotes()
            recycler_view_notes.adapter = NotesAdapter(notes)

        }

        button_add.setOnClickListener {

            val action = HomeFragmentDirections.actionAddNote()
            Navigation.findNavController(it).navigate(action)

        }

    }

}