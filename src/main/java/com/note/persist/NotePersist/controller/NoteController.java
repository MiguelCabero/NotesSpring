package com.note.persist.NotePersist.controller;

import java.time.LocalDateTime;

import com.note.persist.NotePersist.Note;

public class NoteController {
	public boolean createNote(int id, String description, String gtext, LocalDateTime created, LocalDateTime completed,
			String link, String gmentions, char priority) {
		boolean response = false;

		try {
			Note nota = new Note(id, description, gtext, created, completed, link, gmentions, priority);
			response = true;
		} catch (IllegalArgumentException e) {
			System.out.println(e);
		}

		return response;
	}
}
