package in.definex.core;

import in.definex.core.Action.ActionManager;
import in.definex.core.Action.Checker;
import in.definex.core.Action.Core.CheckInCurrentGroupAction;
import in.definex.core.Action.Core.CheckOtherGroupForNewAction;
import in.definex.core.ChatSystem.ChatGroupsManager;
import in.definex.core.ChatSystem.ChatProcessorManager;
import in.definex.core.ChatSystem.Core.CommandCP;
import in.definex.core.Console.Console;
import in.definex.core.Console.Core.LogCC;
import in.definex.core.Console.Core.GroupCC;
import in.definex.core.Console.Core.RunCC;
import in.definex.core.Console.Log;
import in.definex.core.Console.Core.QuitCC;
import in.definex.core.Database.Configuration;
import in.definex.core.Database.Core.ChatGroupDatabase;
import in.definex.core.Database.Core.ClientDatabase;
import in.definex.core.Database.DatabaseManager;
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



    private ExtraLooperFunctions extraLooperFunctions;
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


        WebDriver driver = new ChromeDriver();
        driver.get("http://web.whatsapp.com/");

        ActionManager actionManager = new ActionManager();
        Console console = new Console(this);
        FeatureManager featureManager = new FeatureManager();
        ChatGroupsManager chatGroupsManager = new ChatGroupsManager();
        Checker checker = new Checker(
                () -> Log.a("RESETTING CHECKERS")
        );

        Bot.CreateBot(driver, actionManager, checker, featureManager, new ChatProcessorManager(), console, chatGroupsManager, this,new DatabaseManager(), new Configuration());

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


        //core features
        Bot.getFeatureManager().addFeature(
                new MathFeature(),
                new HelpFeature(),
                new GroupConfigFeature(),
                new AccountsFeature()
        );


        //core checkers
        Bot.getChecker().addCheckers(
                new CheckInCurrentGroupAction(),
                new CheckOtherGroupForNewAction()
        );
        //coreConsoleCommands
        Bot.getConsole().getConsoleCommandManager().add(
            new QuitCC(),
                new GroupCC(),
                new LogCC(),
                new RunCC()
        );

        //core database
        Bot.getDatabaseManager().add(
                new ChatGroupDatabase(),
                new ClientDatabase()
        );

        //core ChatProcessors
        Bot.getChatProcessorManager().add(
                new CommandCP()
        );

        extraLooperFunctions.addThingsInBot();

        //init managers
        Bot.getDatabaseManager().init();
        Bot.getChatGroupsManager().loadGroups();
        Log.init();

        extraLooperFunctions.moreInits();
    }

    /***
     * Loop ran by checkerAndActionThread
     */
    private void loop(){
        if(!Bot.getActionManager().hasPendingWork())
            Bot.getActionManager().add(Bot.getChecker().getNextChecker());

        Bot.getActionManager().popAndPerform();

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
        while (Bot.getWebDriver().findElements(By.xpath(XPaths.autoStartReady)).size() == 0);
        Utils.waitFor(1000);

        System.out.println("Program starting.");

        init();

        checkerAndActionThread.start();
        Bot.getConsole().getMyThread().start();


        try {
            checkerAndActionThread.join();
            Bot.getConsole().getMyThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Interface made to pass initialization of
     * feature, check and console commands from main
     */
    public interface ExtraLooperFunctions {
        void addThingsInBot();
        void moreInits();
    }

}
