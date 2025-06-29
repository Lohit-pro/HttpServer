package org.socgen.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.socgen.exception.HttpConfigurationException;
import org.socgen.utils.Json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigurationManager {

    private static ConfigurationManager myConfigurationManager;
    private static Configuration myConfiguration;

    private ConfigurationManager() {}

    public static ConfigurationManager getInstance(){
        if (myConfigurationManager == null)
            myConfigurationManager = new ConfigurationManager();

        return myConfigurationManager;
    }

    /**
     * Method to load a json configuration file
     * @param filePath : Path of the new json config file
     */
    public void loadConfigurationFile(String filePath) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new HttpConfigurationException(e);
        }
        StringBuilder sb = new StringBuilder();

        int i;
        while (true) {
            try {
                if ((i = fileReader.read()) == -1) break;
            } catch (IOException e) {
                throw new HttpConfigurationException(e);
            }
            sb.append((char) i);
        }

        JsonNode config = null;
        try {
            config = Json.parse(sb.toString());
        } catch (IOException e) {
            throw new HttpConfigurationException("Error parsing the configuration file.", e);
        }
        try {
            myConfiguration = Json.fromJsonToObject(config, Configuration.class);
        } catch (JsonProcessingException e) {
            throw new HttpConfigurationException("Error parsing configuration file to object", e);
        }
    }

    /**
     * Method to get the current configurations
     *
     * @return
     */
    public Configuration getCurrentConfiguration(){
        if (myConfiguration == null){
            throw new HttpConfigurationException("No Configuration Set.");
        }

        return myConfiguration;
    }

}
