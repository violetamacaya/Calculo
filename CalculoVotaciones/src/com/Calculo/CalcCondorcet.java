package com.Calculo;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CalcCondorcet {

	
	
	public static Map<String,Integer> CalculateCondorcet(List<String> opciones, List<List<String>> votes)
	{
		Map<String,Integer> resultados=new LinkedHashMap<String,Integer>();
		
		for(String option:opciones)
		{
			resultados.put(option,0);
		}
		System.out.println("OLA KE ASE " +votes);
		for(List<String> voto:votes)
		{
			int contador = 0;
			for (String prioridad : voto){
				int contadorComparador = 0;
				for(String comparador : voto){
					if(Integer.parseInt(prioridad) > Integer.parseInt(comparador)){
						String opcionVoto = resultados.keySet().toArray()[contador].toString();
						int valorVoto = resultados.get(opcionVoto);
						resultados.put(opcionVoto, valorVoto +1);
					}
					else if(Integer.parseInt(prioridad) == Integer.parseInt(comparador)){
						String opcionVoto = resultados.keySet().toArray()[contador].toString();
						int valorVoto = resultados.get(opcionVoto);
						resultados.put(opcionVoto, valorVoto +1);
						//Sobre ambos votos, porque son iguales.
						
						String opcionVotoComp = resultados.keySet().toArray()[contadorComparador].toString();
						int valorVotoComp = resultados.get(opcionVotoComp);
						resultados.put(opcionVotoComp, valorVotoComp +1);
					}
					contadorComparador++;
					
				}
				contador++;
			}
		}
		
		return resultados;
	}
}
