package com.app.playerservicejava.service.chat;

import com.app.playerservicejava.model.request.TalkToLLM;
import io.github.ollama4j.OllamaAPI;
import io.github.ollama4j.exceptions.OllamaBaseException;
import io.github.ollama4j.models.Model;
import io.github.ollama4j.models.OllamaResult;
import io.github.ollama4j.models.chat.*;
import io.github.ollama4j.types.OllamaModelType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.github.ollama4j.utils.OptionsBuilder;
import io.github.ollama4j.utils.PromptBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatClientService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChatClientService.class);

    private static final Map<String,List<OllamaChatMessage>> chatHistoryCache=new HashMap<>();

    @Autowired
    private OllamaAPI ollamaAPI;

    public List<Model> listModels() throws OllamaBaseException, IOException, URISyntaxException, InterruptedException {
        return ollamaAPI.listModels();
    }

    public String chat() throws OllamaBaseException, IOException, InterruptedException, URISyntaxException {
        String model = OllamaModelType.TINYLLAMA;
        ollamaAPI.pullModel(model);
        // https://ollama4j.github.io/ollama4j/intro
        PromptBuilder promptBuilder =
                new PromptBuilder()
                        .addLine("Recite a haiku about recursion.");

        boolean raw = false;
        OllamaResult response = ollamaAPI.generate(model, promptBuilder.build(), raw, new OptionsBuilder().build());
        return response.getResponse();
    }

    public void ping() {
        ollamaAPI.ping();
    }


    public String talkToLLM(TalkToLLM request) throws OllamaBaseException, IOException, InterruptedException, URISyntaxException {
        OllamaChatRequestBuilder builder = OllamaChatRequestBuilder.getInstance(OllamaModelType.TINYLLAMA);
        List<OllamaChatMessage> chatHistory = chatHistoryCache.getOrDefault(request.getTalkIdentifier(),new ArrayList<>());
        OllamaChatRequestModel requestModel = builder.withMessages(chatHistory).withMessage(OllamaChatMessageRole.USER, request.getTalkContent()).build();
        OllamaChatResult chatResult = ollamaAPI.chat(requestModel);
        chatHistoryCache.put(request.getTalkIdentifier(),chatResult.getChatHistory());
        System.out.println("Chat History: " + chatHistory);
        return chatResult.getResponse();
    }
}
