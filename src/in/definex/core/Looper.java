package in.definex.core;

import in.definex.core.Action.ActionManager;
import in.definex.core.Action.CheckInCurrentGroupAction;
import in.definex.core.Action.CheckOtherGroupForNewAction;
import in.definex.core.Feature.Accounts.AccountsFeature;
import in.definex.core.Feature.Feature;
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
 * Created by adam_ on 30-11-2017.
 */
public class Looper {

    private WebDriver driver;
    private ActionManager actionManager;
    private Checker checker;
    private ExtraLooperFunctions extraLooperFunctions;

    public Looper(ExtraLooperFunctions extraLooperFunctions) {
        this.extraLooperFunctions = extraLooperFunctions;

        this.driver = new ChromeDriver();
        this.driver.get("http://web.whatsapp.com/");
    }

    private void init(){

        actionManager = new ActionManager();
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
                () -> System.out.println("*********** RESETTING CHECKER *************"),
                new CheckInCurrentGroupAction(actionManager, driver, featureManager),
                new CheckOtherGroupForNewAction(actionManager, driver, chatGroupsManager, featureManager)
        );
        extraLooperFunctions.addMoreCheckers(actionManager,featureManager,chatGroupsManager);

    }

    private void loop(){

        if(!actionManager.hasPendingWork())
            actionManager.add(checker.getNextChecker());

        actionManager.popAndPerform();

        Utils.waitFor(500);
    }


    public void start(){
        /*Scanner scanner = new Scanner(System.in);
        System.out.println("Enter to start");
        scanner.nextLine();
        */
        System.out.println("Waiting for Whatsapp web to initialize.");
        while (driver.findElements(By.xpath(XPaths.autoStartReady)).size() == 0);
        Utils.waitFor(1000);

        System.out.println("Program starting.");

        init();
        while(true)
            loop();
    }


    public interface ExtraLooperFunctions {
        void addMoreFeatures(ActionManager actionManager, FeatureManager featureManager, ChatGroupsManager chatGroupsManager);
        void addMoreCheckers(ActionManager actionManager, FeatureManager feature, ChatGroupsManager chatGroupsManager);
    }

}
