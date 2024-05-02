package io.podman.desktop;

import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService(chatMemoryProviderSupplier = RegisterAiService.NoChatMemoryProviderSupplier.class)
public interface AiService {

    static final String PROMPT = """
        Summarize the following text
        ------------
        {text}
        ------------
            """;
    @UserMessage(PROMPT)
    String request(String text);
}
