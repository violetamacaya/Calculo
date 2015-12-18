package com.Calculo;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class CalcMejorPeor {
	public static HashMap<String,Integer> CalculateMejorPeor(List<String> options,List<String> votesPos, List<String> votesNeg)
	{
		HashMap<String,Integer> resultados=new HashMap<String,Integer>();

		for(String option:options)
		{
			resultados.put(option, 0);
			//Inicialmente no hay ningun voto en las opciones
			int frecuenciaPos = Collections.frequency(votesPos, option);
			int frecuenciaNeg = Collections.frequency(votesNeg, option);
			int score = frecuenciaPos - frecuenciaNeg;
			resultados.put(option, score);
			System.out.println("Score: "+score);
		}
/*
		for(String vote:votesPos)
		{
			Integer num = resultados.get(vote.toLowerCase());
			resultados.put(vote.toLowerCase(), num+1);

		}
		for(String vote:votesNeg)
		{
			int numNeg = Integer.parseInt(resultados.get(vote.toLowerCase()).toString());
			resultados.put(vote.toLowerCase(), numNeg-1);
		}
*/
		return resultados;
	}	
}