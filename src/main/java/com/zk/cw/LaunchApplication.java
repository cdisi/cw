package com.zk.cw;

import com.zk.cw.exception.ExceptionHandler;
import com.zk.cw.login.LoginController;
import com.zk.cw.util.Util;

import java.awt.Font;
import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public final class LaunchApplication {

  public static void main(String... aArgs){  
    configureJDKLogging();
    
    fLogger.config("Launching application...");
    fLogger.config("Operating System : " + System.getProperty("os.name")  + " " +  System.getProperty("os.version"));
    fLogger.config("Java Version: " + System.getProperty("java.version"));
    fLogger.config("Java Home: " + System.getProperty("java.home"));

    useCustomExceptionHandler();
    useNativeLookAndFeel();
    setApplicationFont();
    
    fLogger.config("Showing user login screen.");
    userLogin();
    
    fLogger.config("Launch thread now ending.");
  }
  
  public static final String APP_NAME = "CW";
  
  public static final String APP_VERSION = "1.0.0";
  
  private LaunchApplication(){ }
  
  private static final Logger fLogger = Util.getLogger(LaunchApplication.class);

  private static void configureJDKLogging() {
    fLogger.setLevel(Level.FINE);
    boolean OVERWRITE = false;
    int SINGLE_FILE = 1;
    int UNLIMITED_SIZE = 0;
    try {
      FileHandler fileHandler = new FileHandler("log.txt",  UNLIMITED_SIZE, SINGLE_FILE, OVERWRITE);
      fileHandler.setLevel(Level.FINEST);
      fileHandler.setFormatter(new SimpleFormatter());
      fLogger.addHandler(fileHandler);
    }
    catch (IOException ex){
      fLogger.severe("Cannot set up log file.");
    }
  }
  
  /**   See {@link ExceptionHandler}.    */
  private static void useCustomExceptionHandler(){
    fLogger.config("Setting up custom exception handler.");
    Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
  }
  
  private static void useNativeLookAndFeel() {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch(Throwable ex){
      fLogger.severe("Cannot set the look and feel.");
    }
  }

  private static void setApplicationFont() {
    FontUIResource fontResource = new FontUIResource("Verdana",Font.PLAIN,12);
    Enumeration keys = UIManager.getDefaults().keys();
    while (keys.hasMoreElements()) {
      Object key = keys.nextElement();
      Object value = UIManager.get (key);
      if (value instanceof FontUIResource) {
        UIManager.put (key, fontResource);
      }
    }
  }
  
   private static void userLogin(){
     fLogger.config("Showing the login screen.");
     LoginController login = new LoginController();
     login.askUserForCredentials();
   }
}
