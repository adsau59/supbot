package in.definex.core.Feature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by adam_ on 01-12-2017.
 */
public class FeatureManager {

    private List<Feature> features;

    public FeatureManager(){
        features = new ArrayList<>();
    }

    public void addFeature(Feature... features){
        Collections.addAll(this.features, features);
    }

    public FeatureAndCommand findFeatureByCommandKeyword(String keyword){

        for(Feature feature:features){
            Command command = feature.findCommandFromKeyword(keyword);

            if(command != null)
                return new FeatureAndCommand(feature, command);
        }
        return null;
    }

    public Feature findFeatureByName(String name){

        for(Feature feature: features){

            if(feature.getName().equals(name))
                return feature;

        }

        return null;

    }

    public String getListOfFeatures(){

        String s = "";

        for(Feature feature:features){
            s+=feature.getName()+"\n";
        }

        return s;
    }



    public class FeatureAndCommand {
        public Feature feature;
        public Command command;

        public FeatureAndCommand(Feature feature, Command command) {
            this.feature = feature;
            this.command = command;
        }
    }

}
