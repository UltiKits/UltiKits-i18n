package com.ultikits.lib;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JsonI18nManager is a concrete implementation of the I18nManager abstract
 * class.
 * It provides methods for retrieving translations and loading translations from
 * JSON files.
 */
public class JsonI18nManager extends I18nManager {
    /**
     * Represents the singleton instance of the JsonI18nManager class.
     */
    protected static JsonI18nManager instance;
    /**
     * Represents a manager for internationalization (i18n) translations.
     * 
     * The translations are stored in a map where the keys are strings representing
     * the translation keys,
     * and the values are objects representing the translated values.
     */
    private Map<String, Object> translations = new HashMap<>();
    /**
     * The path to the language folder.
     */
    private String langFolder;

    /**
     * Constructs a new instance of the JsonI18nManager class with the specified
     * locale and language folder.
     * 
     * @param locale     the locale to be used for language translation
     * @param langFolder the path to the folder containing the language files
     */
    private JsonI18nManager(String locale, String langFolder) {
        super(locale);
        this.langFolder = langFolder;
        loadTranslations(langFolder);
    }

    /**
     * Returns an instance of the JsonI18nManager class with the specified locale.
     * 
     * @param locale the locale to be used for language translation
     * @return an instance of the JsonI18nManager class
     */
    public static JsonI18nManager getInstance(String locale) {
        return getInstance(locale, "/lang");
    }

    /**
     * Retrieves the singleton instance of the JsonI18nManager class.
     * 
     * @param locale    The locale to use for translations.
     * @param langFolder The path to the folder containing the language files.
     * @return The singleton instance of the JsonI18nManager class.
     */
    public static JsonI18nManager getInstance(String locale, String langFolder) {
        if (instance == null || !langFolder.equals(instance.langFolder)) {
            instance = new JsonI18nManager(locale, langFolder);
        }
        return instance;
    }

    @Override
    public String getTranslation(String langCode, String key) {
        if (translations.containsKey(langCode)) {
            Map<String, String> langTranslations = (Map<String, String>) translations.get(langCode);
            if (langTranslations.containsKey(key)) {
                return langTranslations.get(key);
            }
        }
        return key;
    }

    /**
     * Loads translations from the specified language folder.
     *
     * @param langFolder the path to the language folder containing the translations
     */
    private void loadTranslations(String langFolder) {
        try {
            InputStream resourceStream = getClass().getResourceAsStream(langFolder);
            if (resourceStream == null) {
                throw new IOException("Resource folder not found");
            }
            File folder = new File(getClass().getResource(langFolder).getFile());
            File[] jsonFiles = folder.listFiles((dir, name) -> name.endsWith(".json"));

            if (jsonFiles != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                for (File file : jsonFiles) {
                    String name = file.getName().replace(".json", "");
                    Map<String, String> fileTranslations = objectMapper.readValue(file, Map.class);
                    translations.put(name, fileTranslations);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
