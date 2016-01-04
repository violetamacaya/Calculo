package com.Calculo;

import java.util.*;
@SuppressWarnings({ "unchecked", "rawtypes" })

public class CalcDodgson {

	private static Integer numSwaps = Integer.MAX_VALUE;
	private static Map<String, Integer> bestResult = new HashMap<String, Integer>();

	public static Map<String, Integer> CalculateDodgson(List<String> options, List<List<String>> votes) {
		Map<String, Integer> condorcet = CalcCondorcet.CalculateCondorcet(options, votes);
		Map<String, Integer> resultado = new HashMap<String, Integer>();

		List<Integer> valores = new ArrayList(condorcet.values());
		Set<Integer> valoresUnicos = new HashSet<Integer>(condorcet.values());
		if(valores.size() != valoresUnicos.size()) {
			for (Integer candidate = 1; candidate <= options.size(); candidate++) {
				CalculateDodgsonSwap(options, votes, candidate.toString());
			}
			resultado = CalcDodgson.bestResult;
		} else {
			resultado = condorcet;
		}

		return resultado;
	}

	private static void CalculateDodgsonSwap(List<String> options, List<List<String>> votes, String candidate) {
		int max_swaps = options.size();
		int swaps = 0;
		boolean solved = false;
		Map<String, Integer> condorcet = new HashMap<String, Integer>();

		while (swaps < max_swaps && !solved) {
			votes = pairWiseSwap(votes, candidate);
			condorcet = CalcCondorcet.CalculateCondorcet(options, votes);

			List<Integer> valores = new ArrayList(condorcet.values());
			Set<Integer> valoresUnicos = new HashSet<Integer>(condorcet.values());
			if(valores.size() == valoresUnicos.size()) {
				solved = true;
			}
			swaps++;
		}

		if (CalcDodgson.numSwaps > swaps && solved) {
			CalcDodgson.numSwaps = swaps;
			CalcDodgson.bestResult = condorcet;
		}
	}

	private static List<List<String>> pairWiseSwap(List<List<String>> votes, String candidate) {
		List<List<String>> newVotes = new LinkedList<List<String>>();

		for (List<String> vote:votes) {
			List<String> newVote = new LinkedList<String>();

			int currentIndex = vote.indexOf(candidate);
			int swapIndex = currentIndex + 1;
			if (swapIndex >= vote.size()) {
				swapIndex = currentIndex - 1;
			}
			String swapElement = vote.get(swapIndex);
			String candidateElement = vote.get(currentIndex);

			for (int i = 0; i < vote.size(); i++) {
				if (i == swapIndex) {
					newVote.add(candidateElement);
				} else if (i == currentIndex) {
					newVote.add(swapElement);
				} else {
					newVote.add(vote.get(i));
				}
			}

			newVotes.add(newVote);
		}

		return newVotes;
	}

}