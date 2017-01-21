package batarya;

public class Kapasite {
	private Integer id;
    private String kapasite;
    
    public Kapasite() {
    }
    public Kapasite(Integer id, String kapasite) {
        this.id = id;
        this.kapasite = kapasite;
    }
	public void setId(Integer id) {
        this.id=id;
    }    
	public Integer getId() {
        return this.id;
    }
	public void setKapasite(String kapasite) {
        this.kapasite=kapasite;
    }	
	public String getKapasite() {
        return this.kapasite;
    }	
    
    @Override
    public String toString()
    {
        return kapasite;
    }
}
