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
  
  /** 
  Defines the name of the app.
  Could be used in an About box, trouble ticket emails, and so on.
  */
  public static final String APP_NAME = "My Movies";
  
  /** 
   The version of this application (an arbitrary string). 
   Here, the version string simply matches the version of the JDK. 
  */
  public static final String APP_VERSION = "1.7.0";
  
  // PRIVATE 
 
  /** It makes no sense to call this class's constructor, so it's made private. */
  private LaunchApplication(){ }
  
  private static final Logger fLogger = Util.getLogger(LaunchApplication.class);

  /**   Set up JDK logging to emit logging entries to a file in the application's
   installation directory. The file name is log.txt.  */
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
  
   /**    Use the look which is usually used on the current operating system.
    
    <P>For example, on a Windows machine, use a Windows look and feel.    If you are using a non-default look and feel, then you should set it first thing.
    For more info, see the 
    <a href='http://java.sun.com/docs/books/tutorial/uiswing/lookandfeel/plaf.html#available'>Swing Tutorial</a>
    
    <P>Warning: on Windows, the native look & feel displays accelerator keys only when ALT key is held down. 
    Otherwise, they are not displayed!
  */
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
  
  /**
    Show the login screen. 
    
    <P>This is the first GUI element displayed to the end user.
    When the GUI is realized, then all interaction with the GUI must be through the 
    Event Dispatch thread.
    
    <P>For more info, see
    <a href='http://www.javapractices.com/topic/TopicAction.do?Id=153'>this article</a>.
   */
   private static void userLogin(){
     fLogger.config("Showing the login screen.");
     LoginController login = new LoginController();
     login.askUserForCredentials();
   }
}
