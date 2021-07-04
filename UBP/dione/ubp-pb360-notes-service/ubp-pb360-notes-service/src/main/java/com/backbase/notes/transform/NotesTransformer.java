package com.backbase.notes.transform;

import org.springframework.stereotype.Component;

import com.backbase.notes.rest.spec.v1.notes.id.NotesidGetResponseBody;
import com.externalnotes.api.model.Note;

@Component
public class NotesTransformer {

	public NotesidGetResponseBody transform(Note note) {
		NotesidGetResponseBody body = new NotesidGetResponseBody();
		body.setBody(note.getBody());
		body.setClientLogin(note.getClientLogin());
		body.setId(note.getId());
		body.setTitle(note.getTitle());
		body.setCreationDate(note.getCreationDate());
		body.setUpdateDate(note.getUpdateDate());
		return body;
	}
	
}
