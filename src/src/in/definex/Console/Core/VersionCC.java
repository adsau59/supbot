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
    public String compute(String[] args) {
        return "v0.3.4";
    }

    @Override
    public Helper getHelper() {
        return new Helper(
                "version",
                "version",
                "Shows version of supbot"
        );
    }
}
