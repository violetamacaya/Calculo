package com.Calculo;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CalcNanson {

	public static Map<String, Integer> CalculateNanson(List<String> options, List<List<String>> votes)
	{
		Map<String,Integer> resultados = new LinkedHashMap<String, Integer>();
		Map<String,Integer> resMajority;

		for(String option:options) {
			resultados.put(option, 0);
		}

		resMajority = extractVotesLevel(options, votes);
		int majority = (votes.size() / 2) + 1;
		boolean hasMajority = false;

		for (int posRes = 0; posRes < resMajority.size() && !hasMajority; posRes++) {
			if (resMajority.get(resMajority.keySet().toArray()[posRes].toString()) >= majority) {
				hasMajority = true;
			}
		}

		if (!hasMajority) {
			for (int levelPos = 0; levelPos < options.size(); levelPos++) {
				resultados = calculateBorda(options, votes, levelPos, resultados);
			}

			Integer leastVotes = Integer.MAX_VALUE;
			List<String> leastVoted = new LinkedList<String>();

			for (int posResult = 0; posResult < resultados.size(); posResult++) {
				String key = resultados.keySet().toArray()[posResult].toString();
				if (resultados.get(key) <= leastVotes) {
					leastVotes = resultados.get(key);
					leastVoted.add(key);
				}
			}

			for (String least : leastVoted){
				options.remove(least);
			}

			List<List <String>> newVotes = new LinkedList<List<String>>();
			for (List<String> vote:votes) {
				List<String> voteOptions = new LinkedList<String>();
				for (String option:vote) {
					if (!leastVoted.contains(option)) {
						voteOptions.add(option);
					}
				}
				newVotes.add(voteOptions);
			}

			votes = newVotes;

			resultados = CalculateNanson(options, votes);
		} else {
			resultados = resMajority;
		}

		return resultados;
	}

	private static Map<String, Integer> calculateBorda(List<String> options, List<List<String>> votes, Integer level, Map<String, Integer> levelResults) {

		for (List<String> vote:votes)
		{
			String optionVote = vote.get(level);
			levelResults.put(optionVote, levelResults.get(optionVote) + (options.size() - 1) - level);
		}

		return levelResults;
	}

	private static Map<String, Integer> extractVotesLevel(List<String> options, List<List<String>> votes) {
		Map<String,Integer> levelResults = new LinkedHashMap<String,Integer>();

		for(String option:options) {
			levelResults.put(option, 0);
		}

		for (List<String> vote:votes)
		{
			String optionVote = vote.get(0);
			levelResults.put(optionVote, levelResults.get(optionVote) + 1);
		}

		return levelResults;
	}

}