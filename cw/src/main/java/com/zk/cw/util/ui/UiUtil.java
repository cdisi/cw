package com.zk.cw.util.ui;

import hirondelle.movies.LaunchApplication;
import hirondelle.movies.util.Args;
import java.util.*;
import java.net.URL;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/** 
 Static convenience methods for GUIs which eliminate code duplication.
 
 <P>Your application will likely need to add to such a class. For example, 
 using <tt>GrdiBagLayout</tt> usually benefits from utility methods to 
 reduce code repetition.
*/
public final class UiUtil {

  /**
   <tt>pack</tt>, center, and <tt>show</tt> a window on the screen.
  
   <P>If the size of <tt>aWindow</tt> exceeds that of the screen, 
   then the size of <tt>aWindow</tt> is reset to the size of the screen.
  */
  public static void centerAndShow(Window aWindow){
    //note that the order here is important
    
    aWindow.pack();
    /*
     * If called from outside the event dispatch thread (as is 
     * the case upon startup, in the launch thread), then 
     * in principle this code is not thread-safe: once pack has 
     * been called, the component is realized, and (most) further
     * work on the component should take place in the event-dispatch 
     * thread. 
     *
     * In practice, it is exceedingly unlikely that this will lead 
     * to an error, since invisible components cannot receive events.
     */
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension window = aWindow.getSize();
    //ensure that no parts of aWindow will be off-screen
    if (window.height > screen.height) {
      window.height = screen.height;
    }
    if (window.width > screen.width) {
      window.width = screen.width;
    }
    int xCoord = (screen.width/2 - window.width/2);
    int yCoord = (screen.height/2 - window.height/2);
    aWindow.setLocation( xCoord, yCoord );
   
    aWindow.setVisible(true);
  }
  
  /**
   A window is packed, centered with respect to a parent, and then shown.
  
   <P>This method is intended for dialogs only.
  
   <P>If centering with respect to a parent causes any part of the dialog 
   to be off screen, then the centering is overidden, such that all of the 
   dialog will always appear fully on screen, but it will still appear 
   near the parent.
  
   @param aWindow must have non-null result for <tt>aWindow.getParent</tt>.
  */
  public static void centerOnParentAndShow(Window aWindow){
    aWindow.pack();
    
    Dimension parent = aWindow.getParent().getSize();
    Dimension window = aWindow.getSize();
    int xCoord = 
      aWindow.getParent().getLocationOnScreen().x + 
     (parent.width/2 - window.width/2)
    ;
    int yCoord = 
      aWindow.getParent().getLocationOnScreen().y + 
      (parent.height/2 - window.height/2)
    ;
    
    //Ensure that no part of aWindow will be off-screen
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    int xOffScreenExcess = xCoord + window.width - screen.width;
    if ( xOffScreenExcess > 0 ) {
      xCoord = xCoord - xOffScreenExcess;
    }
    if (xCoord < 0 ) {
      xCoord = 0;
    }
    int yOffScreenExcess = yCoord + window.height - screen.height;
    if ( yOffScreenExcess > 0 ) {
      yCoord = yCoord - yOffScreenExcess;
    }
    if (yCoord < 0) {
      yCoord = 0;
    }
    
    aWindow.setLocation( xCoord, yCoord );
    aWindow.setVisible(true);
  }

  /**
   Return a border of dimensions recommended by the Java Look and Feel 
   Design Guidelines, suitable for many common cases.
  
  <P>Each side of the border has size {@link UiConsts#STANDARD_BORDER}.
  */
  public static Border getStandardBorder(){
    return BorderFactory.createEmptyBorder(
      UiConsts.STANDARD_BORDER, 
      UiConsts.STANDARD_BORDER, 
      UiConsts.STANDARD_BORDER, 
      UiConsts.STANDARD_BORDER
    );
  }

  /**
   Return text which conforms to the Look and Feel Design Guidelines 
   for the title of a dialog : the application name, a colon, then 
   the name of the specific dialog.
  
  <P>Example return value: <tt>My Movies: Login</tt>
  
   @param aSpecificDialogName must have visible content
  */
  public static String getDialogTitle(String aSpecificDialogName){
    Args.checkForContent(aSpecificDialogName);
    StringBuilder result = new StringBuilder(LaunchApplication.APP_NAME);
    result.append(": ");
    result.append(aSpecificDialogName);
    return result.toString(); 
  }
  
  /**
   Make a horizontal row of buttons of equal size, whch are equally spaced, 
   and aligned on the right.
  
   <P>The returned component has border spacing only on the top (of the size 
   recommended by the Look and Feel Design Guidelines).
   All other spacing must be applied elsewhere ; usually, this will only mean 
   that the dialog's top-level panel should use {@link #getStandardBorder}.
   
   @param aButtons contains the buttons to be placed in a row.
  */
  public static JComponent getCommandRow(java.util.List<JComponent> aButtons){
    equalizeSizes( aButtons );
    JPanel panel = new JPanel();
    LayoutManager layout = new BoxLayout(panel, BoxLayout.X_AXIS);
    panel.setLayout(layout);
    panel.setBorder(BorderFactory.createEmptyBorder(UiConsts.THREE_SPACES, 0, 0, 0));
    panel.add(Box.createHorizontalGlue());
    Iterator<JComponent> buttonsIter = aButtons.iterator();
    while (buttonsIter.hasNext()) {
      panel.add( buttonsIter.next() );
      if (buttonsIter.hasNext()) {
        panel.add(Box.createHorizontalStrut(UiConsts.ONE_SPACE));
      }
    }
    return panel;
  }
  
  /**
   Make a vertical row of buttons of equal size, whch are equally spaced, 
   and aligned on the right.
  
   <P>The returned component has border spacing only on the left (of the size 
   recommended by the Look and Feel Design Guidelines).
   All other spacing must be applied elsewhere ; usually, this will only mean 
   that the dialog's top-level panel should use {@link #getStandardBorder}.
   
   @param aButtons contains the buttons to be placed in a column
  */
  public static JComponent getCommandColumn( java.util.List<JComponent> aButtons ){
    equalizeSizes( aButtons );
    JPanel panel = new JPanel();
    LayoutManager layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
    panel.setLayout( layout );
    panel.setBorder(
      BorderFactory.createEmptyBorder(0,UiConsts.THREE_SPACES, 0,0)
    );
    //(no for-each is used here, because of the 'not-yet-last' check)
    Iterator<JComponent> buttonsIter = aButtons.iterator();
    while ( buttonsIter.hasNext() ) {
      panel.add(buttonsIter.next());
      if ( buttonsIter.hasNext() ) {
        panel.add( Box.createVerticalStrut(UiConsts.ONE_SPACE) );
      }
    }
    panel.add( Box.createVerticalGlue() );
    return panel;
  }

  /** Return the currently active frame. */
  public static Frame getActiveFrame() {
    Frame result = null;
    Frame[] frames = Frame.getFrames();
    for (int i = 0; i < frames.length; i++) {
      Frame frame = frames[i];
      if (frame.isVisible()) { //Component method
        result = frame;
        break;
      }
    }
    return result;
  }
  
  /**
   Return a <tt>Dimension</tt> whose size is defined not in terms of pixels, 
   but in terms of a given percent of the screen's width and height. 
  
  <P> Use to set the preferred size of a component to a certain 
   percentage of the screen.  
  
   @param aPercentWidth percentage width of the screen, in range <tt>1..100</tt>.
   @param aPercentHeight percentage height of the screen, in range <tt>1..100</tt>.
  */
  public static final Dimension getDimensionFromPercent(int aPercentWidth, int aPercentHeight){
    int low = 1;
    int high = 100;
    Args.checkForRange(aPercentWidth, low, high);
    Args.checkForRange(aPercentHeight, low, high);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    return calcDimensionFromPercent(screenSize, aPercentWidth, aPercentHeight);
  }

  /**
    Sets the items in <tt>aComponents</tt> to the same size.
   
    <P>Sets each component's preferred and maximum sizes. 
    The actual size is determined by the layout manager, whcih adjusts 
    for locale-specific strings and customized fonts. (See this 
    <a href="http://java.sun.com/products/jlf/ed2/samcode/prefere.html">Sun doc</a> 
    for more information.)
   
    @param aComponents items whose sizes are to be equalized
   */
  public static void equalizeSizes(java.util.List<JComponent> aComponents) {
    Dimension targetSize = new Dimension(0,0);
    for(JComponent comp: aComponents ) {
      Dimension compSize = comp.getPreferredSize();
      double width = Math.max(targetSize.getWidth(), compSize.getWidth());
      double height = Math.max(targetSize.getHeight(), compSize.getHeight());
      targetSize.setSize(width, height);
    }
    setSizes(aComponents, targetSize);
  }

  /**
   Make the system emit a beep.
  
   <P>May not beep unless the speakers are turned on, so this cannot 
   be guaranteed to work.
  */
  public static void beep(){
    Toolkit.getDefaultToolkit().beep();
  }
  
  /**
   Imposes a uniform horizontal alignment on all items in a container.
  
  <P> Intended especially for <tt>BoxLayout</tt>, where all components need 
   to share the same alignment in order for display to be reasonable. 
   (Indeed, this method may only work for <tt>BoxLayout</tt>, since apparently 
   it is the only layout to use <tt>setAlignmentX, setAlignmentY</tt>.)
  
   @param aContainer contains only <tt>JComponent</tt> objects.
  */
  public static void alignAllX(Container aContainer, UiUtil.AlignX aAlignment){
    java.util.List<Component> components = Arrays.asList( aContainer.getComponents() );
    for(Component comp: components){
      JComponent jcomp = (JComponent)comp;
      jcomp.setAlignmentX( aAlignment.getValue() );
    }
  }
  
  /** Enumeration for horizontal alignment. */
  public enum AlignX {
    LEFT(Component.LEFT_ALIGNMENT),
    CENTER(Component.CENTER_ALIGNMENT),
    RIGHT(Component.RIGHT_ALIGNMENT);
    public float getValue(){
      return fValue;
    }
    private final float fValue;
    private AlignX(float aValue){
      fValue = aValue;
    }
  }
  
  /**
   Imposes a uniform vertical alignment on all items in a container.
  
  <P> Intended especially for <tt>BoxLayout</tt>, where all components need 
   to share the same alignment in order for display to be reasonable.
   (Indeed, this method may only work for <tt>BoxLayout</tt>, since apparently 
   it is the only layout to use <tt>setAlignmentX, setAlignmentY</tt>.)
  
   @param aContainer contains only <tt>JComponent</tt> objects.
  */
  public static void alignAllY(Container aContainer, UiUtil.AlignY aAlignment){
    java.util.List components = Arrays.asList( aContainer.getComponents() );
    Iterator compsIter = components.iterator();
    while ( compsIter.hasNext() ) {
      JComponent comp = (JComponent)compsIter.next();
      comp.setAlignmentY( aAlignment.getValue() );
    }
  }

  /** Type-safe enumeration vertical alignment. */
  public enum AlignY {
    TOP(Component.TOP_ALIGNMENT),
    CENTER(Component.CENTER_ALIGNMENT),
    BOTTOM(Component.BOTTOM_ALIGNMENT);
    float getValue(){
      return fValue;
    }
    private final float fValue;
    private AlignY( float aValue){
      fValue = aValue;
    }
  }
  
  /**
   Ensure that <tt>aRootPane</tt> has no default button associated with it.
  
   <P>Intended mainly for dialogs where the user is confirming a delete action.
   In this case, an explicit Yes or No is preferred, with no default action being 
   taken when the user hits the Enter key. 
  */
  public static void noDefaultButton(JRootPane aRootPane){
    aRootPane.setDefaultButton(null);
  }
  
  /**
  Create an icon for use by a given class.
  
  Returns <tt>null</tt> if the icon cannot be found.
  
  @param aPath path to the file, relative to the calling class, as in '../images/blah.png'
  @param aDescription description of the image
  @param aClass class that needs to use the image
  */
  public static ImageIcon createImageIcon(String aPath, String aDescription, Class aClass) {
    ImageIcon result = null;
    URL imageURL = aClass.getResource(aPath); //resolves to an absolute path
    if (imageURL != null) {
      result = new ImageIcon(imageURL, aDescription);
    } 
    return result;
  }

  // PRIVATE
  
  private static void setSizes(java.util.List aComponents, Dimension aDimension){
    Iterator compsIter = aComponents.iterator();      
    while ( compsIter.hasNext() ) {
      JComponent comp = (JComponent) compsIter.next();
      comp.setPreferredSize( (Dimension)aDimension.clone() );
      comp.setMaximumSize( (Dimension)aDimension.clone() );
    }
  }

  private static Dimension calcDimensionFromPercent(Dimension aSourceDimension, int aPercentWidth, int aPercentHeight){
    int width = aSourceDimension.width * aPercentWidth/100;
    int height = aSourceDimension.height * aPercentHeight/100;
    return new Dimension(width, height);
  }
}