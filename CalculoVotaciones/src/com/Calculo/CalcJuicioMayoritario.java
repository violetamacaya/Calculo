package com.Calculo;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CalcJuicioMayoritario {
	public static Map<String,Float> CalculateJuicioMayoritario(List<String> options,List<List<String>> votes)
	{
		Map<String,Float> resultados=new HashMap<String,Float>();

		for(String option:options)
		{
			resultados.put(option, (float) 0.0);
			//Inicialmente no hay ningun voto en las opciones
		}

		for(int i = 0; i< options.size(); i++){
			List<Integer> levelResults = new LinkedList<Integer>();
			for (List<String> vote:votes)
			{
				
				Integer optionVote = Integer.parseInt(vote.get(i));
				levelResults.add(optionVote);
				//Para cada opción, hay que ver qué ha votado cada usuario para hacer la mediana entre esos votos.
			}
			//Hacer la mediana para todos los valores de ese array.
			Collections.sort(levelResults);
			double median;
			if (levelResults.size() % 2 == 0)
			    median = ((double)levelResults.get(levelResults.size()/2) + (double)levelResults.get(levelResults.size()/2 - 1))/2;
			else
			    median = (double)levelResults.get(levelResults.size()/2);
			resultados.put(options.get(i), (float)median);
			
		}
		return resultados;
	}
}
