
public class Transazione 
{
	ContoBancario sorgente;
	ContoBancario destinatario;
	double valore;
	
	Transazione(ContoBancario A, ContoBancario B, double val)
	{
		this.sorgente=A;
		this.destinatario=B;
		this.valore=val;
	}
}

