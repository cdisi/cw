package com.zk.cw.exit;

import com.zk.cw.util.Util;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

/** 
  Save changes and close the application.
  
  <ul>   <li>persist the changes the user has made during this session   <li>close the main window
   <li>shut down the JVM   </ul>
*/
public final class ExitAction extends AbstractAction {
  
  /** Constructor. */
  public ExitAction(){
    super("Exit", null);
    putValue(SHORT_DESCRIPTION, "Exit the application"); 
    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
    putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_X) );    
  }
  
  @Override public void actionPerformed(ActionEvent aActionEvent) {
    System.exit(0);
  }
  
}
