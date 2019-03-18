package in.definex.ChatSystem.Core;

import in.definex.ChatSystem.Bubble;
import in.definex.ChatSystem.ChatProcessor;
import in.definex.ChatSystem.Client;
import in.definex.Console.Log;

public class LoggingCP extends ChatProcessor {


    public LoggingCP() {
        super(true);
    }

    @Override
    public void task(Bubble bubble, Client client) {
        Log.r(String.format("%s: %s",client.getName(), bubble.getText()));
    }
}
