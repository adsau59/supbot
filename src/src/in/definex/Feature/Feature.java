package in.definex.Feature;

/**
 * Features
 * list of commands that comes under one type
 * Abstract class that has to be implemented to create new features
 * view core commands to learn how to implement your own commands
 *
 * Created by adam_ on 01-12-2017.
 */
public abstract class Feature {

    private String name;
    private Command[] commands;

    /**
     * Constructor
     *
     * @param name name of the feature
     * @param commands array of commands
     */
    public Feature(String name, Command[] commands) {
        this.name = name;
        this.commands = commands;
    }

    public String getName() {
        return name;
    }

    /**
     * Find the command from the array, using command keyword
     * @param keyword keyword of the command to be searched
     * @return command object, returns null if not found
     */
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

    /**
     * get Feature description for your feature
     * abstract method has to be implement for your feature
     *
     * @return description of the feature
     */
    public abstract String getDescription();


}
