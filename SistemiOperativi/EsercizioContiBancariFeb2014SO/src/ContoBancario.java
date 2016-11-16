import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



public class ContoBancario extends Thread
{
	
	private double saldo;
	private List<Transazione> storico;
	public int ID;
	public static int creati=0;
	
	Lock lock = new ReentrantLock();
	
	ContoBancario()
	{
		creati++;
		ID=creati;
		this.saldo=1000;
		storico=new ArrayList<Transazione>();
	}
	public double getSaldo() 
	{
		return saldo;
	}


	public void setSaldo(double saldo) 
	{
		this.saldo = saldo;
	}


	public List<Transazione> getStorico()
	{
		return storico;
	}


	public void setStorico(List<Transazione> storico) 
	{
		this.storico = storico;
	}

	public void sblocca()
	{
		lock.unlock();
	}
	
	public void blocca()
	{
		lock.lock();
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}

		

}
