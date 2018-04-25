package in.definex;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Looper looper = new Looper(new Looper.ExtraLooperFunctions() {
            @Override
            public void addThingsInBot() {

            }

            @Override
            public void moreInits() {

            }
        }, "");

        looper.start();
        looper.join();

    }
}
