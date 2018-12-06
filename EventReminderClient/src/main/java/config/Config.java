package config;

import java.util.prefs.Preferences;

public class Config{

    public final static int PORT;
    public final static String HOST;
    public final static int WINDOW_HEIGHT;
    public final static int WINDOW_WIDTH;
    public final static String APP_TITLE;
    public final static String GREETING_MESSAGE;
    public final static String LOGIN;

    static private Preferences userPrefs;

    static {
        userPrefs = Preferences.userNodeForPackage(Config.class);
        PORT = userPrefs.getInt("port", 1234);
        HOST = userPrefs.get("host", "localhost");
        WINDOW_HEIGHT = userPrefs.getInt("window_height",200);
        WINDOW_WIDTH = userPrefs.getInt("window_width",350);
        APP_TITLE = userPrefs.get("app_title", "Event Reminder");
        GREETING_MESSAGE = userPrefs.get("greeting_message", "###greeting@user@message###");
        LOGIN = userPrefs.get("login", "id1");
    }

    public static void putPort(int port){
        userPrefs.putInt("port", port);
    }

    public static void putHost(String host){
        userPrefs.put("host", host);
    }

    public static void putWindowHeight(int height){
        userPrefs.putInt("window_height", height);
    }

    public static void putWindowWidth(int width){
        userPrefs.putInt("window_width", width);
    }

    public static void putAppTitle(String title){
        userPrefs.put("app_title", title);
    }

    public static void putGreetingMessage(String message){
        userPrefs.put("greeting_message", message);
    }

    public static void putLogin(String login){
        userPrefs.put("login", login);
    }



}
