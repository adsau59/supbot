package in.definex;

import in.definex.core.Looper;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Looper looper = new Looper(new Looper.ExtraLooperFunctions() {
            @Override
            public void addInits() {
                //Bot.getFeatureManager().add(new YourFeature(Parameters params));

                //Bot.getChecker().add(new YourCheckers(Parameters params));

                //Bot.getConsole.add(new YourConsoleCommand(Parameters params));
            }
        });
        looper.start();
    }
}
