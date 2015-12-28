package com.Calculo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class CalcBrams
{
	public static Map<String,Integer> CalculateBrams(List<String> options,List<List<String>> votes)
	{
		Map<String,Integer> resultados=new HashMap<String,Integer>();

		for(String option:options)
		{
			resultados.put(option,  0);
			//Inicialmente no hay ningun voto en las opciones
		}

		for(int i = 0; i< options.size(); i++){
			List<Integer> levelResults = new LinkedList<Integer>();
			for (List<String> vote:votes)
			{
				
				Integer optionVote = Integer.parseInt(vote.get(i));
				levelResults.add(optionVote);
				//Para cada opción, hay que ver qué ha votado cada usuario
			}
			System.out.println("Lo almacenado para el nivel "+i+" es: "+levelResults);
			//Hacer la suma para todos los valores de ese array.

			int suma = 0;
			for(int valor: levelResults){
				suma += valor;
			}
			resultados.put(options.get(i), suma);
			
		}
		return resultados;
	}
}