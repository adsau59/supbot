package in.definex.core.ChatSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ChatProcessorManager
 * Manages all the ChatProcessor
 */
public class ChatProcessorManager {

    private List<ChatProcessor> chatProcessors;

    public ChatProcessorManager(){
        chatProcessors = new ArrayList<>();
    }

    /**
     * Used to add chatprocessor
     * @param chatProcessors chatprocessors
     */
    public void add(ChatProcessor... chatProcessors){
        this.chatProcessors.addAll(Arrays.asList(chatProcessors));
    }

    /**
     * Called by checker to run all the chat processors
     * @param bubble Whatsapp chat webelement
     * @param client client who sent the chat
     */
    public void process(Bubble bubble, Client client){
        for(ChatProcessor c:chatProcessors)
            c.process(bubble,client);
    }

}
