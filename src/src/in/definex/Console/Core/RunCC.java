package in.definex.Console.Core;

import in.definex.Bot;
import in.definex.ChatSystem.ChatGroup;
import in.definex.ChatSystem.Client;
import in.definex.Console.ConsoleCommand;
import in.definex.Feature.FeatureManager;

import java.util.Arrays;


/**
 * RunCC
 * Run Bot commands using console,
 * with no feature or role restriction
 *
 * run <groupid> <command> <args>
 *
 * example: run yo solve 5+3
 */

public class RunCC extends ConsoleCommand {


    public RunCC() {
        super("run", -1);
    }

    @Override
    public String compute(String[] args) {

        if(args.length < 2)
            return "Insufficient Arguments";

        FeatureManager.FeatureAndCommand featureAndCommand = Bot.getFeatureManager().findFeatureByCommandKeyword(args[1]);

        if(featureAndCommand == null)
            return "Incorrect command";

        ChatGroup chatGroup = Bot.getChatGroupsManager().findGroupById(args[0]);

        if(chatGroup == null)
            return "Chat group doesn't exist";

        Client client = Client.getSuperClient(chatGroup.getGroupId());

        featureAndCommand.command.proccess(chatGroup, client, Arrays.copyOfRange(args, 2, args.length));

        return "Command Initiated successfully, response will be sent in the group";
    }

    @Override
    public Helper getHelper() {
        return new Helper(
                "run <groupid> <command> <args>",
                "run group1 promote Adam",
                "Run Bot commands using console,\n" +
                        "with no feature or role restriction"
        );
    }
}
