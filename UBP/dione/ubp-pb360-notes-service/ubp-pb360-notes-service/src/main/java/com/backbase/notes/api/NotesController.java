package com.backbase.notes.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.backbase.notes.rest.spec.v1.notes.id.NotesidApi;
import com.backbase.notes.rest.spec.v1.notes.id.NotesidGetResponseBody;
import com.backbase.notes.routes.NotesRoute;

@RestController
public class NotesController implements NotesidApi{

	@EndpointInject(uri = NotesRoute.DIRECT_GET)
    private ProducerTemplate notesRoute;
	
	@Override
	public NotesidGetResponseBody getNotesid(String id, HttpServletRequest request, HttpServletResponse response) {
		return (NotesidGetResponseBody) notesRoute.requestBodyAndHeader(null, "id", id);
	}


}
