package in.definex.core.Feature;

/**
 * Created by adam_ on 01-12-2017.
 */
public abstract class Feature {

    private String name;
    private Command[] commands;

    public Feature(String name, Command[] commands) {
        this.name = name;
        this.commands = commands;
    }

    public String getName() {
        return name;
    }

    public Command findCommandFromKeyword(String keyword){
        for(Command command: commands) {
            if (command.getKeyword().equals(keyword)) {
                return command;
            }
        }
        return null;
    }

    public Command[] getCommands() {
        return commands;
    }

    public abstract String getDescription();


}
