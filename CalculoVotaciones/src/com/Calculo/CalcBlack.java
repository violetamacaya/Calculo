package com.Calculo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CalcBlack {

	@SuppressWarnings("unchecked")
	public static Map<String, Integer> CalculateBlack(List<String> options, List<List<String>> votes) {
		Map<String,Integer> resultados=new LinkedHashMap<String,Integer>();

		for(String option:options)
		{
			resultados.put(option,0);
		}
		for(List<String> voto:votes)
		{
			int contador = 0;
			for (String prioridad : voto){
				int contadorComparador = 0;
				for(String comparador : voto){
					if(Integer.parseInt(prioridad) > Integer.parseInt(comparador) && contador != contadorComparador){
						String opcionVoto = resultados.keySet().toArray()[contador].toString();
						int valorVoto = resultados.get(opcionVoto);
						resultados.put(opcionVoto, valorVoto +1);
					}
					else if(Integer.parseInt(prioridad) == Integer.parseInt(comparador) && contador != contadorComparador){
						String opcionVoto = resultados.keySet().toArray()[contador].toString();
						int valorVoto = resultados.get(opcionVoto);
						resultados.put(opcionVoto, valorVoto +1);
						//Sobre ambos votos, porque son iguales.

						String opcionVotoComp = resultados.keySet().toArray()[contadorComparador].toString();
						int valorVotoComp = resultados.get(opcionVotoComp);
						resultados.put(opcionVotoComp, valorVotoComp +1);
					}
					contadorComparador++;

				}
				contador++;
			}
		}
		//Hasta aquí el metodo condorcet. Hay que comprobar si hay empates.

		@SuppressWarnings("rawtypes")
		List<Integer> valores = new ArrayList(resultados.values());
		Set<Integer> valoresUnicos = new HashSet<Integer>(resultados.values());
		if(valores.size() != valoresUnicos.size()){
			//Hay que hacer borda.
			
			for(String option:options)
			{
				resultados.put(option,0);
			}
			for(List<String> voto:votes){
				HashMap<String, Integer> ordenado = new HashMap<String, Integer>();
				for(int i=0; i<options.size(); i++){
					ordenado.put(options.get(i), Integer.parseInt(voto.get(i)));
				}
				List<String> lista= options;
				for(int i=0;i<lista.size();i++)
				{
					for(int j=0;j<lista.size()-i-1;j++)
					{
						if(ordenado.get(lista.get(j+1))>ordenado.get(lista.get(j)))
						{
							String aux=lista.get(j+1);
							lista.set(j+1, lista.get(j));
							lista.set(j, aux);
						}
					}
				}
				
				int contador = lista.size();
				for(String opcion: lista){					
					resultados.put(opcion, contador);
					contador--;
				}
			}


		}

		return resultados;
	}

}
