package in.definex.core;

import in.definex.core.Action.ActionManager;
import in.definex.core.Action.Checker;
import in.definex.core.ChatSystem.ChatGroupsManager;
import in.definex.core.Console.Console;
import in.definex.core.Feature.FeatureManager;
import org.openqa.selenium.WebDriver;

/**
 * Created by adam_ on 03-04-2018.
 */
public class Bot {

    private static Bot me;

    private WebDriver webDriver;
    private ActionManager actionManager;
    private Checker checker;
    private FeatureManager featureManager;
    private Console console;
    private ChatGroupsManager chatGroupsManager;
    private Looper looper;


    public static void CreateBot(WebDriver webDriver, ActionManager actionManager, Checker checker, FeatureManager featureManager, Console console, ChatGroupsManager chatGroupsManager, Looper looper) {
        me = new Bot();

        me.webDriver = webDriver;
        me.actionManager = actionManager;
        me.checker = checker;
        me.featureManager = featureManager;
        me.console = console;
        me.chatGroupsManager = chatGroupsManager;
        me.looper = looper;
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

    public static FeatureManager getFeatureManager() {
        return me.featureManager;
    }

    public static Console getConsole() {
        return me.console;
    }

    public static ChatGroupsManager getChatGroupsManager() {
        return me.chatGroupsManager;
    }

    public static Looper getLooper() {
        return me.looper;
    }
}
