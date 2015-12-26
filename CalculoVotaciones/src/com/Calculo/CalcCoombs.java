package com.Calculo;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CalcCoombs {

	public static Map<String, Integer> CalculateCoombs(List<String> options, List<List<String>> votes) {
		Map<String,Integer> resultados;
		Map<String,Integer> worseRow;

		resultados = extractVotesLevel(options, votes, 0);
		worseRow = extractVotesLevel(options, votes, (options.size() - 1));
		int majority = (votes.size() / 2) + 1;

		boolean hasMajority = false;

		for (int posRes = 0; posRes < resultados.size() && !hasMajority; posRes++) {
			if (resultados.get(resultados.keySet().toArray()[posRes].toString()) >= majority) {
				hasMajority = true;
			}
		}

		if (!hasMajority) {
			Integer mostVotes = 0;
			String mostVoted = "";

			for (int posResult = 0; posResult < worseRow.size(); posResult++)
			{
				String key = worseRow.keySet().toArray()[posResult].toString();
				if (worseRow.get(key) > mostVotes) {
					mostVotes = resultados.get(key);
					mostVoted = key;
				}
			}

			options.remove(options.indexOf(mostVoted));

			List<List <String>> newVotes = new LinkedList<List<String>>();
			for (List<String> vote:votes) {
				List<String> voteOptions = new LinkedList<String>();
				for (String option:vote) {
					if (!option.equals(mostVoted)) {
						voteOptions.add(option);
					}
				}
				newVotes.add(voteOptions);
			}

			votes = newVotes;

			resultados = CalculateCoombs(options, votes);
		}

		return resultados;
	}

	private static Map<String, Integer> extractVotesLevel(List<String> options, List<List<String>> votes, Integer level) {
		Map<String,Integer> levelResults = new LinkedHashMap<String,Integer>();

		for(String option:options) {
			levelResults.put(option, 0);
		}

		for (List<String> vote:votes)
		{
			String optionVote = vote.get(level);
			levelResults.put(optionVote, levelResults.get(optionVote) + 1);
		}

		return levelResults;
	}

}