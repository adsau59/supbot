package in.definex;

import in.definex.Action.ActionManager;
import in.definex.Action.Checker;
import in.definex.Action.Core.Checker.CheckInCurrentGroupAction;
import in.definex.Action.Core.Checker.CheckOtherGroupForNewAction;
import in.definex.Action.Core.MoveToChatAction;
import in.definex.Action.Core.SendMessageAction;
import in.definex.Action.StringActionInitializer;
import in.definex.ChatSystem.ChatGroupsManager;
import in.definex.ChatSystem.ChatProcessorManager;
import in.definex.ChatSystem.Core.CommandCP;
import in.definex.Console.Console;
import in.definex.Console.Core.*;
import in.definex.Console.Log;
import in.definex.Database.Configuration;
import in.definex.Database.Core.ChatGroupDatabase;
import in.definex.Database.Core.ClientDatabase;
import in.definex.Database.DatabaseManager;
import in.definex.Feature.Accounts.AccountsFeature;
import in.definex.Feature.FeatureManager;
import in.definex.Feature.GroupConfig.GroupConfigFeature;
import in.definex.Feature.Help.HelpFeature;
import in.definex.Functions.Utils;
import in.definex.NetworkJob.NetworkJobManager;
import in.definex.Scheduler.ScheduleDatabase;
import in.definex.Scheduler.ScheduleManager;
import in.definex.Scheduler.ScheduleTaskInitializer;
import in.definex.String.Strings;
import in.definex.String.XPaths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

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

    private String chromeProfileLoc;

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

        checkerAndActionThread = new Thread("CheckerActionThread"){
            @Override
            public void run() {
                while(!quit)
                    loop();
            }
        };
    }

    public void setChromeProfileLoc(String chromeProfileLoc) {
        this.chromeProfileLoc = chromeProfileLoc;
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
        WebDriver driver;

        if(chromeProfileLoc == null || !chromeProfileLoc.isEmpty()) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("user-data-dir="+chromeProfileLoc);
            driver = new ChromeDriver(options);
        }
        else
            driver = new ChromeDriver();
        driver.get("http://web.whatsapp.com/");
        Bot.CreateBot(
                driver,
                new ActionManager(),
                new Checker(() -> Log.a("RESETTING CHECKERS")),
                new FeatureManager(),
                new ChatProcessorManager(),
                new Console(this),
                new ChatGroupsManager(),
                this,
                new DatabaseManager(),
                new Configuration(),
                new StringActionInitializer(),
                new ScheduleManager(),
                new NetworkJobManager(),
                new ScheduleTaskInitializer()
        );
        Strings.commandPrefix = Bot.getConfiguration().GetConfig("command_prefix",";;");
        Strings.titlePrefix = Bot.getConfiguration().GetConfig("group_title_prefix", ";;");

        

        System.out.println("Waiting for Whatsapp web to initialize.");
        while (Bot.getWebDriver().findElements(By.xpath(XPaths.autoStartReady)).size() == 0) Utils.waitFor(500);
        Utils.waitFor(1000);

        System.out.println("Program starting.");

        init();

        checkerAndActionThread.start();
        Bot.getConsole().getMyThread().start();
    }

    /**
     * Initializes ActionManger, FeatureManager, ChatgroupManager, Console with core objects
     * also initializes non core objects
     *
     * Ran inside start function
     */
    private void init(){

        //core features
        Bot.getFeatureManager().add(
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
                new RunCC(),
                new ActionCallerCC(),
                new CheckerCallerCC(),
                new VersionCC(),
                new HelpCC(),
                new ScheduleCC()
        );

        //core database
        Bot.getDatabaseManager().add(
                new ChatGroupDatabase(),
                new ClientDatabase(),
                new ScheduleDatabase()
        );

        //core ChatProcessors
        Bot.getChatProcessorManager().add(
                new CommandCP()
        );

        Bot.getRemoteActionCall().add(
                MoveToChatAction.class,
                SendMessageAction.class
        );

        extraLooperFunctions.addThingsInBot();

        //init managers
        Bot.getDatabaseManager().init();
        Bot.getChatGroupsManager().loadGroups();
        Log.init();
        Bot.getScheduleManager().init();

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


    public void join(){
        try {
            checkerAndActionThread.join();
            Bot.getConsole().getMyThread().join();
            Bot.getScheduleManager().cancelAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Bot.getWebDriver().quit();
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
