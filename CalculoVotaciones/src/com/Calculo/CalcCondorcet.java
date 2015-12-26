package com.Calculo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CalcCondorcet {

	
	
	public static Map<String,Integer> CalculateCondorcet(List<String> opciones, List<List<String>> votes)
	{
		Map<String,Integer> ganadores=new LinkedHashMap<String,Integer>();
		
		for(String option:opciones)
		{
			ganadores.put(option,0);
		}
		for(List<String> voto:votes)
		{
			int contador = 0;
			for (String prioridad : voto){
				int contadorComparador = 0;
				for(String comparador : voto){
					if(Integer.parseInt(prioridad) > Integer.parseInt(comparador) && contador != contadorComparador){
						String opcionVoto = ganadores.keySet().toArray()[contador].toString();
						int valorVoto = ganadores.get(opcionVoto);
						ganadores.put(opcionVoto, valorVoto +1);
					}
					else if(Integer.parseInt(prioridad) == Integer.parseInt(comparador) && contador != contadorComparador){
						String opcionVoto = ganadores.keySet().toArray()[contador].toString();
						int valorVoto = ganadores.get(opcionVoto);
						ganadores.put(opcionVoto, valorVoto +1);
						//Sobre ambos votos, porque son iguales.
						
						String opcionVotoComp = ganadores.keySet().toArray()[contadorComparador].toString();
						int valorVotoComp = ganadores.get(opcionVotoComp);
						ganadores.put(opcionVotoComp, valorVotoComp +1);
					}
					contadorComparador++;
					
				}
				contador++;
			}
		}
		
		return ganadores;
	}
}
