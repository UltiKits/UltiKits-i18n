package com.ultikits.lib;

import org.bukkit.entity.Player;

public interface I18n {

    /**
     * Retrieves the I18nManager instance.
     *
     * @return The I18nManager instance.
     */
    default I18nManager getI18nManager() {
        return JsonI18nManager.getInstance("en_US", "/lang");
    }

    /**
     * Translates the given message to the default language.
     *
     * @param message the message to be translated
     * @return the translated message in the default language
     */
    default String i18n(String message) {
        return i18n(getI18nManager().getDefaultLangCode(), message);
    };

    /**
     * Translates the given message to the specified locale using the default
     * I18nManager.
     * 
     * @param locale  The locale to translate the message to.
     * @param message The message to be translated.
     * @return The translated message.
     */
    default String i18n(String locale, String message) {
        return i18n(getI18nManager(), locale, message);
    };

    /**
     * Translates the given message to the specified locale using the provided
     * I18nManager.
     * 
     * @param i18nManager The I18nManager instance used for translation.
     * @param locale      The locale to translate the message to.
     * @param message     The message to be translated.
     * @return The translated message.
     */
    default String i18n(I18nManager i18nManager, String locale, String message) {
        return i18nManager.getTranslation(locale, message);
    };

    /**
     * Translates and sends an internationalized message to the specified player.
     * If the player's locale is available, the message will be translated using
     * that locale.
     * If the player's locale is not available, the message will be translated
     * using the default locale.
     *
     * @param player  the player to send the message to
     * @param message the message to be translated and sent
     */
    default void i18n(Player player, String message) {
        try {
            String locale = player.getLocale();
            player.sendMessage(i18n(locale, message));
        } catch (NoSuchMethodError e) {
            player.sendMessage(i18n(message));
        }
    };
}
