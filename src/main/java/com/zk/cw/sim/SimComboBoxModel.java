package com.zk.cw.sim;

import javax.swing.DefaultComboBoxModel;

public class SimComboBoxModel extends DefaultComboBoxModel<Sim> {
		private static final long serialVersionUID = 1L;

		public SimComboBoxModel() {
	        super();
	    }
	 
	    @Override
	    public Sim getSelectedItem() {
	    	Sim selectedSim = (Sim) super.getSelectedItem();
	        return selectedSim;
	    }
}
