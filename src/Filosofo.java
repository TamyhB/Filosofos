import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

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
		/*if(this.getMaoEsq() != 0 && this.getMaoDir() == 0){
			System.out.println("Preciso do garfo da mão direita para começar a comer :(");
			this.setEstado("Estou esperando o garfo");
		}
		if(this.getMaoDir() != 0 && this.getMaoEsq() == 0){
			System.out.println("Preciso do garfo da mão esquerda para começar a comer :(");
			this.setEstado("Estou esperando o garfo");
		}
        if(this.getMaoEsq() != 0 && this.getMaoDir() != 0){
        	setEstado("Estou comendo...");
        }*/
		System.out.println("Estou comendo");
    }
	
    public void pensar() throws InterruptedException{
        	this.setEstado("Estou pensando...");
        	System.out.println(this.getEstado());
        	Random rand = new Random();
        	System.out.println(rand.nextInt(9) * 1000);
        	Thread.sleep(rand.nextInt(9) * 1000);
    }
    public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
    	Thread t1 = new Thread(new Filosofo(new String[]{"acquire 1 Platão", "acquire 2 Platão", "release 1 Platão", "release 2 Platão", "finish 0 Platão"}));
		t1.start();
		Thread t2 = new Thread(new Filosofo(new String[]{"acquire 2 Socrates", "acquire 3 Socrates", "release 2 Socrates", "release 3 Socrates", "finish 0 Socrates"}));
		t2.start();
		Thread t3 = new Thread(new Filosofo(new String[]{"acquire 3 Descartes", "acquire 4 Descartes", "release 3 Descartes", "release 4 Descartes", "finish 0 Descartes"}));
		t3.start();
		Thread t4 = new Thread(new Filosofo(new String[]{"acquire 4 Aristoteles", "acquire 5 Aristoteles", "release 4 Aristoteles", "release 5 Aristoteles", "finish 0 Aristoteles"}));
		t4.start();
		Thread t5 = new Thread(new Filosofo(new String[]{"acquire 5 Tales", "acquire 1 Tales", "release 5 Tales", "release 1 Tales", "finish 0 Tales"}));
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
		gotGarfo3 = false;
		gotGarfo4 = false;
		gotGarfo5 = false;
	}
	
    public void sendReleaseMsg(String msg, PrintStream pstream, BufferedReader in) throws IOException{
		if(msg.split(" ")[1] == "1" && gotGarfo1 || msg.split(" ")[1] == "2" && gotGarfo2){
			pstream.println(msg);
			String response = in.readLine();
			System.out.println("echo " + this.toString() + ": " + response);
			if(msg.split(" ")[1] == "1"){
				gotGarfo1 = false;
			}
			if(msg.split(" ")[1] == "2"){
				gotGarfo2 = false;
			}
		} else if(msg.split(" ")[1] == "2" && gotGarfo2 || msg.split(" ")[1] == "3" && gotGarfo3){
			pstream.println(msg);
			String response = in.readLine();
			System.out.println("echo " + this.toString() + ": " + response);
			if(msg.split(" ")[1] == "2"){
				gotGarfo2 = false;
			}
			if(msg.split(" ")[1] == "3"){
				gotGarfo3 = false;
			}
		} else if(msg.split(" ")[1] == "3" && gotGarfo3 || msg.split(" ")[1] == "4" && gotGarfo4){
			pstream.println(msg);
			String response = in.readLine();
			System.out.println("echo " + this.toString() + ": " + response);
			if(msg.split(" ")[1] == "3"){
				gotGarfo3 = false;
			}
			if(msg.split(" ")[1] == "4"){
				gotGarfo4 = false;
			}
		} else if(msg.split(" ")[1] == "4" && gotGarfo4 || msg.split(" ")[1] == "5" && gotGarfo5){
			pstream.println(msg);
			String response = in.readLine();
			System.out.println("echo " + this.toString() + ": " + response);
			if(msg.split(" ")[1] == "4"){
				gotGarfo4 = false;
			}
			if(msg.split(" ")[1] == "5"){
				gotGarfo5 = false;
			}
		} else if(msg.split(" ")[1] == "5" && gotGarfo5 || msg.split(" ")[1] == "1" && gotGarfo1){
			pstream.println(msg);
			String response = in.readLine();
			System.out.println("echo " + this.toString() + ": " + response);
			if(msg.split(" ")[1] == "5"){
				gotGarfo5 = false;
			}
			if(msg.split(" ")[1] == "1"){
				gotGarfo1 = false;
			}
		}
	}
	
	public void sendAcquireMsg(String msg, PrintStream pstream, BufferedReader in) throws IOException{		
		pstream.println(msg);
		String response = in.readLine();
		System.out.println("Acquire " + response);
		if(response == "true"){
			if(msg.split(" ")[1] == "1"){
				gotGarfo1 = true;
			}
			if(msg.split(" ")[1] == "2"){
				gotGarfo2 = true;
			}
			if(msg.split(" ")[1] == "3"){
				gotGarfo3 = true;
			}
			if(msg.split(" ")[1] == "4"){
				gotGarfo4 = true;
			}
			if(msg.split(" ")[1] == "5"){
				gotGarfo5 = true;
			}
		}
	}
    
	public void run() {
		String hostName = "localhost";
		int portNumber = 15695;
		String response;
		try {
			this.pensar();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try (   Socket echoSocket = new Socket(hostName, portNumber);
				PrintStream pstream = new PrintStream(echoSocket.getOutputStream());
				BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));	
				) {
			
			for(int m = 0; m < msgs.length; m++){
				
				if(msgs[m].split(" ")[0].equals("release")){
					System.out.println("send liberar");
					sendReleaseMsg(msgs[m], pstream, in);
				}
				if(msgs[m].split(" ")[0].equals("acquire")){
					System.out.println("send adquirir");
					sendAcquireMsg(msgs[m], pstream, in);
				}
				
				//pstream.println(msgs[m]);
				response = in.readLine();
				System.out.println("echo " + this.toString() + ": " + response);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
