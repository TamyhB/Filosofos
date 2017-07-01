
public class Filosofo {

	private int maoEsq = 0;
	private int maoDir = 0;
    private String estado = "Estou pensando..."; 
    private int prefMaoEsq = 999;
    private int prefMaoDir = 999;
    
	public int getMaoEsq() {
		return maoEsq;
	}
	public void setMaoEsq(int maoEsq) {
		this.maoEsq = maoEsq;
	}
	public int getMaoDir() {
		return maoDir;
	}
	public void setMaoDir(int maoDir) {
		this.maoDir = maoDir;
	}
    
    public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getPrefMaoEsq() {
		return prefMaoEsq;
	}
	public void setPrefMaoEsq(int prefMaoEsq) {
		this.prefMaoEsq = prefMaoEsq;
	}
	public int getPrefMaoDir() {
		return prefMaoDir;
	}
	public void setPrefMaoDir(int prefMaoDir) {
		this.prefMaoDir = prefMaoDir;
	}
	public void comer(){
		if(this.getMaoEsq() == this.getPrefMaoEsq() && this.getMaoDir() == 0){
			System.out.println("Preciso do garfo "+ this.getPrefMaoDir() + " para começar a comer :(");
			this.setEstado("Estou esperando o garfo");
		}
		if(this.getMaoDir() == this.getPrefMaoDir() && this.getMaoEsq() == 0){
			System.out.println("Preciso do garfo "+ this.getPrefMaoEsq() + " para começar a comer :(");
			this.setEstado("Estou esperando o garfo");
		}
        if(this.getMaoEsq() == this.getPrefMaoEsq() && this.getMaoDir() == this.getPrefMaoDir()){
        	setEstado("Estou comendo...");
        }
    }
	
    public void pensar(){
    		this.setMaoDir(0);
    		this.setMaoEsq(0);
        	this.setEstado("Estou pensando...");
    }
}
