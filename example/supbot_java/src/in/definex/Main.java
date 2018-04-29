package in.definex;


import in.definex.Math.MathFeature;

public class Main {

    public static void main(String[] args) {
	// write your code here


        //Creating the looper and sending code to create components and initializing it
        Looper looper = new Looper(


                new Looper.ExtraLooperFunctions() {
                    @Override
                    public void addThingsInBot() {
                        //add stuff in the bot here,

                        //Bot.getFeatureManager().add(new YourFeature(Parameters params));
                        Bot.getFeatureManager().add(new MathFeature());

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

        //use this method to set your chrome profile
        //looper.setChromeProfileLoc("D:\\Extra\\ChromeProfile");

        //Run the code to initialize components and start the bot.
        //Bot Services can only be used after looper.start()
        looper.start();



        //Wait for the bot to quit.
        looper.join();

    }
}
