package com.note.persist.NotePersist.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.note.persist.NotePersist.Note;
import com.note.persist.NotePersist.repository.NoteRepository;

public class NoteController {

	@Autowired
	NoteRepository noteRepository = new NoteRepository();

	ArrayList<Note> notesCreated = new ArrayList<>();

	// Test 1
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

	// Test 2
	public void saveNote(Note note) {
		noteRepository.addNote(note);
	}

	public ArrayList<Note> getNotes() {
		return this.notesCreated;
	}

	public void setNotes(ArrayList<Note> notes) {
		this.notesCreated = notes;
	}

	// Test 3
	public Note getOneNote(int id) {
		Note nota = noteRepository.getNote(id);
		return nota;
	}

	// Test 4
	public boolean exportNote(Note nota) {
		return false;
	}
}
