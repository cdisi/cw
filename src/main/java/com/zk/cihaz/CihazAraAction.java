package com.zk.cihaz;

import com.zk.cw.util.Util;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.JFrame;

/** Show dialog for adding a new {@link Movie}. */
public final class CihazAraAction extends AbstractAction  {
  
  /** Constructor. */
  public CihazAraAction(JFrame aFrame){
    super("Cihaz ara...", null );
    putValue(SHORT_DESCRIPTION, "Cihaz ara"); 
    putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_A) );
    fFrame = aFrame;
  }
  
  @Override public void actionPerformed(ActionEvent aActionEvent) {
    fLogger.config("Adding a new movie.");
    //UreticiView view = new UreticiView(fFrame);
  }
  
  // PRIVATE
  private JFrame fFrame;
  private static final Logger fLogger = Util.getLogger(CihazAraAction.class);
  
}
