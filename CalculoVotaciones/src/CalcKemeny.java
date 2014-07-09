import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class CalcKemeny {

	/*
	 * Las listas permutacion y permutacion ganadoras deberan esar inicializadas
	 */
	public static Map<String,Integer> CalculateKemeny(List<List<String>>opciones,List<List<String>>votos,List<String> permutaciones,List<String> permutGanadora)
	{
		if(opciones==null|| votos==null||permutaciones==null)
		{
			return null;
		}
		else
		{
			permutaciones.clear();
			permutGanadora.clear();
		}
		
		
		List<String> opcionesKemeny=CalcularOpcionesKemeny(1,opciones);
		
		Map<String,Integer> mapa=iniciarMapa(opcionesKemeny);
		
		calcularPermutaciones(permutaciones,opcionesKemeny,"",opcionesKemeny.size(),opcionesKemeny.size());
		
		//POSIBLE CAMBIO PASANDOLE LAS OPCIONEKEMENY
		votos=validateVotes(opciones,votos);
		
		int [][] matriz=calcularMatriz(opcionesKemeny,votos,mapa);
		Map<String,Integer> resultado=CalcularValorPermutacion(permutaciones,matriz,mapa);
		
		calcularPermutacionGanadora(permutaciones,resultado,permutGanadora);
	
		return resultado;
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
	/*
	 * x debe ser siempre igual a 1(x=1) en la primera llamada
	 */
	private static List<String> CalcularOpcionesKemeny(int x,List<List<String>> opciones)
	{
		
		List<String> lista=null;
		List<String> temp=new LinkedList<String>();
 		
		if(x!=opciones.size())
		{
			lista=CalcularOpcionesKemeny(x+1,opciones);
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
	
	
	private static Map<String,Integer>iniciarMapa(List<String> opcionesKemeny)
	{
		Map<String,Integer> mapa=new HashMap<String,Integer>();
		for(int i=0;i<opcionesKemeny.size();i++)
		{
			mapa.put(opcionesKemeny.get(i), i);
		}
		return mapa;
	}
	private static int [][] calcularMatriz(List<String> opcionesKemeny,List<List<String>> votos,Map<String,Integer> mapa)
	{
		int [][] matriz=new int[opcionesKemeny.size()][opcionesKemeny.size()];
		for(int i=0;i<opcionesKemeny.size();i++)
		{
			for(int x=0;x<opcionesKemeny.size();x++)
			{
				matriz[i][x]=0;
			}
		}
		
		for(int i=0;i<opcionesKemeny.size();i++)
		{
			for(int x=i+1;x<opcionesKemeny.size();x++)
			{
				for(int z=0;z<votos.size();z++)
				{
					for(int t=0;t<votos.get(z).size();t++)
					{
						if(votos.get(z).get(t).equals(opcionesKemeny.get(i)))
						{
							int m=mapa.get(opcionesKemeny.get(i));
							int n=mapa.get(opcionesKemeny.get(x));
							matriz[n][m]++;
							t=votos.get(z).size();
						}
						else if(votos.get(z).get(t).equals(opcionesKemeny.get(x)))
						{
							int m=mapa.get(opcionesKemeny.get(i));
							int n=mapa.get(opcionesKemeny.get(x));
							matriz[m][n]++;
							t=votos.get(z).size();
							
						}
					}
				}
			}
		}
		return matriz;
	}
	
	/*
	 * Al llamar a la funcion:
	 * 		act---> debe ser una cadena vacia ""
	 * 		n y r-> deben ser el valor opcionesKemeny.size() en ambas
	 */
	private static void calcularPermutaciones(List<String>permutaciones,List<String> opcionesKemeny, String act, int n, int r)
	{
		 if (n == 0)
		 {
	            permutaciones.add(act);
	            
	     } 
		 else 
	     {
	         for (int i = 0; i < r; i++) {
	                if (!act.contains(opcionesKemeny.get(i))) // Controla que no haya repeticiones
	                { 
	                	if(n==1)
	                	{
	                		calcularPermutaciones(permutaciones,opcionesKemeny, act + opcionesKemeny.get(i) + "", n - 1, r);
	                	}
	                	else
	                	{
	                		calcularPermutaciones(permutaciones,opcionesKemeny, act + opcionesKemeny.get(i) + ">", n - 1, r);
	                	}
	                	
	                }
	            }
	        }
	   }
	
	private static Map<String,Integer> CalcularValorPermutacion(List<String> permutaciones,int[][] matriz,Map<String,Integer>mapa)
	{
		List<Integer>valores=new LinkedList<Integer>();
		Map<String,Integer> resultado=new HashMap<String,Integer>();
		for(int i=0;i<permutaciones.size();i++)
		{
			String[] temp=permutaciones.get(i).split(">");
			int val=0;
			for(int z=0;z<temp.length;z++)
			{
				for(int t=z+1;t<temp.length;t++)
				{
					int m=mapa.get(temp[z]);
					int n=mapa.get(temp[t]);
					val=val+matriz[n][m];
					
				}
			}
			valores.add(val);
		}
		
		for(int i=0;i<permutaciones.size();i++)
		{
			resultado.put(permutaciones.get(i),valores.get(i));
		}
		return resultado;
		
	}
	private static void calcularPermutacionGanadora(List<String>permutaciones,Map<String,Integer> resultado,List<String> permutacionGanadora)
	{
		int max=0;
		for(String permut:permutaciones)
		{
			if(resultado.get(permut)>max)
			{
				permutacionGanadora.clear();
				max=resultado.get(permut);
				permutacionGanadora.add(permut);
			}
			else if(resultado.get(permut)==max)
			{
				permutacionGanadora.add(permut);
			}
		}
	}
	
}
