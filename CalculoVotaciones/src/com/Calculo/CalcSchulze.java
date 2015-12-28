package com.Calculo;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CalcSchulze {

	public static Map<String, Integer> CalculateSchulze(List<String> options, List<List<String>> votes)
	{
		Map<String,Integer> resultados = new LinkedHashMap<String, Integer>();
		List<Distance> distances = new LinkedList<Distance>();

		votes = translateVotesToOptions(options,votes);

		for (int posFirst = 0; posFirst < options.size(); posFirst++) {
			for (int posSecond = 0; posSecond < options.size(); posSecond++)
			{
				if (posFirst != posSecond) {
					distances.add(calcPairWisePath(options.get(posFirst), options.get(posSecond), votes));
				}
			}
		}

		List<Path> paths = calcPathStrength(distances, options);
		List<Path> bestPaths = new LinkedList<Path>();

		for (int i = 0; i < options.size(); i++) {
			for (int j = i; j < options.size(); j++) {
				if (i != j) {
					Path bPath = gestBestPathOfPair(options.get(i), options.get(j), paths);
					if (bPath != null) {
						bestPaths.add(bPath);
					}
				}
			}
		}

		for (String option:options) {
			resultados.put(option, 0);
		}

		for (Path path : bestPaths) {
			resultados.put(path.getOrigin(), resultados.get(path.getOrigin()) + path.getStrength());
		}

		return resultados;
	}

	private static List<Path> calcPathStrength(List<Distance> distances, List<String> options)
	{
		List<Path> paths = new LinkedList<Path>();

		for (int i = 0; i < options.size(); i++)
		{
			for (int j = 0; j < options.size(); j++)
			{
				if (i != j)
				{
					for (int k = 0; k < options.size(); k++)
					{
						if (i != k && j != k) {
							Integer distanceJK = getDistanceFromOriginDestination(options.get(j), options.get(k), distances);
							Integer distanceJI = getDistanceFromOriginDestination(options.get(j), options.get(i), distances);
							Integer distanceIK = getDistanceFromOriginDestination(options.get(i), options.get(k), distances);

							if (distanceJK != null && distanceJI != null && distanceIK != null) {
								Integer strength = Math.max(distanceJK, Math.min(distanceJI, distanceIK));
								Path newPath = new Path(options.get(j), options.get(k), strength);
								paths.add(newPath);
							}
						}
					}
				}
			}
		}

		return paths;
	}

	private static Integer getDistanceFromOriginDestination(String origin, String destination, List<Distance> distances) {
		for (Distance dist : distances)
		{
			if (dist.getOrigin().equals(origin) && dist.getDestination().equals(destination)) {
				return dist.getDistance();
			}
		}

		return null;
	}

	private static Path gestBestPathOfPair(String origin, String destination, List<Path> paths) {
		Path first = null;
		Path second = null;
		for (Path path : paths)
		{
			if (path.getOrigin().equals(origin) && path.getDestination().equals(destination)) {
				first = path;
			} else if (path.getDestination().equals(origin) && path.getOrigin().equals(destination)){
				second = path;
			}
		}

		if (first != null && second != null) {
			if (first.getStrength() > second.getStrength()) {
				return first;
			} else {
				return second;
			}
		} else if (first == null && second != null) {
			return second;
		} else if (second == null && first != null) {
			return first;
		}

		return null;
	}

	private static Distance calcPairWisePath(String first, String second, List<List<String>> votes) {
		Integer firstOverSecond = 0;
		Distance resultDistance;

		for (List<String> vote:votes) {
			if (vote.indexOf(first) < vote.indexOf(second)) {
				firstOverSecond++;
			}
		}
		resultDistance = new Distance(first, second, firstOverSecond);

		return resultDistance;
	}

	private static List<List<String>> translateVotesToOptions(List<String> options, List<List<String>> votes)
	{
		List<List<String>> newVotes = new LinkedList<List<String>>();

		for (List<String> vote:votes) {
			List<String> newVote = new LinkedList<String>();

			for (String option:vote) {
				String newOption = options.get(Integer.parseInt(option) -1 );
				newVote.add(newOption);
			}

			newVotes.add(newVote);
		}

		return newVotes;
	}

}

abstract class PathDistanceBase {
	protected String origin;
	protected String destination;

	public PathDistanceBase(String origin, String destination) {
		this.origin = origin;
		this.destination = destination;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
}

final class Distance extends PathDistanceBase {
	private Integer distance;

	public Distance(String origin, String destination, Integer distance) {
		super(origin, destination);
		this.distance = distance;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}
}

final class Path extends PathDistanceBase {
	private Integer strength;

	public Path(String origin, String destination, Integer strength) {
		super(origin, destination);
		this.strength = strength;
	}

	public Integer getStrength() {
		return strength;
	}

	public void setStrength(Integer strength) {
		this.strength = strength;
	}
}




