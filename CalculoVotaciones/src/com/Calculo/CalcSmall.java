package com.Calculo;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CalcSmall {

	public static Map<String, Integer> CalculateSmall(List<String> options, List<List<String>> votes) {
		Map<String,Integer> copeland;
		Map<String,Integer> resultados=new LinkedHashMap<String,Integer>();
		List<String> optionsTie=new LinkedList<String>();
		List<Integer> positionsTie=new LinkedList<Integer>();
		List<List<String>> votesTie=new LinkedList<List<String>>();

		boolean hayGanador = false;
		boolean empate = false;
		String ganadorKey = "";
		Integer ganadorValue = 0;

		copeland = CalcCopeland.CalculateCopeland(options, votes);

		for (int posicion = 0; posicion < copeland.size(); posicion++){
			String posicionKey = copeland.keySet().toArray()[posicion].toString();

			if (ganadorKey == "" || ganadorValue < copeland.get(posicionKey)) {
				ganadorKey = posicionKey;
				ganadorValue = copeland.get(posicionKey);
				hayGanador = true;
			} else if (ganadorValue == copeland.get(posicionKey) && hayGanador) {
				empate = true;
				if (optionsTie.size() == 0) {
					optionsTie.add(ganadorKey);
					positionsTie.add(options.indexOf(posicionKey));
				}
				optionsTie.add(posicionKey);
				positionsTie.add(posicion);
			}
		}

		if (empate) {
			for (List<String> vote:votes) {
				List<String> newVote = new LinkedList<String>();
				for (Integer position:positionsTie) {
					newVote.add(vote.get(position));
				}
				votesTie.add(newVote);
			}

			resultados = CalculateSmall(optionsTie, votesTie);
		} else {
			resultados = copeland;
		}

		return resultados;
	}

}