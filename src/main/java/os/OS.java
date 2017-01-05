package os;

public class OS {
    
	private Integer id;
    private String ad;
    
    public OS() {
    }
    public OS(Integer id, String ad) {
        this.id = id;
        this.ad = ad;
    }
	public void setId(Integer id) {
        this.id=id;
    }    
	public Integer getId() {
        return this.id;
    }
	public void setAd(String ad) {
        this.ad=ad;
    }	
	public String getAd() {
        return this.ad;
    }	
    
    @Override
    public String toString()
    {
        return ad;
    }
}
