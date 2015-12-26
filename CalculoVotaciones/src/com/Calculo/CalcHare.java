package com.Calculo;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CalcHare {

	public static Map<String, Integer> CalculateHare(List<String> options, List<List<String>> votes) {
		Map<String,Integer> resultados = new LinkedHashMap<String,Integer>();

		return CalculateHare(options, votes, resultados);
	}

	private static Map<String, Integer> CalculateHare(List<String> options, List<List<String>> votes, Map<String, Integer> resultados) {

		if (!resultados.isEmpty()) {
			Integer leastVotes = votes.size();
			String leastVoted = "";

			for (int posResult = 0; posResult < resultados.size(); posResult++)
			{
				String key = resultados.keySet().toArray()[posResult].toString();
				if (resultados.get(key) < leastVotes) {
					leastVotes = resultados.get(key);
					leastVoted = key;
				}
			}

			options.remove(options.indexOf(leastVoted));

			List<List <String>> newVotes = new LinkedList<List<String>>();
			for (List<String> vote:votes) {
				List<String> voteOptions = new LinkedList<String>();
				for (String option:vote) {
					if (!option.equals(leastVoted)) {
						voteOptions.add(option);
					}
				}
				newVotes.add(voteOptions);
			}

			votes = newVotes;
		}

		resultados = extractVotesLevel(options, votes);
		int majority = (votes.size() / 2) + 1;
		boolean hasMajority = false;

		for (int posRes = 0; posRes < resultados.size() && !hasMajority; posRes++) {
			if (resultados.get(resultados.keySet().toArray()[posRes].toString()) >= majority) {
				hasMajority = true;
			}
		}

		if (!hasMajority) {
			resultados = CalculateHare(options, votes, resultados);
		}

		return resultados;
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