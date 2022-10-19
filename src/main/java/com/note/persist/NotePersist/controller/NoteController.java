package com.note.persist.NotePersist.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.note.persist.NotePersist.Note;
import com.note.persist.NotePersist.repository.NoteRepository;

public class NoteController {

	@Autowired
	NoteRepository noteRepository;

	ArrayList<Note> notesCreated = new ArrayList<>();

	public boolean createNote(Note note) {
		boolean response = false;

		try {
			notesCreated.add(note);
			response = true;
		} catch (final IllegalArgumentException e) {
			System.out.println(e);
		}

		return response;
	}

	public void saveNote(Note note) {
		final boolean response = false;

		noteRepository.addNote(note);
	}

	public ArrayList<Note> getNotes() {
		return this.notesCreated;
	}

	public void setNotes(ArrayList<Note> notes) {
		this.notesCreated = notes;
	}

}
