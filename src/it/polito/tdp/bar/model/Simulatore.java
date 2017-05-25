package it.polito.tdp.bar.model;

import java.util.PriorityQueue;
import java.util.TreeMap;

import it.polito.tdp.bar.model.Event.EventType;

public class Simulatore {
	
	// Parametri di simulazione
	// private int gruppiTotali; // numero totale di gruppi
	
	// Stato del modello
	private TreeMap<Integer, Integer> situazioneTavoli;
	
	// Variabili di interesse
	private int numero_totale_clienti = 0; //numero clienti arrivati al bar
	private int numero_clienti_soddisfatti = 0;
	private int numero_clienti_insoddisfatti = 0;
	
	// Lista degli eventi
	PriorityQueue<Event> queue ;

	public Simulatore() {
		super();
		this.queue = new PriorityQueue<>() ;
		this.situazioneTavoli = new TreeMap<Integer, Integer>();
		situazioneTavoli.put(4, 5); //da 4 posti ci sono 5 tavoli
		situazioneTavoli.put(6, 4); //da 6 posti ci sono 4 tavoli
		situazioneTavoli.put(8, 4); //da 8 posti ci sono 4 tavoli
		situazioneTavoli.put(10, 2); //da 10 posti ci sono 2 tavoli
	}
	
	public void addGruppo(int time, int num_persone, int durata, float tolleranza){
		queue.add(new Event(time, EventType.ARRIVO_GRUPPO_CLIENTI, num_persone, durata, tolleranza));
	}
	
	public void run(){
		while(!queue.isEmpty()) {
			Event e = queue.poll();
			
			switch(e.getType()) {
			case ARRIVO_GRUPPO_CLIENTI:
				for(Integer i : situazioneTavoli.keySet())
					if(e.getNumPersone()<=i && situazioneTavoli.get(i)>0 && this.metaBar()==true){ // se c'è posto al tavolo per quel gruppo 
						int numTavoliTipo=situazioneTavoli.get(i);
						situazioneTavoli.put(i, numTavoliTipo--);
						this.numero_totale_clienti+=e.getNumPersone();
						this.numero_clienti_soddisfatti+=e.getNumPersone();
						int durata = e.getDurata();
						queue.add(new Event(e.getTime()+durata, EventType.PARTENZA_GRUPPO_CLIENTI, i)); // salvo anche il numero di posti che si liberano
						return;
					}
				//se non c'è posto al tavolo vado al bancone con una decisone
				float decisione = (float)(Math.random());
				if(decisione<=e.getTolleranza()){
					this.numero_totale_clienti+=e.getNumPersone();
					this.numero_clienti_soddisfatti+=e.getNumPersone();
				}
				else{
					this.numero_totale_clienti+=e.getNumPersone();
					this.numero_clienti_insoddisfatti+=e.getNumPersone();
				}
			break;				
			case PARTENZA_GRUPPO_CLIENTI:
				int i = e.getNumPersone();
				situazioneTavoli.put(i, situazioneTavoli.get(i)+1);
			break;
			}
		}
	}

	/**
	 * @return the numero_totale_clienti
	 */
	public int getNumero_totale_clienti() {
		return numero_totale_clienti;
	}

	/**
	 * @return the numero_clienti_soddisfatti
	 */
	public int getNumero_clienti_soddisfatti() {
		return numero_clienti_soddisfatti;
	}

	/**
	 * @return the numero_clienti_insoddisfatti
	 */
	public int getNumero_clienti_insoddisfatti() {
		return numero_clienti_insoddisfatti;
	}
	
	public boolean metaBar(){
		int postiOccupati=0;
		for(Integer i : situazioneTavoli.keySet())
			postiOccupati+=i*situazioneTavoli.get(i);
		if(postiOccupati<48)
			return true;
		return false;
	}

}
