package com.zk.cw.cpu;

public class CekirdekSayiAta {
	private Integer id;
    private Integer cihazId;
    private Integer cpuCekirdekSayiId;
    
    public CekirdekSayiAta() {
    }
    public CekirdekSayiAta(Integer id, Integer cihazId, Integer cpuCekirdekSayiId) {
        this.id = id;
        this.cihazId = cihazId;
        this.cpuCekirdekSayiId = cpuCekirdekSayiId;
    }
	public void setId(Integer id) {
        this.id=id;
    }  
	public Integer getId() {
        return this.id;
    }		
	public Integer getCihazId() {
        return this.cihazId;
    }
	public void setCihazId(Integer cihazId) {
        this.cihazId=cihazId;
    }	
	public Integer getCpuCekirdekSayiId() {
        return this.cpuCekirdekSayiId;
    }
	public void setCpuCekirdekSayiId(Integer cpuCekirdekSayiId) {
        this.cpuCekirdekSayiId=cpuCekirdekSayiId;
    }		
}
