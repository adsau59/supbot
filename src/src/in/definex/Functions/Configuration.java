package in.definex.Functions;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import in.definex.Console.Log;
import java.io.*;


/**
 * Configuration
 * Used to save name value pair in a json file,
 * and loaded when needed
 */
public class Configuration {

    /**
     * Json file for configuration on disk.
     */
    private static final String configFile = "config.json";

    /**
     * Generalized private method to save a name value pair in the configuration
     * @param name Name
     * @param obj Value
     * @return true, if successful
     */
    private boolean SaveConfig(String name, Object obj){
        JsonObject jsonObject;
        try {
            jsonObject = ReadJsonObject();
        }catch (FileNotFoundException | IllegalStateException e){
            Log.e("Configuration file missing or not valid, creating new");
            jsonObject = new JsonObject();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }


        if(obj.getClass() == String.class){
            jsonObject.addProperty(name, (String) obj);
        }else if(obj.getClass() == Integer.class){
            jsonObject.addProperty(name, (Integer) obj);
        }else if(obj.getClass() == Float.class){
            jsonObject.addProperty(name, (Float) obj);
        }else if(obj.getClass() == Boolean.class){
            jsonObject.addProperty(name, (Boolean) obj);
        }else{
            Log.e("Invalid type, "+obj.getClass().getName()+" in configuration");
            return false;
        }

        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter(configFile));
            writer.write(jsonObject.toString());
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;

    }

    /**
     * String/int/float/boolean overload to save configuration
     */
    public boolean SaveConfig(String name, String str){ return SaveConfig(name, (Object)str); }
    public boolean SaveConfig(String name, int i){ return SaveConfig(name, (Object)i); }
    public boolean SaveConfig(String name, float f){ return SaveConfig(name, (Object)f); }
    public boolean SaveConfig(String name, boolean b){ return SaveConfig(name, (Object)b); }

    /**
     * Generalized private method to get value from the saved name value pair configuration
     * @param name name
     * @param _defautlt default value
     * @return returns value from the configuration, returns _default if not found
     */
    private Object GetConfig(String name, Object _defautlt){
        try {
            JsonObject jsonObject = ReadJsonObject();

            JsonElement result = jsonObject.get(name);

            if(_defautlt.getClass() == String.class)
                return result.getAsString();

            if(_defautlt.getClass() == Integer.class)
                return result.getAsInt();

            if(_defautlt.getClass() == Float.class)
                return result.getAsFloat();

            if(_defautlt.getClass() == Boolean.class)
                return result.getAsBoolean();

        } catch (IOException | NullPointerException e) {
            Log.p(e);
            //Log.e("Could not find config file or config, returning default");
        }
        return _defautlt;
    }


    /**
     * String/int/float/boolean overload to get configuration
     */
    public String GetConfig(String name, String _default){ return (String) GetConfig(name, (Object)_default); }
    public Integer GetConfig(String name, Integer _default){ return (Integer) GetConfig(name, (Object)_default); }
    public Float GetConfig(String name, Float _default){ return (Float) GetConfig(name, (Object)_default); }
    public Boolean GetConfig(String name, Boolean _default){ return (Boolean) GetConfig(name, (Object)_default); }


    /**
     * Helper function to read config json file
     * @return json object from the config file
     * @throws IOException
     */
    private static JsonObject ReadJsonObject() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(configFile));
        String jsonString = "";
        String thisLine;

        while ((thisLine = br.readLine()) != null){
            jsonString+=thisLine;
        }

        return (new JsonParser()).parse(jsonString).getAsJsonObject();
    }

}
