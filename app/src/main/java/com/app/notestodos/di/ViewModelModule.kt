package com.app.notestodos.di

import androidx.lifecycle.ViewModel
import com.app.notestodos.NoteViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(NoteViewModel::class)
    protected abstract fun bindNoteViewModel(noteViewModel: NoteViewModel): ViewModel
}