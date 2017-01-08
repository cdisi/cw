package cpu;

public class CekirdekSayiAta {
	private Integer id;
    private Integer cihazId;
    private Integer cpuOzellikId;
    
    public CekirdekSayiAta() {
    }
    public CekirdekSayiAta(Integer id, Integer cihazId, Integer cpuOzellikId) {
        this.id = id;
        this.cihazId = cihazId;
        this.cpuOzellikId = cpuOzellikId;
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
	public Integer getCpuOzellikId() {
        return this.cpuOzellikId;
    }
	public void setCpuOzellikId(Integer cpuOzellikId) {
        this.cpuOzellikId=cpuOzellikId;
    }		
}
