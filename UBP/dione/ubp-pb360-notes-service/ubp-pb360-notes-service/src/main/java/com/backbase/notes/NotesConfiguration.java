package com.backbase.notes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.externalnotes.api.spec.NotesApi;

@Configuration
public class NotesConfiguration {

	@Bean
	public NotesApi getNotesApi() {
		return new NotesApi();
	}
}
