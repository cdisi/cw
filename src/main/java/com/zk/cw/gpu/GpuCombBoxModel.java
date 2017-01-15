package com.zk.cw.gpu;

import javax.swing.DefaultComboBoxModel;

public class GpuCombBoxModel extends DefaultComboBoxModel<Gpu> {
	private static final long serialVersionUID = 1L;

	public GpuCombBoxModel() {
        super();
    }
 
    @Override
    public Gpu getSelectedItem() {
    	Gpu selectedGpu = (Gpu) super.getSelectedItem();
        return selectedGpu;
    }
}