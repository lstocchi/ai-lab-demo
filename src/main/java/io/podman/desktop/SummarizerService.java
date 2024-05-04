package io.podman.desktop;

import java.util.Optional;
import java.util.function.Consumer;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.model.StreamingResponseHandler;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.mistralai.MistralAiStreamingChatModel;
import dev.langchain4j.model.output.Response;
import jakarta.inject.Singleton;

@Singleton
public class SummarizerService {

    @ConfigProperty(name="aiEnabled")
    Optional<Boolean> aiEnabled;

    @ConfigProperty(name="quarkus.langchain4j.mistralai.base-url")
    String baseUrl;

    @ConfigProperty(name="modelName")
    String modelName;

    public void getSummarization(String text, Consumer<String> consumer) {
        if (text == null) {
            return;
        }

        StreamingChatLanguageModel model = 
        new MistralAiStreamingChatModel(baseUrl, "sk-dummy", modelName, null, null, null, null, null, "text", null, null, null);
        
        model.generate("Summarize this text: " + text, new StreamingResponseHandler<AiMessage>() {

            @Override
            public void onNext(String token) {
                // TODO Auto-generated method stub
                consumer.accept(token);
            }

            @Override
            public void onComplete(Response<AiMessage> response) {
                
            }

            @Override
            public void onError(Throwable error) {
                // TODO Auto-generated method stub
                System.out.println("error");
                
            }
            
        });
    }
}
