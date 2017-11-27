package centrourbano;

import java.io.Serializable;

import eccezioni.LottoLibero;


public class Settori implements Serializable{
	private static final long serialVersionUID = 1L;

	public Settori() {
		setValore(0);
		lista = new Lotti[3][5];
	}
	/**
	 * 
	 * Costruttore per decidere le dimensioni di un settore
	 * 
	 * @param altezza righe
	 * @param larghezza colonne
	 * 
	 */
	
	
	/**
	 * Calcola il prezzo del lotto
	 * @param settore
	 */
	
	public int vendiEdificio(int riga, int colonna) {
		Lotti vend = getLotto(riga, colonna);
		int tot = (vend.getVal() + this.getValore()) * vend.getCeff();
		rmEdificio(riga, colonna);
		return tot;
		
	}
	
	
	
	/**
	 * In base al tipo di lotto il metodo esegue una di 3 azioni
	 * -Edificio pubblico: Incrementa di 1 il valore del settore
	 * -Strada: Trova i lotti adiacenti nelle 8 direzioni e aumenta il loro valore di 1
	 * -Edificio privato: Controlla i lotti adiacenti in cerca di strade e aumenta per ogni strada il valore di 1
	 * @param Nuovo
	 */
	public void addLotto(Lotti Nuovo, int riga, int colonna) {
		if(Nuovo.getTip() == 1)
			addStrada( Nuovo,riga,colonna);
		if(Nuovo.getTip() == 2)
			addepub(Nuovo,riga,colonna);
		if(Nuovo.getTip() == 3)
			addepriv(Nuovo,  riga, colonna);
	}
	
	
	
	
	/**
	 * Rimuove l'edificio e inverte i cambiamenti che questo influiva sui vicini e sul settore.
	 * -Edificio privato: Semplice rimozione
	 * -Edificio pubblico: Il valore del settore viene ridotto di 1
	 * -Strada: Riduce di 1 il valore dei lotti adiacenti
	 * 
	 * 
	 * Nel caso in cui il lotto sia gi� libero lancia un eccezione di tipo "LottoLibero"
	 * @param riga
	 * @param colonna
	 */
	
	public void rmEdificio(int riga, int colonna) {
		if((getLotto(riga, colonna)).getTip() == 0)
			throw new LottoLibero();
		if((getLotto(riga, colonna)).getTip() == 1)
			rmStrada(riga,colonna);
		if((getLotto(riga, colonna)).getTip() == 2)
			rmepub(riga,colonna);
		if((getLotto(riga, colonna)).getTip() == 3)
			rmepriv(riga,colonna);
		
	}
	
	/**
	 * Cambia l'edificio selezionato con un altro aggiornando gli effetti
	 * @param riga
	 * @param colonna
	 * @throws LottoLibero
	 */
	
	public void cgEdificio(Lotti nuovo,int riga, int colonna) {
		rmEdificio(riga, colonna);
		addLotto(nuovo, riga , colonna);
	}
	
	public Lotti getLotto(int riga, int colonna) {
		Lotti io = lista[riga][colonna]; 		
		
		
		return io;
	}
	
	/**
	 * Aggiunge una strada alla posizione indicata da riga e colonna e aumenta di uno 
	 * il valore di tutti i lotti adiacenti
	 * @param Nuovo
	 * @param riga
	 * @param colonna
	 */
	private void addStrada(Lotti Nuovo, int riga, int colonna) {
			lista[riga][colonna] = Nuovo;
			lista[riga][colonna - 1].setVal(lista[riga][colonna - 1].getVal() +1);
			lista[riga][colonna + 1].setVal(lista[riga][colonna + 1].getVal() +1);
			lista[riga - 1][colonna].setVal(lista[riga - 1][colonna].getVal() +1);
			lista[riga - 1][colonna - 1].setVal(lista[riga - 1][colonna - 1].getVal() +1);
			lista[riga - 1][colonna + 1].setVal(lista[riga - 1][colonna + 1].getVal() +1);
			lista[riga + 1][colonna].setVal(lista[riga + 1][colonna].getVal() +1);
			lista[riga + 1][colonna - 1].setVal(lista[riga + 1][colonna - 1].getVal() +1);
			lista[riga + 1][colonna + 1].setVal(lista[riga + 1][colonna + 1].getVal() +1);
		}
	
	/**
	 * Aggiunge un edificio pubblico e aumenta il valore del settore di 1
	 * @param Nuovo
	 * @param riga
	 * @param colonna
	 */
	private void addepub(Lotti Nuovo, int riga, int colonna) {
		lista[riga][colonna] = Nuovo;
		this.setValore(this.getValore() + 1);
	}
	
	private void addepriv(Lotti Nuovo, int riga, int colonna) {
		lista[riga][colonna] = Nuovo;
	}
	
	/**
	 * Rimuovi la strada ed elimina il bonus agli edifici adiacenti
	 * @param riga
	 * @param colonna
	 */
	private void rmStrada(int riga, int colonna) {
		lista[riga][colonna].setVal(0);
		lista[riga][colonna].setTip(0);
		lista[riga][colonna - 1].setVal(lista[riga][colonna - 1].getVal() -1);
		lista[riga][colonna + 1].setVal(lista[riga][colonna + 1].getVal() -1);
		lista[riga - 1][colonna].setVal(lista[riga - 1][colonna].getVal() -1);
		lista[riga - 1][colonna - 1].setVal(lista[riga - 1][colonna - 1].getVal() -1);
		lista[riga - 1][colonna + 1].setVal(lista[riga - 1][colonna + 1].getVal() -1);
		lista[riga + 1][colonna].setVal(lista[riga + 1][colonna].getVal() -1);
		lista[riga + 1][colonna - 1].setVal(lista[riga + 1][colonna - 1].getVal() -1);
		lista[riga + 1][colonna + 1].setVal(lista[riga + 1][colonna + 1].getVal() -1);
	}
	
	/**
	 * Rimuovi l'edificio pubblico e rimuove il +1 bonus
	 * @param riga
	 * @param colonna
	 */
	
	private void rmepub(int riga,int colonna) {
		lista[riga][colonna].setTip(0);
		lista[riga][colonna].setVal(0);
		this.setValore(this.getValore() - 1);
	
	}
	
	private void rmepriv(int riga,int colonna) {
		lista[riga][colonna].setTip(0);
		lista[riga][colonna].setVal(0);
	}
	
	public int getValore() {
		return valore;
	}
	public void setValore(int valore) {
		this.valore = valore;
	}




	private int valore;

	
	
	public Lotti[][] lista;
}
