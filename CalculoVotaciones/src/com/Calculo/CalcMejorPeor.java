package com.Calculo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalcMejorPeor {
	public static Map<String,Integer> CalculateMejorPeor(List<String> options,List<String> votes)
	{
		Map<String,Integer> resultadosMejor=new HashMap<String,Integer>();
		Map<String,Integer> resultadosPeor=new HashMap<String,Integer>();
		
		for(String option:options)
		{
			resultadosMejor.put(option.toLowerCase(), 0);
			resultadosPeor.put(option.toLowerCase(), 0);

			//Inicialmente no hay ningun voto en las opciones
		}
		
		for(String vote:votes)
		{
			Integer num = resultadosMejor.get(vote.toLowerCase());
			if(num != null)
			{
				resultadosMejor.put(vote.toLowerCase(), num+1);
			}
			
		}
		return resultadosMejor;
	}
	
	
	public static Map<String,Integer> CalculateMejorPeor(List<String> options,List<String> votes,List<String> ganador)
	{
		Map<String,Integer> resultados=new HashMap<String,Integer>();
		
		for(String option:options)
		{
			
			resultados.put(option.toLowerCase(), 0);
		}
		
		
		for(String vote:votes)
		{
			Integer num=resultados.get(vote.toLowerCase());
			if(num!=null)
			{
				resultados.put(vote.toLowerCase(), num+1);
			}
			
		}
		for(String option:options)
		{
			System.out.println(option+"->"+resultados.get(option.toLowerCase()));
		}
		
	 	int votosGanador=0;
		
		for(String option:options)
		{
			int num=resultados.get(option.toLowerCase());
			if(num>votosGanador)
			{
				ganador.clear();
				ganador.add(option);
				votosGanador=num;
			}
			else if(num==votosGanador)
			{
				ganador.add(option);
			}
			
		}
		
		return resultados;
	}


}
