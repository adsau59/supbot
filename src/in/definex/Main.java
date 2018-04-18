package in.definex;

import com.google.gson.Gson;
import in.definex.core.Console.Console;
import in.definex.core.Console.Log;
import in.definex.core.Database.Configuration;
import in.definex.core.Database.Database;
import in.definex.core.Looper;

import java.lang.reflect.Field;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Looper looper = new Looper(new Looper.ExtraLooperFunctions() {
            @Override
            public void addThingsInBot() {
                //add stuff in the bot here,

                //Bot.getFeatureManager().add(new YourFeature(Parameters params));

                //Bot.getChatProcessorManager.add(new YourChatProcessor(Parameters params));

                //Bot.getChecker().add(new YourCheckers(Parameters params));

                //Bot.getConsole.add(new YourConsoleCommand(Parameters params));
            }

            @Override
            public void moreInits() {
                //initialize things here
            }
        });
        looper.start();


    }
}
