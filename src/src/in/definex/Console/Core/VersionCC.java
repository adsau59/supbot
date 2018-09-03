package in.definex.Console.Core;

import in.definex.Console.ConsoleCommand;

public class VersionCC extends ConsoleCommand {

    /**
     * Shows version of supbot
     */
    public VersionCC() {
        super("version", 0);
    }

    @Override
    protected String compute(String[] args) {
        return "v0.3.1";
    }
}
