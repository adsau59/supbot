package in.definex.core.Console;

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
            return "Doesn't have sufficent arguments";

        return compute(args);

    }

    /**
     * abstract method to be implemented for new Commands
     *
     * @param args arguments used in console
     * @return response showed in the console
     */
    protected abstract String compute(String[] args);


}
