package it.polito.tdp.bar.model;

public class Event implements Comparable<Event>{
	
	public enum EventType {ARRIVO_GRUPPO_CLIENTI, PARTENZA_GRUPPO_CLIENTI};
	private int time;
	private EventType type;
	private int numPersone;
	private int durata;
	private float tolleranza;
	
	public Event(int time, EventType type, int numPersone, int durata, float tolleranza) {
		super();
		this.time = time;
		this.type = type;
		this.numPersone = numPersone;
		this.durata = durata;
		this.tolleranza = tolleranza;
	}
	
	public Event(int time, EventType type, int postiLiberati) {
		super();
		this.time = time;
		this.type = type;
		this.numPersone = postiLiberati;
	}

	/**
	 * @return the time
	 */
	public int getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(int time) {
		this.time = time;
	}

	/**
	 * @return the type
	 */
	public EventType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(EventType type) {
		this.type = type;
	}

	/**
	 * @return the numPersone
	 */
	public int getNumPersone() {
		return numPersone;
	}

	/**
	 * @param numPersone the numPersone to set
	 */
	public void setNumPersone(int numPersone) {
		this.numPersone = numPersone;
	}

	/**
	 * @return the durata
	 */
	public int getDurata() {
		return durata;
	}

	/**
	 * @param durata the durata to set
	 */
	public void setDurata(int durata) {
		this.durata = durata;
	}

	/**
	 * @return the tolleranza
	 */
	public float getTolleranza() {
		return tolleranza;
	}

	/**
	 * @param tolleranza the tolleranza to set
	 */
	public void setTolleranza(float tolleranza) {
		this.tolleranza = tolleranza;
	}
	
	public int compareTo (Event other){
		return this.time-other.time;
	}
	
}