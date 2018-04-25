package in.definex.Feature.Math;

import in.definex.Action.ActionManager;
import in.definex.Bot;
import in.definex.Feature.Command;
import in.definex.Feature.Feature;
import org.openqa.selenium.WebDriver;

/**
 * Created by adam_ on 01-12-2017.
 */
public class MathFeature extends Feature {

    public static final String name = "Math";

    public MathFeature() {
        super(name, new Command[]{new SolveCommand()});
    }

    @Override
    public String getDescription() {
        return "Helps solve simple maths equation";
    }
}
