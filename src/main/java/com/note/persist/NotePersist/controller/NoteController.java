package com.note.persist.NotePersist.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
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
		return noteRepository.getNotes();
	}

	public void setNotes(ArrayList<Note> notes) {
		this.notesCreated = notes;
	}

	// Test 3
	public Note getOneNote(int id) {
		final Note nota = noteRepository.getNote(id);
		return nota;
	}

	// Test 4
	public boolean printNote(Note nota, String path)
			throws FileNotFoundException, IOException {
		final boolean printed = noteRepository.printNote(nota, path);

		return printed;
	}

	// Test 5
	public boolean exportNote(Note note, String path) {
		final boolean export = noteRepository.exportNote(note, path);
		return export;
	}

	// Test 6
	public Note importNote(String path)
			throws ClassNotFoundException, IOException {
		return noteRepository.importOneNote(path);
	}

	// Test 7
	public boolean deleteNote(int id) {
		return noteRepository.deleteNote(id);
	}

	// Test 8
	public Note updateNote(Note note, Note newNote) {
		// TODO Auto-generated method stub
		return noteRepository.updateNote(note, newNote);
	}

	// Test 9
	public String checkLink(String link) {
		return noteRepository.checkLink(link);
	}

	// Test 10
	public String getProfileLink(Note note) {
		return noteRepository.getMentionLink(note);
	}
}
