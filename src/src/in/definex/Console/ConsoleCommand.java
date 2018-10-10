package in.definex.Console;

import in.definex.String.Strings;

/**
 * ConsoleCommand
 * abstract ConsoleCommand class
 * implemented for new commands
 * refer core commands to learn how to implement
 *
 * Created by adam_ on 16-03-2018.
 */
public abstract class ConsoleCommand {

    private String keyword;
    private int noOfArgs; //-1 for variable args

    /**
     * Constructor
     *
     * @param keyword keyword used to run the command
     * @param noOfArgs number of arguments needed to run the command (-1 for variable arguments)
     */
    public ConsoleCommand(String keyword, int noOfArgs) {
        this.keyword = keyword;
        this.noOfArgs = noOfArgs;
    }

    public String getKeyword() {
        return keyword;
    }

    /**
     * Checks if has sufficient arguments
     * @param args arguments used in console
     * @return true if has sufficient arugments
     */
    private boolean doesHaveSufficientArgs(String[] args){
        return noOfArgs == -1 || args.length == noOfArgs;
    }

    /**
     * checks if has sufficientsarguments if yes then compute and return result
     * @param args arguments used in console
     * @return response showed in the console
     */
    String runCommand(String[] args){

        if(!doesHaveSufficientArgs(args))
            return "Doesn't have sufficent arguments\n\n"+
                    String.format(
                            Strings.helpConsoleCommandFormate,
                            this.getClass().getSimpleName(),
                            getHelper().description,
                            getHelper().template,
                            getHelper().example);

        return compute(args);

    }

    /**
     * abstract method to be implemented for new Commands
     *
     * @param args arguments used in console
     * @return response showed in the console
     */
    public abstract String compute(String[] args);


    /**
     * Helper class including template, example and description for the console command
     */
    public class Helper{
        public String template;
        public String example;
        public String description;

        public Helper(String template, String example, String description) {
            this.template = template;
            this.example = example;
            this.description = description;
        }
    }

    /**
     * abstract method to be implemented for new COmmands
     *
     * @return Helper class including template, example and description for the console command created
     */
    public abstract Helper getHelper();


}
