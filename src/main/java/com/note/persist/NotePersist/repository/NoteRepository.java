package com.note.persist.NotePersist.repository;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.note.persist.NotePersist.Note;

@Component
public class NoteRepository {

	private ArrayList<Note> notes = new ArrayList<Note>();

	public ArrayList<Note> getNotes() {
		return notes;
	}

	public void setNotes(ArrayList<Note> notes) {
		this.notes = notes;
	}

	public void addNote(Note note) {
		this.notes.add(note);
	}

	public Note getNote(int id) {
		return notes.stream().filter(item -> item.getId() == id).findAny().get();
	}
}
