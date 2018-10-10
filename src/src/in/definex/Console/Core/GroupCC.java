package in.definex.Console.Core;

import in.definex.Bot;
import in.definex.ChatSystem.ChatGroup;
import in.definex.Console.ConsoleCommand;

/**
 * Add, Remove, Show groups that can be recognized by the bot
 *
 * use "group show" to show all current groups
 * use "group add <gid>" to add new group with groupid gid
 * use "group remove <gid>" to remove a group with groupid gid
 *
 * example: group add yo
 *
 * Created by adam_ on 03-04-2018.
 */
public class GroupCC extends ConsoleCommand {


    public GroupCC() {
        super("group", -1);
    }

    @Override
    public String compute(String[] args) {

        String gid;
        ChatGroup chatGroup;

        switch (args[0]){

            case "show":

                String result = "";
                for(ChatGroup c: Bot.getChatGroupsManager().getChatGroupList()){
                    result += c.getGroupId()+"\n";
                }

                return result;

            case "add":
                gid = args[1];
                chatGroup = Bot.getChatGroupsManager().findGroupById(gid);

                if(chatGroup != null)
                    return "ChatGroup already exists";

                ChatGroup newChatGroup = new ChatGroup(gid, Bot.getFeatureManager().getCoreFeatures());
                Bot.getChatGroupsManager().add(newChatGroup);

                return "New ChatGroup Id have been added";

            case "remove":
                gid = args[1];
                chatGroup = Bot.getChatGroupsManager().findGroupById(gid);

                if(chatGroup == null)
                    return "ChatGroup doesnt exists";

                ChatGroup deleteChatGroup = Bot.getChatGroupsManager().findGroupById(gid);
                Bot.getChatGroupsManager().remove(deleteChatGroup);

                return "ChatGroup successfully deleted";

            default:
                return "Invalid Arguments, use 'show', 'add' or 'remove'";

        }
    }
    @Override
    public Helper getHelper() {
        return new Helper(
                "group show\n" +
                        "group add <gid>\n" +
                        "group remove <gid>",
                "group add group1",
                "Add, Remove, Show groups that can be recognized by the bot"
        );
    }


}
