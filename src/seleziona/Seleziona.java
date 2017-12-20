package seleziona;

import java.io.Serializable;

import centrourbano.*;

public class Seleziona implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//Seleziona contiene il settore selezionato, il massimo e il minimo ammissibili nella selezione, 
	//il tipi di selezione scelta e un array che conterra tutti i lotti scelti
	
	public Seleziona(CentroUrbano centr,double ma,double mi,int scelt) {
        lista= new Lotti[DIM_LIST];
		centro=centr;
		max=ma;
		min=mi;
		scelta=scelt;
		
	}
	
	//Il metodo scelta selezione il metodo di selezione
	
	public void Scelta() {
		switch(scelta) {
		case 0:coeffEff();
		       break;
		case 1:coeffInv();
		       break;
		case 2:val();
		       break;
		default: System.out.println("Selezione Errata\n");
		}
		
	}
	
	//Il metodo coeffEff selezione i lotti presenti nel settore selezionato che rispettano 
	//le condizioni di max e min di coeff. di Efficienza dei lotti
	
	private void coeffEff() {
		int j=0;
		for(int l=0;l<2;l++)
			for(int k=0;k<3;k++)
				for(int i=0;i<MAX_X;i++)
					for(int y=0;y<MAX_Y;y++) {
						if(centro.lista[l][k].getLotto(i, y).getTip()!=0)
						if(centro.lista[l][k].lista[i][y].getCeff()>min && centro.lista[l][k].lista[i][y].getCeff()<max) {
							lista[j]=centro.lista[l][k].lista[i][y];
						}
					}
		this.setCount(j);
	}
	
	//Il metodo coeffInv seleziona i lotti presenti nel settore selezionato che rispettano 
	//le condizioni di max e min di coeff. di Invecchiamento dei lotti
	
	private void coeffInv() {
		int j=0;
		for(int l=0;l<2;l++)
			for(int k=0;k<3;k++)
				for(int i=0;i<MAX_X;i++)
					for(int y=0;y<MAX_Y;y++) {	
						if(centro.lista[l][k].getLotto(i, y).getTip()!=0)
						if(centro.lista[l][k].getLotto(i, y).getCinv()>min && centro.lista[l][k].getLotto(i, y).getCinv()<max)
							lista[j]=centro.lista[l][k].getLotto(i, y);
					}
		this.setCount(j);
	}
	
	//Il metodo val seleziona i lotti presenti nel settore selezionato che rispettano 
    //le condizioni di max e min di Valore dei lotti
	
	
	private void val() {
		int j=0;
		for(int l=0;l<2;l++)
			for(int k=0;k<3;k++)	
				for(int i=0;i<MAX_X;i++)
					for(int y=0;y<MAX_Y;y++)
						if(centro.lista[l][k].lista[i][y].getTip()==3 && centro.lista[l][k].getLotto(i, y).getTip()!=0)
						if(centro.lista[l][k].getLotto(i, y).getValore()>min && centro.lista[l][k].getLotto(i, y).getValore()<max)
							lista[j]=centro.lista[l][k].getLotto(i, y);
		this.setCount(j);
	}
	
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int c) {
		count+=c;
	}
	
	public int getScelta() {
		return scelta;
	}
	

    private CentroUrbano centro;
    private double max;
    private double min;
    private int scelta;
    private int count=0;
    
    
    private static final int MAX_X = 2;
	private static final int MAX_Y = 4;
    private final static int DIM_LIST=90;
    
    
    public Lotti lista[];
}
