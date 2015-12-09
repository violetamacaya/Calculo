package com.Calculo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalcMejorPeor {
	public static Map<String,Integer> CalculateMejorPeorPos(List<String> options,List<String> votesPos)
	{
	Map<String,Integer> resultados=new HashMap<String,Integer>();
		
		for(String option:options)
		{
			resultados.put(option.toLowerCase(), 0);
			//Inicialmente no hay ningun voto en las opciones
		}
		
		for(String vote:votesPos)
		{
			Integer num = resultados.get(vote.toLowerCase());
			if(num != null)
			{
				resultados.put(vote.toLowerCase(), num+1);
			}
			
		}
		return resultados;
	}	
	
	public static Map<String,Integer> CalculateMejorPeorNeg(List<String> options,List<String> votesNeg)
	{
	Map<String,Integer> resultados=new HashMap<String,Integer>();
		
		for(String option:options)
		{
			resultados.put(option.toLowerCase(), 0);
			//Inicialmente no hay ningun voto en las opciones
		}
		
		for(String vote:votesNeg)
		{
			Integer num = resultados.get(vote.toLowerCase());
			if(num != null)
			{
				resultados.put(vote.toLowerCase(), num+1);
			}
			
		}
		return resultados;
	}	
}