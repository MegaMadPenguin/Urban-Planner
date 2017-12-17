package centrourbano;

import java.io.Serializable;
import edifici.*;

public class Lotti implements Serializable{
	private static final long serialVersionUID = 1L;

	public Lotti() {
		edificio = VUOTO;
	}
	

	public Lotti(Edificabile NuovoEdificio) {
		edificio = NuovoEdificio;
	}
	
	/**
	 * La funzione verrà usata per modificare il tipo di lotto
	 * sostituendolo con il nuovo lotto
	 * 
	 * @param NuovoEdificio il nuovo lotto
	 */
	
	//public void modEdi(Edificabile NuovoEdificio) {
	//	edificio = NuovoEdificio;
	//}

	public void setEdificio(Edificabile NuovoEdificio) {
		edificio = NuovoEdificio;
	}
	
	/**
	 * Il valore del lotto viene cambiato al nuovo valore
	 * @param NuovoValore Il valore che verra assegnato a edificio
	 */
	public void setValore(int NuovoValore) {
		((EPrivato) edificio).setValore(NuovoValore);
	}
	
	
	/**
	 * Restituisce il valore dell'edificio
	 */
	
	public int getValore() {
		return ((EPrivato) edificio).getValore();
	}

	/**
	 * Restituisce il tipo dell'edificio
	 */
	
	public int getTip() {
		return edificio.getTipo();
	}
	
	/**
	 * Restituisce il coefficiente di efficienza dell'edificio
	 */
	
	public int getCeff() {
		return edificio.getCeff();
	}
	
	/**
	 * Cambia il coeffficiente di efficienza
	 */
	
	public void setCeff(int ceff) {
		edificio.setCeff(ceff);
	}
	
	/**
	 * Restituisce il coefficiente di invecchiamento
	 */
	
	public int getCinv() {
		return edificio.getCinv();
	}
	
	/**
	 * Imposta il coefficiente di invecchiamento
	 */
	
	public void setCinv(int cinv) {
		edificio.setCinv(cinv);
	}


	private Edificabile edificio;
	
//Questo sta qua da un po, ma lo usiamo ancora? 
	// o lo si puo eliminare?
	
	private static Edificabile VUOTO = new Edificabile(){
		private static final long serialVersionUID = 1L;

		public int getTipo() {
			return 0;
		}	
	};
}
