package com.ultikits.lib;

/**
 * The <code>I18nManager</code> class is an abstract class that provides
 * localization functionality for managing translations in different languages.
 * 
 * This class contains methods for retrieving translations based on language
 * codes and keys, as well as loading translations from a specified language
 * folder.
 * 
 * To use the <code>I18nManager</code> class, you need to extend it and
 * implement the <code>getTranslation</code> and <code>loadTranslations</code>
 * methods.
 * 
 * <br>
 * <br>
 * Example usage:
 * 
 * <pre>
 * <code>
 * public class MyI18nManager extends I18nManager {
 *     // Implement the abstract methods
 *     // ...
 * }
 * 
 * // Create an instance of MyI18nManager
 * MyI18nManager i18nManager = new MyI18nManager();
 * 
 * // Retrieve a translation
 * String translation = i18nManager.getTranslation("en_US", "hello");
 * </code>
 * </pre>
 */
public abstract class I18nManager {
    /**
     * The language code used by the I18nManager.
     */
    private String langCode;

    /**
     * Constructs a new instance of the I18nManager class with the specified
     * language code.
     * 
     * @param langCode the language code to be used for localization
     */
    protected I18nManager(String langCode) {
        this.langCode = langCode;
    }

    /**
     * Returns the default language code.
     *
     * @return The default language code.
     */
    public String getDefaultLangCode() {
        return langCode;
    }

    /**
     * Sets the language code for the I18nManager.
     *
     * @param langCode the language code to set
     */
    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }

    /**
     * Retrieves the translation for the given key.
     *
     * @param key the key of the translation to retrieve
     * @return the translated string
     */
    public String getTranslation(String key) {
        return getTranslation(langCode, key);
    }

    /**
     * Retrieves the translation for the specified language code and key.
     *
     * @param langCode the language code for the translation
     * @param key      the key for the translation
     * @return the translation string
     */
    public abstract String getTranslation(String langCode, String key);
}
