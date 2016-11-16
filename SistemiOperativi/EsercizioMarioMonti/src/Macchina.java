

public class Macchina extends Thread
{

	
	Ponte marioMonti;
	int id;
	static int count=0;
	Ponte.Direzione direzione;
	boolean passato=false;
	
	Macchina(Ponte marioMonti,Ponte.Direzione direzione)
	{
		this.marioMonti=marioMonti;
		this.direzione= direzione;
		count++;
		id=count;
	}
	
	

	
	public void run()
	{
		while(!passato)
		{
			marioMonti.takeRL();
			
			if(!marioMonti.occupato||marioMonti.direzione==this.direzione)
			{
				marioMonti.takeWL();
				marioMonti.occupato=true;
				System.out.println(Ponte.ANSI_GREEN+"E' Libero: "+this.id +" Passa con direzione  "+this.direzione+Ponte.ANSI_RESET);
				passato=true;
				marioMonti.direzione=direzione;
				marioMonti.update(this);
				marioMonti.leaveWL();

			}
			else
			{
				if(marioMonti.direzione==Ponte.Direzione.MARE && marioMonti.mare.isEmpty())
				{
					marioMonti.takeWL();
					marioMonti.direzione=Ponte.Direzione.MONTAGNA;
					System.out.println("E' Occupato ma la strada per il MARE è vuota");
					marioMonti.occupato=false;
					
					marioMonti.leaveWL();

				}
				else if(marioMonti.direzione==Ponte.Direzione.MONTAGNA && marioMonti.monti.isEmpty())
				{
					marioMonti.takeWL();
					marioMonti.direzione=Ponte.Direzione.MARE;
					System.out.println("E' Occupato ma la strada per la MONTAGNA è vuota");
					marioMonti.occupato=false;
					marioMonti.leaveWL();

				} 
				else
				{
					//marioMonti.takeWL();
					try{
						this.sleep(2000);
						}
					 catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("E' Occupato "+this.id+" aspetta");
						
						
					//marioMonti.leaveWL();
					
				}
				
				
			}

			marioMonti.leaveRL();
		}
	}
	
	
}
