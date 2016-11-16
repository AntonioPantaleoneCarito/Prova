import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MarioMonti {

	public static void main(String[] args) throws InterruptedException
	{
		int numeroAuto=10;
		
		Ponte.Direzione[] direzioni={Ponte.Direzione.MARE,Ponte.Direzione.MONTAGNA};
		Random rand=new Random();
		
		Ponte marioMonti = new Ponte();
		List<Macchina> macchine = new ArrayList<Macchina>();
		
		for(int i=0;i<=numeroAuto;i++)
		{
			macchine.add(new Macchina(marioMonti, direzioni[rand.nextInt(2)]));
			
			System.out.println("CREATA MACCHINA "+ macchine.get(i).id +" CON DIREZIONE "+ macchine.get(i).direzione);
			
			if(macchine.get(i).direzione==Ponte.Direzione.MARE)
				marioMonti.mare.add(macchine.get(i));
			else
				marioMonti.monti.add(macchine.get(i));
		}
		
		for(int i=0;i<macchine.size();i++)
		{
			macchine.get(i).start();
			System.out.println(macchine.get(i).id+" PARTE");
			
			
		}
		
		
			System.out.println("FINE MAIN");	

	}

}
