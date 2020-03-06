package com.app.notestodos

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.app.notestodos.entity.Note
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val ADD_NOTE_REQUEST = 1
    private lateinit var noteviewModel: NoteViewModel
    private val adapter = NoteAdapter()
    lateinit var allNotes: List<Note>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupButtonAddNote()
        setupRecyclerView()
        allNotes = mutableListOf()
        val viewModelFactory=MyViewModelFactory(application)
        noteviewModel = ViewModelProvider(this,viewModelFactory).get(NoteViewModel::class.java)
        noteviewModel.getAllNotes().observe(this,
            Observer<List<Note>> { list ->
                list?.let {
                    allNotes=it
                    adapter.setNotes(it)
                }
            })
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT.or(
            ItemTouchHelper.RIGHT)) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
               return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                noteviewModel.delete(allNotes.get(position))
                Toast.makeText(baseContext, "Note Deleted!", Toast.LENGTH_SHORT).show()
            }
        }
        ).attachToRecyclerView(recycler_view)
    }
        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.main_menu, menu)
            return true
        }

        override fun onOptionsItemSelected(item: MenuItem?): Boolean {
            return when (item?.itemId) {
                R.id.delete_all_notes -> {
                    noteviewModel.deleteAllNotes()
                    Toast.makeText(this, "All notes deleted!", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> {
                    super.onOptionsItemSelected(item)
                }
            }
        }
    private fun setupButtonAddNote() {
        buttonAddNote.setOnClickListener {
            startActivityForResult(
                Intent(this, AddNoteActivity::class.java),
                ADD_NOTE_REQUEST
            )
        }
    }

    private fun setupRecyclerView() {
        recycler_view.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        recycler_view.setHasFixedSize(true)
        recycler_view.adapter = adapter
    }
        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)

            if (requestCode == ADD_NOTE_REQUEST && resultCode == Activity.RESULT_OK) {
                val newNote = Note(
                    data!!.getStringExtra(AddNoteActivity.EXTRA_TITLE),
                    data.getStringExtra(AddNoteActivity.EXTRA_DESCRIPTION)
                )
                noteviewModel.insert(newNote)

                Toast.makeText(this, "Note saved!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Note not saved!", Toast.LENGTH_SHORT).show()
            }

        }

}
class MyViewModelFactory(val arg: Application):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Application::class.java)
            .newInstance(arg)
    }

}