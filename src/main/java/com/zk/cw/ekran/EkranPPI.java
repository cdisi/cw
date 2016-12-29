package com.zk.cw.ekran;

public class EkranPPI {
    private Integer id;
    private String ppi;
    
    public EkranPPI() {
    }
    public EkranPPI(Integer id, String ppi) {
        this.id = id;
        this.ppi = ppi;
    }
	public void setId(Integer id) {
        this.id=id;
    }    
	public Integer getId() {
        return this.id;
    }
	public void setPpi(String ppi) {
        this.ppi=ppi;
    }	
	public String getPpi() {
        return this.ppi;
    }	
    
    @Override
    public String toString()
    {
        return ppi;
    }
}
