# UltiTools-i18n

## Introduction

UltiTools-i18n allows developers to easily manage and use multilingual translations. By defining translation files and using annotations, developers can quickly implement internationalization features.

## Installation

### Maven

Add the following dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>com.ultikits.lib</groupId>
    <artifactId>ultikits-i18n</artifactId>
    <version>0.0.3</version>
</dependency>
```

### Gradle

Add the following dependency to your `build.gradle`:

``` gradle
implementation 'com.ultikits.lib:ultikits-i18n:0.0.3'
```

## Quick Start

### Create I18nManager Implementation Class (Optional)

You can create a class that extends `I18nManager` and implements its abstract methods.

`JsonI18nManager` is included and will be used by default and as an example.

``` java
package com.ultikits.lib;

public class MyI18nManger extends I18nManager {

    @Override
    public String getTranslation(String langCode, String key) {
        ...
    }
}
```

### Define Translation Files

To use `JsonI18nManager`, you must create your translation files in the `resources/lang` folder. For example, `en_US.json`:

``` json
{
    "hello": "Hello",
    "goodbye": "Goodbye"
}
```

### Retrieve Translations

Use the `i18n` method to get translations:

``` java
public class MyCommandExecuter implements CommandExecutor, I18n {
    
     @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            // Send the player who executes this command an internationalized message based on their locale
            i18n(player, "hello");
        } else {
            // Send the sender who executes this command an internationalized message based on the default locale
            sender.sendMessage(i18n("hello"))
        }
        return false;
    }
}
```

## More Details

### Custom Language Folder

You can specify a custom default locale or language folder for `JsonI18nManager`. Keep in mind that every time you get an instance with a different language folder from the last time, a new instance will be created. It is not recommended to use a different language folder for each class.

``` java
public class myClass implements I18n {
    // other code in this class...

    @Override
    public I18nManager getI18nManager() {
        // return JsonI18nManager.getInstance("zh_CN");
        return JsonI18nManager.getInstance("zh_CN", "/path/to/lang");
    }
}
```

### Retrieve Translations for Specific Language

``` java
String translatedMessage = myI18nClass.i18n("en_US", "hello");
```

## License

This project is licensed under the MIT License. See the LICENSE file for details.
