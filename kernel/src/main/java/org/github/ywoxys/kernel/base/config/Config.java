package org.github.ywoxys.kernel.base.config;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Config {

    private static final String CONFIG_FILE_PATH = "C:/melo-network/config.yml";
    private static Config instance;
    private final Map<String, Object> configData;

    private Config() throws Exception {
        Yaml yaml = new Yaml();
        try (InputStream in = Files.newInputStream(Paths.get(CONFIG_FILE_PATH))) {
            Object data = yaml.load(new InputStreamReader(in));
            if (data instanceof Map) {
                this.configData = (Map<String, Object>) data;
            } else {
                this.configData = new LinkedHashMap<>();
            }
            replaceSpecialCharacters(configData);
        }
    }

    public static Config getInstance() throws Exception {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    private void replaceSpecialCharacters(Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() instanceof String) {
                String modifiedValue = ((String) entry.getValue()).replace("&", "§");
                entry.setValue(modifiedValue);
            } else if (entry.getValue() instanceof Map) {
                replaceSpecialCharacters((Map<String, Object>) entry.getValue());
            } else if (entry.getValue() instanceof List) {
                replaceSpecialCharactersInList((List<Object>) entry.getValue());
            }
        }
    }

    private void replaceSpecialCharactersInList(List<Object> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof String) {
                list.set(i, ((String) list.get(i)).replace("&", "§"));
            }
        }
    }

    public Object get(String key) {
        String[] keys = key.split("\\.");
        Map<String, Object> currentMap = configData;

        for (int i = 0; i < keys.length - 1; i++) {
            Object nextMap = currentMap.get(keys[i]);
            if (nextMap instanceof Map) {
                currentMap = (Map<String, Object>) nextMap;
            } else {
                return null; // Chave não encontrada
            }
        }

        return currentMap.get(keys[keys.length - 1]);
    }
}
