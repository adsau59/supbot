package in.definex;

import in.definex.core.Action.ActionManager;
import in.definex.core.ChatGroupsManager;
import in.definex.core.Feature.Feature;
import in.definex.core.Feature.FeatureManager;
import in.definex.core.Looper;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Looper looper = new Looper(new Looper.ExtraLooperFunctions() {
            @Override
            public void addMoreFeatures(ActionManager actionManager, FeatureManager featureManager, ChatGroupsManager chatGroupsManager) {

            }

            @Override
            public void addMoreCheckers(ActionManager actionManager, FeatureManager feature, ChatGroupsManager chatGroupsManager) {

            }


        });
        looper.start();
    }
}
