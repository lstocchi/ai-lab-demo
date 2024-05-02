package io.podman.desktop;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/rn")
@ApplicationScoped
public class ReleaseNoteResource {

    @Inject
    SummarizerService summarizerService;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(String text) {
        return summarizerService.getSummarization(text);
    }
}
