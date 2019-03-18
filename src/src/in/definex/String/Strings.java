package in.definex.String;


/**
 * Strings
 * response strings for commands
 *
 * Created by adam_ on 30-11-2017.
 */
public class Strings {

    public static String commandPrefix = ";;";
    public static String titlePrefix = ";;";
    public static final String dbListSeperator = ",";

    public static final String inSufficiantPermission = "You don't have Sufficiant Permission.";

    public static final String commandDoesntExists = "Command doesnt exists";
    public static final String commandNotAllowedInThisGroup = "Command not allowed in this group, request the admin to add it!!!";

    public static final String allReadyRegistered = "You have already been register.";
    public static final String successfullyRegisteredFormat = "Successfully Registered %s.";
    public static final String targetClientIsNotRegistered = "Target client is not registered yet.";
    public static final String youDontHaveAuthorityToPromote = "You don't have autority to promote this member.";
    public static final String thereCanBeOnlyOneAdminInAGroup = "There can only be on admin in a group.";
    public static final String thereIsNoRoleAboveAdmin = "There is no role above admin.";
    public static final String dontPromoteYourself = "Don't try to promote yourself";
    public static final String successfullyChangedRole = "Successfully changed role of %s from %s to %s.";
    public static final String cantDemoteMember = "Member is the least role, can't demote further.";
    public static final String youDontHaveAuthorityToDemote = "You don't have autority to demote this member.";
    public static final String cantDemoteAdmin = "Can't demote Admin";
    public static final String dontDemoteYourself = "Why do you want to demote yourself?";
    public static final String roleResponse = "Role of %s is %s.";

    public static final String featureDoesntExisits = "Requested feature Does'nt exists.";
    public static final String featureAlreadyAdded = "Requested feature is already added in the group.";
    public static final String succesffullyAddedFeature = "Feature %s has been successfully added to the group.";
    public static final String featureHasNotBeenAdded = "Requested feature has not been added to the group yet or has already been removed.";
    public static final String successfullyRemovedFeature = "Feature %s has been successfully removed from the group.";

    public static final String helpResponse = "SUPBOT HELP\nThese are the available features in the group.\n%s\nto find out more about these commands use "+Strings.commandPrefix+"help-feature [feature-name] command";
    public static final String helpFeatureResponse = "%s\n%s\nCommands:\n%s";
    public static final String helpCommandResponse = "%s\n%s\nUsage:\n%s\nExample:\n%s\nMinimum Role:\n%s";

    public static final String helpConsoleCommandFormate = "" +
            "%s\n" +
            "%s\n" +
            "\n" +
            "Usage:\n" +
            "%s\n" +
            "\n" +
            "Example:\n" +
            "%s";



    public static String fromReferenceToClientName(String refernce){
        return String.format("+%s %s %s", refernce.subSequence(0,2), refernce.subSequence(2,7), refernce.subSequence(7,12));
    }

}
