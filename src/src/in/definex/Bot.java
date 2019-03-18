package in.definex;

import in.definex.Action.ActionManager;
import in.definex.Action.Checker;
import in.definex.Action.StringActionInitializer;
import in.definex.ChatSystem.ChatProcessorManager;
import in.definex.ChatSystem.ClientManager;
import in.definex.Console.Console;
import in.definex.Functions.Configuration;
import in.definex.NetworkJob.NetworkJobManager;
import in.definex.Scheduler.ScheduleManager;
import in.definex.Scheduler.ScheduleTaskInitializer;
import org.openqa.selenium.WebDriver;

/**
 * Wrapper class for all the manager class in the bot,
 * can access the managers by using the static getter functions
 *
 *
 * Created by adam_ on 03-04-2018.
 */
public class Bot {

    private static Bot me;

    /**
     * Selenium Web Driver
     * Used to get web elements from the whatsapp web application
     */
    private WebDriver webDriver;

    /**
     * Go to the class to read their description
     */
    private ActionManager actionManager;
    private Checker checker;
    private Console console;
    private Looper looper;
    private ChatProcessorManager chatProcessorManager;
    private StringActionInitializer stringActionInitializer;
    private ScheduleManager scheduleManager;
    private ScheduleTaskInitializer scheduleTaskInitializer;
    private NetworkJobManager networkJobManager;
    private ClientManager clientManager;
    private Configuration configuration;

    static void CreateBot(WebDriver webDriver,
                          ActionManager actionManager,
                          Checker checker,
                          ChatProcessorManager chatProcessorManager,
                          Console console,
                          Looper looper,
                          StringActionInitializer stringActionInitializer,
                          ScheduleManager scheduleManager,
                          NetworkJobManager networkJobManager,
                          ScheduleTaskInitializer scheduleTaskInitializer,
                          ClientManager clientManager,
                          Configuration configuration
    ) {
        me = new Bot();

        me.webDriver = webDriver;
        me.actionManager = actionManager;
        me.checker = checker;
        me.chatProcessorManager = chatProcessorManager;
        me.console = console;
        me.looper = looper;
        me.stringActionInitializer = stringActionInitializer;
        me.scheduleManager = scheduleManager;
        me.networkJobManager = networkJobManager;
        me.scheduleTaskInitializer = scheduleTaskInitializer;
        me.clientManager = clientManager;
        me.configuration = configuration;
    }

    public static WebDriver getWebDriver() {
        return me.webDriver;
    }

    public static ActionManager getActionManager() {
        return me.actionManager;
    }

    public static Checker getChecker() {
        return me.checker;
    }

    public static ChatProcessorManager getChatProcessorManager(){
        return me.chatProcessorManager;
    }


    public static Console getConsole() {
        return me.console;
    }

    public static Looper getLooper() {
        return me.looper;
    }

    public static ClientManager getClientManager() { return me.clientManager; }

    public static StringActionInitializer getRemoteActionCall() {
        return me.stringActionInitializer;
    }

    public static ScheduleManager getScheduleManager() { return me.scheduleManager;}

    public static String getProjectLoc() {
        return System.getProperty("user.dir");
    }

    public static NetworkJobManager getNetworkJobManager() {
        return me.networkJobManager;
    }

    public static ScheduleTaskInitializer getScheduleTaskInitializer(){return me.scheduleTaskInitializer;}

    public static Configuration getConfiguration() { return  me.configuration;}

}
