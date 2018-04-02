package in.definex.core;

import in.definex.core.Action.ActionManager;
import in.definex.core.Action.Core.CheckInCurrentGroupAction;
import in.definex.core.Action.Core.CheckOtherGroupForNewAction;
import in.definex.core.Console.Console;
import in.definex.core.Console.Log;
import in.definex.core.Console.Core.QuitCC;
import in.definex.core.Feature.Accounts.AccountsFeature;
import in.definex.core.Feature.FeatureManager;
import in.definex.core.Feature.GroupConfig.GroupConfigFeature;
import in.definex.core.Feature.Help.HelpFeature;
import in.definex.core.Feature.Math.MathFeature;
import in.definex.core.Functions.Utils;
import in.definex.core.String.XPaths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Looper class
 *
 * Initializes features, checkers, console commands,
 * creates and runs checkerAndActionThread thread and console command threads
 *
 * Created by adam_ on 30-11-2017.
 */
public class Looper {


    private WebDriver driver;
    private ActionManager actionManager;
    private Checker checker;
    private ExtraLooperFunctions extraLooperFunctions;
    private Console console;
    private Thread checkerAndActionThread;
    private boolean quit = false;

    /**
     * Status of the program.
     *
     * @return true if the program has quit.
     */
    public boolean isQuit() { return quit; }

    /**
     * Called to exit the program
     */
    public void quit() { quit = true; }

    /**
     * Constructor,
     * Creates checkerAndActionThread thread,
     * starts selenium
     *
     * @param extraLooperFunctions passed from the main Class,
     *                             contains initialization of checker, features and console commands
     */
    public Looper(ExtraLooperFunctions extraLooperFunctions) {
        this.extraLooperFunctions = extraLooperFunctions;

        this.driver = new ChromeDriver();
        this.driver.get("http://web.whatsapp.com/");

        checkerAndActionThread = new Thread("CheckerActionThread"){
            @Override
            public void run() {
                while(!quit)
                    loop();
            }
        };
    }

    /***
     * Initializes ActionManger, FeatureManager, ChatgroupManager, Console with core objects
     * also initializes non core objects
     *
     * Ran inside start function
     */
    private void init(){

        actionManager = new ActionManager();
        console = new Console(this);

        FeatureManager featureManager = new FeatureManager();
        ChatGroupsManager chatGroupsManager = new ChatGroupsManager();


        //core features
        featureManager.addFeature(
                new MathFeature(actionManager,driver),
                new HelpFeature(actionManager, driver, chatGroupsManager, featureManager),
                new GroupConfigFeature(actionManager, driver, featureManager, chatGroupsManager),
                new AccountsFeature(actionManager, driver)
        );
        extraLooperFunctions.addMoreFeatures(actionManager,featureManager, chatGroupsManager);


        chatGroupsManager.loadGroups(featureManager, driver);


        //core checkers
        checker = new Checker(
                () -> Log.a("RESETTING CHECKERS"),
                new CheckInCurrentGroupAction(actionManager, driver, featureManager),
                new CheckOtherGroupForNewAction(actionManager, driver, chatGroupsManager, featureManager)
        );
        extraLooperFunctions.addMoreCheckers(checker,actionManager,featureManager,chatGroupsManager);

        //coreConsoleCommands
        console.getConsoleCommandManager().add(
            new QuitCC(this)
        );
        extraLooperFunctions.addMoreConsoleCommands(console);
    }

    /***
     * Loop ran by checkerAndActionThread
     */
    private void loop(){
        if(!actionManager.hasPendingWork())
            actionManager.add(checker.getNextChecker());

        actionManager.popAndPerform();

        Utils.waitFor(500);
    }

    /***
     * Method called to start the program from main
     *
     * Waits wait for whatsapp to initialize (scan qr code)
     * then runs the init() method
     * then runs checkerAndActionThread and console thread
     * then waits for the threads to exit
     */
    public void start(){
        System.out.println("Waiting for Whatsapp web to initialize.");
        while (driver.findElements(By.xpath(XPaths.autoStartReady)).size() == 0);
        Utils.waitFor(1000);

        System.out.println("Program starting.");

        init();

        checkerAndActionThread.start();
        console.getMyThread().start();


        //todo implement way to break for loop
        try {
            checkerAndActionThread.join();
            console.getMyThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Interface made to pass initialization of
     * feature, check and console commands from main
     */
    public interface ExtraLooperFunctions {
        void addMoreFeatures(ActionManager actionManager, FeatureManager featureManager, ChatGroupsManager chatGroupsManager);
        void addMoreCheckers(Checker checker, ActionManager actionManager, FeatureManager feature, ChatGroupsManager chatGroupsManager);
        void addMoreConsoleCommands(Console console);
    }

}
