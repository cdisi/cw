package com.zk.cw.uretici;

import com.zk.cw.util.Util;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.JFrame;

/** Show dialog for adding a new {@link Movie}. */
public final class UreticiActionAra extends AbstractAction  {
  
  /** Constructor. */
  public UreticiActionAra(JFrame aFrame){
    super("Ara...", null );
    putValue(SHORT_DESCRIPTION, "Üretici ara"); 
    putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_A) );
    fFrame = aFrame;
  }
  
  @Override public void actionPerformed(ActionEvent aActionEvent) {
    fLogger.config("Adding a new movie.");
    UreticiView view = new UreticiView(fFrame);
  }
  
  // PRIVATE
  private JFrame fFrame;
  private static final Logger fLogger = Util.getLogger(UreticiActionAra.class);
}
