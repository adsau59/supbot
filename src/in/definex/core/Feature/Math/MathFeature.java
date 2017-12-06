package in.definex.core.Feature.Math;

import in.definex.core.Action.ActionManager;
import in.definex.core.Feature.Command;
import in.definex.core.Feature.Feature;
import org.openqa.selenium.WebDriver;

/**
 * Created by adam_ on 01-12-2017.
 */
public class MathFeature extends Feature {

    public static final String name = "Math";

    public MathFeature(ActionManager actionManager, WebDriver driver) {
        super(name, new Command[]{new SolveCommand(actionManager, driver)});
    }

    @Override
    public String getDescription() {
        return "Helps solve simple maths equation";
    }
}
