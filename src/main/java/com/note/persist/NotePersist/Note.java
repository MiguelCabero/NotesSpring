package com.note.persist.NotePersist;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Note implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int id;
	private String description;
	private String text;
	private final LocalDateTime created;
	private LocalDateTime completed;
	private String link;
	private String mentions;
	private char priority;

	public Note() {
		this.id = 0;
		this.created = null;
	}

	public Note(int id, String description, String text,
			LocalDateTime created, LocalDateTime completed,
			String link, String mentions, char priority) {
		super();
		this.id = id;
		this.description = description;
		this.text = text;
		this.created = created;
		this.completed = completed;
		this.link = link;
		this.mentions = mentions;
		this.priority = priority;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LocalDateTime getCompleted() {
		return completed;
	}

	public void setCompleted(LocalDateTime completed) {
		this.completed = completed;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getMentions() {
		return mentions;
	}

	public void setMentions(String mentions) {
		this.mentions = mentions;
	}

	public char getPriority() {
		return priority;
	}

	public void setPriority(char priority) {
		this.priority = priority;
	}

	public int getId() {
		return id;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	@Override
	public String toString() {
		return "Note [id=" + id + ", description=" + description
				+ ", text=" + text + ", created=" + created
				+ ", completed=" + completed + ", link=" + link
				+ ", mentions=" + mentions + ", priority=" + priority
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(completed, created, description, id, link,
				mentions, priority, text);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Note other = (Note) obj;
		return Objects.equals(completed, other.completed)
				&& Objects.equals(created, other.created)
				&& Objects.equals(description, other.description)
				&& id == other.id && Objects.equals(link, other.link)
				&& Objects.equals(mentions, other.mentions)
				&& priority == other.priority
				&& Objects.equals(text, other.text);
	}

}
