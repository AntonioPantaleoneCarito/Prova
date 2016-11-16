import java.util.Random;

public class Tizio extends Thread
{
	ContoBancario conto;
	Banca b;
	
	Tizio(Banca b, ContoBancario conto)
	{
		this.b=b;
		this.conto=conto;
	}
	public void picatija()
	{
		Random random = new Random();
		int rand = random.nextInt(b.conti.size());
		b.trasferisci(conto, b.conti.get(rand), 100);
		System.out.println("Picatijo con i miei soldi: Sorgente "+conto.getID()+" Destinatario "+ b.conti.get(rand).getID());
		System.out.println("Adesso il mio saldo è "+conto.getSaldo());
	}
	
	public void run()
	{
		while(true)
		{
			picatija();
			try {
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
