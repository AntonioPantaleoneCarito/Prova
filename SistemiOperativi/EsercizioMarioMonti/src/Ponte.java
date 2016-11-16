import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ponte
{
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_RESET = "\u001B[0m";
	
	enum Direzione { MARE(0) , MONTAGNA(1);
		private final int i;
		Direzione(int i) {this.i=i;};
	}
	
	Direzione direzione;

	boolean occupato;
	
	
	
	List<Macchina> mare = new ArrayList<Macchina>();
	List<Macchina> monti = new ArrayList<Macchina>();
	
	Lock read_Lock=new ReentrantLock();
	Lock write_Lock=new ReentrantLock();
	
	
	Ponte()
	{
		occupato=false;
		direzione=Direzione.MARE;	
	}
	
	
	public void takeRL()
	{
		
		read_Lock.lock();
		System.out.println(ANSI_RED+"PRENDO READLOCK"+ANSI_RESET);
	}
	
	public void takeWL()
	{
		write_Lock.lock();
		System.out.println(ANSI_RED+"PRENDO WRITELOCK"+ANSI_RESET);
	}
	
	public void leaveRL()
	{
		read_Lock.unlock();
		System.out.println(ANSI_RED+"LASCIO READLOCK"+ANSI_RESET);
	}
	public void leaveWL()
	{
		write_Lock.unlock();
		System.out.println(ANSI_RED+"LASCIO WRITELOCK"+ANSI_RESET);
	}

	public void update(Macchina m)
	{
		
		
		if(m.direzione==Direzione.MARE)
		{
			mare.remove(m);
			System.out.println("RIMUOVO LA MACCHINA "+m.id+" DALLA CODA MARE");
		}
		else
		{
			monti.remove(m);
			System.out.println("RIMUOVO LA MACCHINA "+m.id+" DALLA CODA MONTAGNA");
		}
		

	}
	
}
