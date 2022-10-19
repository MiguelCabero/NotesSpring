package com.note.persist.NotePersist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.note.persist.NotePersist.controller.NoteController;

@SpringBootTest
class NotePersistApplicationTests {
	final NoteController noteController = new NoteController();
	final LocalDateTime local = LocalDateTime.now();
	final LocalDateTime local2 = LocalDateTime.now(); // User selects time

	// Test 1
	@Test
	void createNote_WhenCorrectArguments_ReturnsTrue() {

		final Note note = new Note(1, "description", "text", local,
				local2, "link", "mentions", 'a');

		final boolean isCreated = noteController.createNote(note);

		Assert.isTrue(isCreated, "Note must have all the requisites");

	}

	// Test 2
	@Test
	void getNotes_WhenCalled_ReturnsAllNotes() {

		for (int i = 0; i < 5; i++) {
			final Note note = new Note(i, "description", "text",
					local, local2, "link", "mentions", 'p');
			noteController.createNote(note);
		}
		final ArrayList<Note> noteList = noteController.getNotes();

		assertEquals(5, noteList.size());

	}

	// Test 3
	@Test
	void getOneNote_WhenCalled_ReturnsTheNote() {

		for (int i = 0; i < 5; i++) {
			final Note note = new Note(i + 1, "description", "text",
					local, local2, "link", "mentions", 'p');
			noteController.createNote(note);
			noteController.saveNote(note);
		}
		final Note noteFinded = noteController.getOneNote(2);
		final ArrayList<Note> noteList = noteController.getNotes();

		assertEquals(noteList.get(1), noteFinded);
	}

	// Test 4
	@Test
	void printNoteIntoFile_WhenCalled_ReturnsTrue()
			throws FileNotFoundException, IOException {
		final Note note = new Note(1, "description", "text", local,
				local2, "link", "mentions", 'p');
		final String username = System.getProperty("user.name");
		noteController.saveNote(note);

		final boolean printedNote = noteController.printNote(note,
				"C:\\Users\\" + username + "\\Documents\\note"
						+ note.getId() + ".txt");
		Assert.isTrue(printedNote, "Note is saved correctly");
	}

	// Test 5
	@Test
	void exportNoteIntoFile_WhenCalled_ReturnsTrue()
			throws FileNotFoundException, IOException {
		final Note note = new Note(1, "description", "text", local,
				local2, "link", "mentions", 'p');
		final String username = System.getProperty("user.name");
		noteController.saveNote(note);

		final boolean exportedNote = noteController.exportNote(note,
				"C:\\Users\\" + username + "\\Documents\\note"
						+ note.getId() + ".ser");
		Assert.isTrue(exportedNote, "Note is saved correctly");
	}

	// Test 6
	@Test
	void importNoteFromFile_WhenCalled_ReturnsFile()
			throws ClassNotFoundException, IOException {
		final Note note = new Note(1, "description", "text", local,
				local2, "link", "mentions", 'p');
		final String username = System.getProperty("user.name");
		final String path = "C:\\Users\\" + username
				+ "\\Documents\\note1.ser";

		noteController.exportNote(note, path);

		assertEquals(note, noteController.importNote(path));
	}

	// Test 7
	@Test
	void deleteNote_WhenCalled_ReturnsTrue()
		
	
		Assert.isTrue(noteController.deleteNote());
	}

}
