package com.Calculo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CalcCopeland {

	public static Map<String, Integer> CalculateCopeland(List<String> options, List<List<String>> votes) {
		Map<String,Integer> ganadores=new LinkedHashMap<String,Integer>();
		Map<String,Integer> perdedores=new LinkedHashMap<String,Integer>();
		Map<String,Integer> resultados=new LinkedHashMap<String,Integer>();

		for(String option:options)
		{
			ganadores.put(option, 0);
			perdedores.put(option, 0);
			resultados.put(option, 0);
		}

		for (int posA = 0; posA < options.size(); posA++) {
			for (int posB = 0; posB < options.size(); posB++) {
				if (posA != posB) {
					Integer ganaA = 0;
					Integer ganaB = 0;
					for(List<String> voto:votes)
					{
						if (Integer.parseInt(voto.get(posA)) < Integer.parseInt(voto.get(posB))) {
							ganaA += 1;
						} else if (Integer.parseInt(voto.get(posA)) > Integer.parseInt(voto.get(posB))) {
							ganaB += 1;
						}
					}
					if (ganaA > ganaB) {
						ganadores.put(options.get(posA), ganadores.get(options.get(posA)) + 1);
						perdedores.put(options.get(posB), perdedores.get(options.get(posB)) + 1);
					} else {
						ganadores.put(options.get(posB), ganadores.get(options.get(posB)) + 1);
						perdedores.put(options.get(posA), perdedores.get(options.get(posA)) + 1);
					}
				}
			}
		}

		for (int position = 0; position < resultados.size(); position++)
		{
			String opcion = resultados.keySet().toArray()[position].toString();

			int resultadoOpcion = ganadores.get(opcion) - perdedores.get(opcion);

			resultados.put(opcion, resultadoOpcion);
		}


		return resultados;
	}

}