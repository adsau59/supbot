package in.definex.core.Feature.Help;

import in.definex.core.Action.ActionManager;
import in.definex.core.ChatGroupsManager;
import in.definex.core.Client;
import in.definex.core.Feature.Command;
import in.definex.core.Feature.Feature;
import in.definex.core.String.FeatureAndCommandDescription;
import in.definex.core.String.Strings;
import org.openqa.selenium.WebDriver;

/**
 * Created by adam_ on 04-12-2017.
 */
public class HelpCommand extends Command {

    public static final String name = "help";

    private ChatGroupsManager chatGroupsManager;

    public HelpCommand(ActionManager actionManager, WebDriver driver, ChatGroupsManager chatGroupsManager) {
        super(actionManager, driver, name, 0, Client.Role.Unregistered);
        this.chatGroupsManager = chatGroupsManager;
    }

    @Override
    protected String compute(Client client, String[] args) {
        String availFeatures = "";

        for(Feature feature: client.getChatGroup(chatGroupsManager).getMyFeatures()){
            availFeatures+=feature.getName()+"\n";
        }

        return String.format(Strings.helpResponse, availFeatures);
    }

    @Override
    public Helper getHelper() {
        return new Helper(
                FeatureAndCommandDescription.helpCommandTemplateExample,
                FeatureAndCommandDescription.helpCommandTemplateExample,
                FeatureAndCommandDescription.helpCommandDescription
        );
    }
}
