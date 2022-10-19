package com.note.persist.NotePersist;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class NotePersistApplicationTests {

	@Test
	void createNote_WhenCorrectArguments_ReturnsTrue() {

		final NoteController noteController = new NoteController();

		final LocalDateTime local = LocalDateTime.now();

		final LocalDateTime local2 = LocalDateTime.now();

		final bool isCreated = noteController.createNote(id,
				"description", "text", local, local2, "link",
				"mentions", 'p');

		Assert.isTrue(isCreated, "Note must have all the requisites");

	}

}
