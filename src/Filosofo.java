import java.io.IOException;
import java.net.UnknownHostException;

public class Filosofo implements Runnable {
	private boolean gotGarfo1;
	private boolean gotGarfo2;
	private boolean gotGarfo3;
	private boolean gotGarfo4;
	private boolean gotGarfo5;
	private String msgs[];
	private int maoEsq = 0;
	private int maoDir = 0;
    private String estado = "Estou pensando..."; 
    
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
	
	public void comer(){
		if(this.getMaoEsq() != 0 && this.getMaoDir() == 0){
			System.out.println("Preciso do garfo da mão direita para começar a comer :(");
			this.setEstado("Estou esperando o garfo");
		}
		if(this.getMaoDir() != 0 && this.getMaoEsq() == 0){
			System.out.println("Preciso do garfo da mão esquerda para começar a comer :(");
			this.setEstado("Estou esperando o garfo");
		}
        if(this.getMaoEsq() != 0 && this.getMaoDir() != 0){
        	setEstado("Estou comendo...");
        }
    }
	
    public void pensar(){
    		this.setMaoDir(0);
    		this.setMaoEsq(0);
        	this.setEstado("Estou pensando...");
    }
    public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
    	Thread t1 = new Thread(new Filosofo(new String[]{"acquire 1", "acquire 2"}));
		t1.start();
		Thread t2 = new Thread(new Filosofo(new String[]{"acquire 2", "acquire 3"}));
		t2.start();
		Thread t3 = new Thread(new Filosofo(new String[]{"acquire 3", "acquire 4"}));
		t3.start();
		Thread t4 = new Thread(new Filosofo(new String[]{"acquire 4", "acquire 5"}));
		t4.start();
		Thread t5 = new Thread(new Filosofo(new String[]{"acquire 5", "acquire 1"}));
		t5.start();
		
		t1.join();
		t2.join();
		t3.join();
		t4.join();
		t5.join();
    }
    public Filosofo(String msgs[]) {
		this.msgs = msgs;
		gotGarfo1 = false;
		gotGarfo2 = false;
	}
	
	public void run() {
		
		
	}
}
