package io.podman.desktop;

import java.util.Optional;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class SummarizerService {

    @ConfigProperty(name="aiEnabled")
    Optional<Boolean> aiEnabled;

    @Inject
    AiService aiService;

    public String getSummarization(String text) {
        if (text == null) {
            return "";
        }
        String result = "";
        if (aiEnabled.orElse(Boolean.FALSE)) {
            var blocks = Utils.splitString(text, 2048);
            var response = "";
            for(var block : blocks) {
                response = aiService.request(block); 
                System.out.println(response);
            }
            return response;    
        } else {
            return result;
        }
    }
}
