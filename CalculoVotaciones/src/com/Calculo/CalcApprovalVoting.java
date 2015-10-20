package com.Calculo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CalcApprovalVoting
{
	public static Map<String,Integer> CalculateApprovalVoting(List<String> options,List<String> votes)
	{
		Map<String,Integer> resultados=new HashMap<String,Integer>();
		
		for(String option:options)
		{
			resultados.put(option.toLowerCase(), 0);
			//Inicialmente no hay ningun voto en las opciones
		}
		
		for(String vote:votes)
		{
			Integer num = resultados.get(vote.toLowerCase());
			if(num != null)
			{
				resultados.put(vote.toLowerCase(), num+1);
			}
			
		}
		return resultados;
	}
	
	
	public static Map<String,Integer> CalculateApprovalVoting(List<String> options,List<String> votes,List<String> ganador)
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