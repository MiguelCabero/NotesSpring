package com.note.persist.NotePersist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.note.persist.NotePersist.controller.NoteController;

@SpringBootTest
class NotePersistApplicationTests {

	@Test
	void createNote_WhenCorrectArguments_ReturnsTrue() {

		final NoteController noteController = new NoteController();

		final LocalDateTime local = LocalDateTime.now();

		final LocalDateTime local2 = LocalDateTime.now();

		final Note note = new Note(1, "description", "text", local,
				local2, "link", "mentions", 'a');

		final boolean isCreated = noteController.createNote(note);

		Assert.isTrue(isCreated, "Note must have all the requisites");

	}

	@Test
	void getNotes_WhenCalled_ReturnsAllNotes() {

		final NoteController noteController = new NoteController();
		final LocalDateTime local = LocalDateTime.now();
		final LocalDateTime local2 = LocalDateTime.now();

		for (int i = 0; i < 5; i++) {
			final Note note = new Note(i, "description", "text",
					local, local2, "link", "mentions", 'p');
			noteController.createNote(note);
		}
		final ArrayList<Note> noteList = noteController.getNotes();

		assertEquals(5, noteList.size());

	}
}
