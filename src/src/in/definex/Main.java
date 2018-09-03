package in.definex;

import in.definex.Action.ActionManager;
import in.definex.Action.Core.SendMessageAction;

public class Main {

    public static void main(String[] args) {


        //Creating the looper and sending code to create components and initializing it
        Looper looper = new Looper(


                new Looper.ExtraLooperFunctions() {
                    @Override
                    public void addThingsInBot() {
                        //add stuff in the bot here,

                        //Bot.getFeatureManager().add(new YourFeature(Parameters params));

                        //Bot.getChatProcessorManager.add(new YourChatProcessor(Parameters params));

                        //Bot.getChecker().add(new YourCheckers(Parameters params));

                        //Bot.getConsole.add(new YourConsoleCommand(Parameters params));

                        //Bot.getRemoteActionCall().add(YourAction.class);
                    }

                    @Override
                    public void moreInits() {
                        //initialize things here
                    }
                }

        );
        //Run the code to initialize components and start the bot.
        //Bot Services can only be used after looper.start()
        looper.start();

/*
        SendMessageAction sendMessageAction = new SendMessageAction(
                Bot.getChatGroupsManager().findGroupById("group1"),
                "yo +91 90290 90597 how are you?"
        );

        sendMessageAction.tagPhoneNumbers("\\+\\d{2} \\d{5} \\d{5}");

        Bot.getActionManager().add(sendMessageAction);
*/
        //Wait for the bot to quit.
        looper.join();
    }

}
