package com.Calculo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalcRangeVoting {

	
	
	public static Map<String,Integer>  CalculateRangeVoting(List<String>opciones,int min,int max,List<List<String>>votos,List<String>winner)
	{
		Map<String,Integer> resultados=new HashMap<String,Integer>();
		for(String opcion:opciones)
		{
			resultados.put(opcion, 0);
		}
		validateVotes(opciones.size(),min,max,votos);
		
		for(List<String>voto:votos)
		{
			for(int i=0;i<voto.size();i++)
			{
				resultados.put(opciones.get(i), resultados.get(opciones.get(i))+Integer.parseInt(voto.get(i)));
			}
		}
		int win=0;
		for(String opcion:opciones)
		{
			int act=resultados.get(opcion);
			if(act>win)
			{
				win=act;
				winner.clear();
				winner.add(opcion);
			}
			else if(act==win)
			{
				winner.add(opcion);
			}
		}
		
		return resultados;
		
	}
	
	
	
	
	
	
	private static void validateVotes(int numOpt,int min,int max,List<List<String>> votos)
	{
		
		for(List<String> voto:votos)
		{
			if(voto.size()<numOpt)
			{
				votos.remove(voto);
			}
			for(String valor:voto)
			{
				if(Integer.parseInt(valor)<min || Integer.parseInt(valor)>max)
				{
					votos.remove(voto);
				}
			}
		}
			
	}
	
	
	
	
}
