package com.Calculo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CalcBorda {

	
	
	public static Map<String,Integer> CalcularBorda(List<List<String>> opciones,List<String> opcionesBorda,List<List<String>> votos,List<String> winner)
	{
		votos=validateVotes(opciones,votos);
		Map<String,Integer> map=new HashMap<String,Integer>();
		List<String> optBorda=CalcularOpcionesBorda(1,opciones);
		opcionesBorda.clear();
		for(String current:optBorda)
		{
			opcionesBorda.add(current);
		}
		
		
		for(String option:opcionesBorda)
		{
			map.put(option,0);
		}
		for(List<String> voto:votos)
		{
			int current=0;
			for(String opt:voto)
			{
				map.put(opt, map.get(opt)+(voto.size()-current));
				current++;
			}
		}
		int win=0;
		for(String option:opcionesBorda)
		{
			int act=map.get(option);
			if(act>win)
			{
				win=act;
				winner.clear();
				winner.add(option);
			}
			else if(act==win)
			{
				winner.add(option);
			}
		}
		
		return map;
	}
	
	
	
	
	
	
	
	public static List<String> CalcularOpcionesBorda(int x,List<List<String>> opciones)
	{
		
		List<String> lista=null;
		List<String> temp=new LinkedList<String>();
 		
		if(x!=opciones.size())
		{
			lista=CalcularOpcionesBorda(x+1,opciones);
		}
		if(x==opciones.size())
		{
			return opciones.get(x-1);
		}
		else
		{
			for(int i=0;i<opciones.get(x-1).size();i++)
			{
				for(int z=0; z<lista.size();z++)
				{
					temp.add(opciones.get(x-1).get(i)+"-"+lista.get(z));
				}
					
			}
		}
		return temp;		
	}
	
	
	
	
	
	/*
	 * Este metodo compueba si los votos tienen la forma correcta
	 */
	private static List<List<String>> validateVotes(List<List<String>>opciones,List<List<String>>votos)
	{
		int tamVoto=1;
		
		List<List<String>>votosValidos=new LinkedList<List<String>>();
		for(List<String> opcion:opciones)//Comprueba el tamaño que debe tener el voto
		{
			tamVoto=tamVoto*opcion.size();
		}
		System.out.println("tamaño voto->"+tamVoto);
		for(List<String> voto:votos)
		{
			boolean valido=true;
			if(voto.size()==tamVoto)
			{	
				for(int i=0;i<voto.size();i++)
				{
					String cadA=voto.get(i);
					for(int x=i+1;x<voto.size();x++)
					{
						String cadB=voto.get(x);
						if(cadA.equals(cadB))
						{
							valido=false;
						}
						
					}
				}
			}
			else
			{
				valido=false;
				
			}
			if(valido)
			{
				votosValidos.add(voto);
			}
			
			
		}
		votos=votosValidos;
		votosValidos=new LinkedList<List<String>>();
		
		
		for(List<String>voto:votos)
		{
			boolean valido=true;
			for(String parteEntera:voto)
			{
				//Separa cada una de las partes para verificar que hay un numero correcto de partes
				//y para comprobar que cada parte este presente el la correspondiente lista de Opciones
				String [] partes=parteEntera.split("-");
				if(partes.length==opciones.size())
				{
					for(int i=0;i<partes.length;i++)
					{
						boolean presente=false;
						
						List<String>opcion=opciones.get(i);
						for(String item:opcion)
						{
							if(partes[i].equals(item))
							{
								presente=true;
								System.out.println("PRESENTE");
							}
						}
						
						if(presente==false)
						{
							System.out.println("NO PRESENTE");
							valido=false;
						}
					}
				}
				else
				{
					valido=false;
				}
			}
			if(valido)
			{
				votosValidos.add(voto);
			}	
		}
		
		return votosValidos;	
	}
	
	
	
	
}
