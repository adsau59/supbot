package in.definex;


import in.definex.Console.Log;

public class Main {

    public static void main(String[] args) {
	// write your code here

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
                },
                ""

        );
        looper.start();


        looper.join();



    }

}
