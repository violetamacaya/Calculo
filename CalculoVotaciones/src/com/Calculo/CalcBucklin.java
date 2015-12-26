package com.Calculo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CalcBucklin {

	public static Map<String, Integer> CalculateBucklin(List<String> options, List<List<String>> votes)
	{
		Map<String,Integer> resultados = new LinkedHashMap<String,Integer>();

		for(String option:options)
		{
			resultados.put(option, 0);
		}

		return CalculateBucklin(options, votes, 0, resultados);
	}

	private static Map<String, Integer> CalculateBucklin(List<String> options, List<List<String>> votes, Integer level, Map<String, Integer> resultados)
	{
		resultados = extractVotesLevel(options, votes, level, resultados);
		int majority = (votes.size() / 2) + 1;
		boolean hasMajority = false;

		for (int posRes = 0; posRes < resultados.size() && !hasMajority; posRes++) {
			if (resultados.get(resultados.keySet().toArray()[posRes].toString()) >= majority) {
				hasMajority = true;
			}
		}

		if (!hasMajority) {
			resultados = CalculateBucklin(options, votes, (level + 1), resultados);
		}

		return resultados;
	}

	private static Map<String, Integer> extractVotesLevel(List<String> options, List<List<String>> votes, Integer level, Map<String,Integer> levelResults)
	{
		for (List<String> vote:votes)
		{
			String optionVote = vote.get(level);
			levelResults.put(optionVote, levelResults.get(optionVote) + 1);
		}

		return levelResults;
	}

}