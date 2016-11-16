import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Banca 
{
	ArrayList<ContoBancario> conti;
	
	Banca()
	{
		this.conti=new ArrayList<ContoBancario>();
	}
	
	public double getSaldo(ContoBancario c)
	{
		if(conti.contains(c))
		{
			return c.getSaldo();
		}
		
		return -1;
	}
	
	public boolean trasferisci(ContoBancario sorgente, ContoBancario destinazione, double valore)
	{
		
		if(sorgente.getSaldo()>=valore&&conti.contains(sorgente)&&conti.contains(destinazione)&&sorgente!=destinazione)
		{
			if(sorgente.getID()>destinazione.getID())
			{
				sorgente.blocca();
				destinazione.blocca();
				
				sorgente.setSaldo(sorgente.getSaldo()-valore);
				destinazione.setSaldo(destinazione.getSaldo()+valore);
			
				sorgente.getStorico().add(new Transazione(sorgente,destinazione,valore));
				
				sorgente.sblocca();
				
				destinazione.getStorico().add(new Transazione(sorgente, destinazione, valore));
				
				destinazione.sblocca();
			}
			else 
			{
				destinazione.blocca();
				sorgente.blocca();
			
				sorgente.setSaldo(sorgente.getSaldo()-valore);
				destinazione.setSaldo(destinazione.getSaldo()+valore);
			
				destinazione.getStorico().add(new Transazione(sorgente, destinazione, valore));
			
				destinazione.sblocca();
				
				sorgente.getStorico().add(new Transazione(sorgente,destinazione,valore));
				
				sorgente.sblocca();
			}
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) 
	{
		Banca mediolanum=new Banca(); //costruita intorno ai Thread
		Tizio[] tizi= new Tizio[5];
		
		for(int i=0; i<5;i++)
		{
			tizi[i]=new Tizio(mediolanum,new ContoBancario());
			mediolanum.conti.add(tizi[i].conto);
			tizi[i].start();
		}

	}

}
