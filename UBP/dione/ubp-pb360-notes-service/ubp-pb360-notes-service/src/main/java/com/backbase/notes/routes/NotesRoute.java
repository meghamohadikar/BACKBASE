package com.backbase.notes.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.backbase.notes.transform.NotesTransformer;
import com.externalnotes.api.spec.NotesApi;

@Component
public class NotesRoute extends RouteBuilder {

	public static final String DIRECT_GET = "notes";

	@Autowired
	NotesApi notesApi;
	
	@Autowired
	NotesTransformer notesTransformer;
	
	@Override
	public void configure() throws Exception {
		 from(DIRECT_GET)
         .routeId("com.backbase.notes.get")
         .bean(notesApi, "notesGet_0(${header.id})")
         .log(LoggingLevel.INFO, "${body.size} notes retrieved")
         .bean(notesTransformer, "transform(${body})")
         .end();

		
	}

}
