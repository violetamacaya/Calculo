package com.Calculo;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class Calculate {

	public static void main(String [] args)
	{
		Integer numOpt=null;
		
		
		
		if(args.length==0)
		{
			//DATOS ERRONEOS
			valoresEntrada();
		}
		else if(args[0].equals("relativeMajority")||args[0].equals("mayoriaRelativa")|| args[0].equals("0"))
		{
			
			if(args.length==1)
			{	
				valoresEntrada();
			}
			else
			{
				try
				{
					numOpt=Integer.parseInt(args[1]);
				}
				catch(Exception e)
				{
					numOpt=null;
					System.out.println("numOpt debe de ser un entero");
				}
				if(numOpt!=null)
				{
					if(numOpt<2)
					{
						System.out.println("Minimo 2 opciones");
					}
					if(args.length<numOpt+1)
					{
						System.out.println("numero opciones introducido no se corresponde con numOpt");
					}
					else
					{
						gestionarMayoriaRelativa(args,numOpt);
					}
				}
			
			}
		}
		else if(args[0].equals("kemeny")|| args[0].equals("1"))
		{
			gestionarKemeny(args);
		}
		else if(args[0].equals("help"))
		{
			help();
		}
		else
		{
			valoresEntrada();
		}
	}
	private static void help()
	{
		System.out.print("\n\n\n");
		System.out.println("En la votacion kemeny,los votos introducidos deberan tener la forma:\n"
				+ "\"OpcionAx-OpcionBx>OpcionAx-OpcionBx>OpcionAx-OpcionBx>OpcionAx-OpcionBx\"\n"
				+ "\n Todo entre comillas"
				+ "\nSi alguna opcion estubiera repetida o faltara alguna de las 4 seria voto nulo\n");
	}
		
	private static void valoresEntrada()
	{
		System.out.println("Introduzca :");
		System.out.println("\tMayoria Relativa-> [0 , relativeMajority , mayoriaRelativa] \n,\t\tnumOpt [opA opB],[voto1,voto2...] ");
		System.out.println("\tKemeny-----------> [1 , kemeny]\n\t OpcionA1 OpcionA2 OpcionB1 OpcionB2 ,[voto1,voto2...]");
		System.out.println("\tKemeny-----------> [1 , kemeny]\n\t OpcionA1 OpcionA2 OpcionB1 OpcionB2 ,[voto1,voto2...]");
		System.out.println("\thelp------------->ayuda con la forma de los votos");
	}
	
	///////////////////////////////////////// MAYORIA RELATIVA //////////////////////////////////////
	
	private static void gestionarMayoriaRelativa(String[] args,int numOpt)
	{
		List<String>options=new LinkedList<String>();
		List<String>votes=new LinkedList<String>();
		List<String>ganadores=new LinkedList<String>();
		for(int i=2;i<numOpt+2;i++)
		{
			//System.out.println(args[i]);
			options.add(args[i]);
		}
		for(int i=numOpt+2;i<args.length;i++)
		{
			votes.add(args[i]);
		}
		Map<String,Integer>resultados=CalcMayoriaRelativa.CalculateMayoriaRelativa(options, votes,ganadores);
		System.out.println("\n\n");
		System.out.println("//////////////////////////////// RESULTADOS ///////////////");
		for(String option:options)
		{
			System.out.println(option+"->"+resultados.get(option.toLowerCase()));
		}
		
		int votosGanador=resultados.get(ganadores.get(0).toLowerCase());
		if(ganadores.size()>1)
		{
			System.out.print("\n\n Hay un EMPATE entre ");
			for(String ganador:ganadores)
			{
				System.out.print(ganador+" ");
			}
			System.out.print("con "+votosGanador+" votos\n");
		}
		else
		{
			System.out.print("\n\nEl ganador es "+ganadores.get(0)+" con "+votosGanador+" votos\n");
		}
	}
	
	///////////////////////////////////////////////////  KEMENY   ////////////////////////////////
	
	private static int gestionarKemeny(String args[])
	{
		
		if(args.length<5)
		{
			System.out.println("Insuficientes argumentos para kemeny");
			valoresEntrada();
			return 0;
		}
		List<List<String>>opciones=new LinkedList<List<String>>();
		List<List<String>>votos=new LinkedList<List<String>>();
		List<String>opcionesA=new LinkedList<String>();
		List<String>opcionesB=new LinkedList<String>();
		
		opcionesA.add(args[1]);
		opcionesA.add(args[2]);
		opcionesB.add(args[3]);
		opcionesB.add(args[4]);
		opciones.add(opcionesA);
		opciones.add(opcionesB);
		for(int i=5;i<args.length;i++)
		{
			List<String> voto=new LinkedList<String>();
			String [] pares=args[i].split(">");
			if(pares.length==4)
			{
				for(String par:pares)
				{
					voto.add(par);
				}
				votos.add(voto);
			}
		}
		List<String>permutaciones=new LinkedList<String>();
		List<String>permutGanadora=new LinkedList<String>();
		System.out.println("calcular kemeny");
		Map<String,Integer> resultado=CalcKemeny.CalculateKemeny(opciones, votos, permutaciones,permutGanadora);
		System.out.println("PERMUTACIONES");
		for(int i=0;i<permutaciones.size();i++)
		{
			System.out.println(permutaciones.get(i)+" ->"+resultado.get(permutaciones.get(i)));
			
		}
		System.out.println("GANADORES");
		for(String ganador:permutGanadora)
		{
			System.out.println(ganador+" ->"+resultado.get(ganador));
		}
		
		return 0;
		
		
	}
}
