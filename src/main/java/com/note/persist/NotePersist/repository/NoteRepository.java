package com.note.persist.NotePersist.repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
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
		return notes.stream().filter(item -> item.getId() == id)
				.findAny().get();
	}

	public boolean printNote(Note nota, String path)
			throws FileNotFoundException, IOException {
		final String CSV_SEPARATOR = ",";
		final File f = new File(path);

		try {
			final BufferedWriter bw = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(path),
							"UTF-8"));
			final StringBuffer oneLine = new StringBuffer();

			oneLine.append(nota.getId());
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(nota.getDescription());
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(nota.getText());
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(nota.getCreated());
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(nota.getCompleted());
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(nota.getLink());
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(nota.getMentions());
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(nota.getPriority());
			bw.write(oneLine.toString());
			bw.newLine();
			bw.flush();
			bw.close();
			return true;
		} catch (final Exception e) {
			System.out.println(e);
		}

		return false;

	}

}
