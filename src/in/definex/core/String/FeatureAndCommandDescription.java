package in.definex.core.String;

import in.definex.core.Feature.Accounts.DemoteCommand;
import in.definex.core.Feature.Accounts.PromoteCommand;
import in.definex.core.Feature.Accounts.RegisterCommand;
import in.definex.core.Feature.GroupConfig.AddFeatureCommand;
import in.definex.core.Feature.GroupConfig.RemoveFeatureCommand;
import in.definex.core.Feature.GroupConfig.RoleCommand;
import in.definex.core.Feature.GroupConfig.ShowAvailableFeatureCommand;
import in.definex.core.Feature.Help.HelpCommand;
import in.definex.core.Feature.Help.HelpCommandCommand;
import in.definex.core.Feature.Help.HelpFeatureCommand;
import in.definex.core.Feature.Math.MathFeature;
import in.definex.core.Feature.Math.SolveCommand;

/**
 * Created by adam_ on 04-12-2017.
 */
public class FeatureAndCommandDescription {

    public static final String groupConfigDescription = "Configure group settings for the bot.";
    public static final String showAvailDescription = "Shows available features you can add to the group.";
    public static final String showAvailTemplateExample = Strings.commandPrefix+ ShowAvailableFeatureCommand.name;
    public static final String addFeatureDescription = "Add feature to the group.";
    public static final String addFeatureTemplate = Strings.commandPrefix+ AddFeatureCommand.name+" [feature_name]";
    public static final String addFeatureExample = Strings.commandPrefix+AddFeatureCommand.name+" "+ MathFeature.name;
    public static final String removeFeatureDescription = "Remove feature from the group";
    public static final String removeFeatureTemplate = Strings.commandPrefix+ RemoveFeatureCommand.name+" [feature_name]";
    public static final String removeFeatureExample = Strings.commandPrefix+RemoveFeatureCommand.name+" "+MathFeature.name;

    public static final String accountsDescription = "Register, Promote, Demote, Account Management for the group.";
    public static final String registerDescription = "Register group member for the group";
    public static final String registerTemplateExample = Strings.commandPrefix+ RegisterCommand.name;
    public static final String promoteDescription = "Promote group member";
    public static final String promoteTemplate = Strings.commandPrefix+ PromoteCommand.name+" [member-reference]";
    public static final String promoteExample = Strings.commandPrefix+ PromoteCommand.name+" @Adam";
    public static final String demoteDescription = "Demote group member";
    public static final String demoteTemplate = Strings.commandPrefix+ DemoteCommand.name+" [member-reference]";
    public static final String demoteExample = Strings.commandPrefix+DemoteCommand.name+" @Adam";
    public static final String roleDescription = "Shows role of target member.";
    public static final String roleTemplate = Strings.commandPrefix+ RoleCommand.name+" [member-reference]";
    public static final String roleExample = Strings.commandPrefix+RoleCommand.name+" @Adam";

    public static final String helpDescription = "Gives description of the commands that can be used and how to use it.";
    public static final String helpCommandDescription = "Shows available features in the group.";
    public static final String helpCommandTemplateExample = Strings.commandPrefix+ HelpCommand.name;
    public static final String helpFeatureDescription = "Shows commands in the feature.";
    public static final String helpFeatureTemplate = Strings.commandPrefix+ HelpFeatureCommand.name+" [feature-name]";
    public static final String helpFeatureExample = Strings.commandPrefix+HelpFeatureCommand.name+" "+MathFeature.name;
    public static final String helpCommandCommandDescription = "Shows description, usage and example of a command.";
    public static final String helpCommandCommandTemplate = Strings.commandPrefix+ HelpCommandCommand.name+" [command]";
    public static final String helpCommandCommandExample = Strings.commandPrefix+HelpCommandCommand.name+" "+ SolveCommand.name;


}
