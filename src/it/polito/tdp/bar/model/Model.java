package it.polito.tdp.bar.model;

public class Model {

	public String simulazione(){
		int time=0;
		
		Simulatore sim = new Simulatore();
		
		for(int i=0; i<2000; i++){
			time += (int)(1+Math.random()*10); // tra 1 e 10,99; prendendo la parte intera ho un numero tra 1 e 10
			int num_persone = (int)(1+Math.random()*10);
			int durata = 60 + (int)(1+Math.random()*60); // tra 0 e 60; poi sommo 60
			float tolleranza = (float)(Math.random());
			if(tolleranza>0.9)
				tolleranza=(float)0.9;
			
			sim.addGruppo(time, num_persone, durata, tolleranza);
		}
			
		sim.run();
		
		String res="";
		res+="Numero totale clienti: "+sim.getNumero_totale_clienti()+"\n"+"Numero clienti soddisfatti: "+sim.getNumero_clienti_soddisfatti()+"\n"+"Numero clienti insoddisfatti: "+sim.getNumero_clienti_insoddisfatti();
		return res;
	}

}