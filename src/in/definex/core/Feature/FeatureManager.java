package in.definex.core.Feature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * FeatureManage
 * Manages the list of features in the bot
 *
 * Created by adam_ on 01-12-2017.
 */
public class FeatureManager {

    private List<Feature> features;

    /**
     * Constructor
     * Creates FeatureManager with no features
     */
    public FeatureManager(){
        features = new ArrayList<>();
    }

    /**
     * add new features to the feature manager,
     * called in init method in Looper class
     *
     * @param features
     */
    public void addFeature(Feature... features){
        Collections.addAll(this.features, features);
    }

    /**
     * Find feature by command keyword
     * @param keyword keyword of the command to be search
     * @return FeatureAndCommand Object
     */
    public FeatureAndCommand findFeatureByCommandKeyword(String keyword){

        for(Feature feature:features){
            Command command = feature.findCommandFromKeyword(keyword);

            if(command != null)
                return new FeatureAndCommand(feature, command);
        }
        return null;
    }

    /**
     * Find feature by its name
     * @param name name of the feature to be returned
     * @return Feature Object
     */
    public Feature findFeatureByName(String name){

        for(Feature feature: features){

            if(feature.getName().equals(name))
                return feature;

        }

        return null;

    }

    /**
     * Returns the list of features in string
     * @return string
     */
    public String getListOfFeatures(){

        String s = "";

        for(Feature feature:features){
            s+=feature.getName()+"\n";
        }

        return s;
    }


    /**
     * FeatureAndCommand
     * Wrapper class for Feature and a Command
     */
    public class FeatureAndCommand {
        public Feature feature;
        public Command command;

        public FeatureAndCommand(Feature feature, Command command) {
            this.feature = feature;
            this.command = command;
        }
    }

}
