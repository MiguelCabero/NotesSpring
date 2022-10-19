package com.note.persist.NotePersist.repository;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

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

	public boolean printNote(Note nota, String path) throws FileNotFoundException, IOException {
		final String CSV_SEPARATOR = ",";
		boolean response = false;
		try {
			final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));
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
			response = true;
		} catch (final Exception e) {
			System.out.println(e);
		}

		return response;

	}

	public boolean exportNote(Note note, String path) {
		boolean response = false;
		try (FileOutputStream fos = new FileOutputStream(path); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(note);
			response = true;
		} catch (final IOException ex) {
			ex.printStackTrace();
		}
		return response;
	}

	public Note importOneNote(String path) throws IOException, ClassNotFoundException {
		try {
			final FileInputStream file = new FileInputStream(path);
			final ObjectInputStream in = new ObjectInputStream(file);
			final Note object = (Note) in.readObject();

			in.close();
			file.close();
			return object;
		}

		catch (final IOException ex) {
			ex.printStackTrace();
		}

		catch (final ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return null;

	}

	public boolean deleteNote(int id) {
		return this.notes.remove(notes.stream().filter(item -> item.getId() == id).findAny().get());
	}

	public Note updateNote(Note note, Note newNote) {
		final int index = this.notes.indexOf(note);
		this.notes.set(index, newNote);
		return this.notes.get(index);
	}

	public String checkLink(String link) {
		String type = "";
		ArrayList<String> patternString = new ArrayList<String>(
				Arrays.asList("(.*).pdf", "(.*)@(.*)", "(.*)youtube(.*)", "http://(.*)", "https://(.*)"));

		boolean flag = false;
		for (int i = 0; i < patternString.size() && !flag; i++) {
			flag = checkPattern(link, patternString.get(i));
			if (flag)
				type = patternString.get(i);
		}

		switch (type) {
		case "(.*).pdf":
			type = "It's a pdf";
			break;
		case "(.*)@(.*)":
			type = "It's an email";
			break;
		case "(.*)youtube(.*)":
			type = "It's a youtube link";
			break;
		case "http://(.*)":
		case "https://(.*)":
			type = "It's a webpage";
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + type);
		}
		return type;
	}

	private boolean checkPattern(String link, String patternString) {
		return link.matches(patternString);
	}

}
