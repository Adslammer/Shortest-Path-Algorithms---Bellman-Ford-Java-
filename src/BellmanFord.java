import java.awt.TextArea;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Klasa BellmanFord sadrži cijelu logiku algoritma i ispisuje sve korake.
 *
 */

public class BellmanFord {
	private int INF = Integer.MAX_VALUE;
	
	/**
	 * Konstruktor klase BellmanFord.
	 * @param edges - polje objekata klase Edge
	 * @param source - integer koji predstavlja izvorišni čvor
	 * @param nnode - integer koji predstavlja ukupan broj čvorova u učitanoj topologiji
	 * @param result - textArea u koji ispisujemo rezultate izvođenja algoritma
	 */

	BellmanFord(ArrayList<Edge> edges, int source, int nnode, TextArea result) {
		int[] distance = new int[nnode]; // velicina polja odgovara broju
											// cvorova!
		Arrays.fill(distance, INF);
		int[] parent = new int[nnode];
		Arrays.fill(parent, source);
		distance[source] = 0;
		boolean negative;
		boolean ok = true;

		// Metoda u kojoj je cijeli algoritam algoritam
		iteration(nnode, source, distance, parent, edges, result);

		// Metoda za provjeru postojanja negativne grane nakon V-1 iteracija
		negative = checkNegativeEdge(distance);

		// Ako postoji negativna grana ide u metodu negativeCycele koja ispisuje
		// da postoji negativna petlja
		if (negative) {
			ok = negativCycle(distance, edges, result);
		}

		// Ako ne psotoji negativna petlja ispisi konacan rezultat
		if (ok) {
			printResult(source, distance, parent, result);
		}
	}

	/*
	 * Ovdje je cijela logika algoritma
	 */
	@SuppressWarnings("deprecation")
	
	/**
	 * Metoda iteration u kojoj prolazimo kroz sve čvorove i bridove u V-1 iteracija.
	 * @param nnode - integer koji predstavlja ukupan broj čvorova u učitanoj topologiji
	 * @param source - integer koji predstavlja izvorišni čvor
	 * @param distance - polje integera u koje spremamo udaljenosti do svih čvorova u topologiji
	 * @param parent - polje integera u koje spremamo prethodni čvor 
	 * @param edges - polje objekata klase Edge
	 * @param result - textArea u koji ispisujemo rezultate izvođenja algoritma
	 */
	private void iteration(int nnode, int source, int[] distance, int[] parent,
			ArrayList<Edge> edges, TextArea result) {
		for (int i = 1; i <= nnode - 1; i++) { // i iterira od 1 do V-1
			result.appendText("\n#####Korak " + i + ". iteracije#####\n");
			for (int j = 0; j < edges.size(); j++) {
				if (distance[edges.get(j).source] == INF) {
					continue;
				}

				int newDistance = distance[edges.get(j).source]
						+ edges.get(j).weight;

				if (newDistance < distance[edges.get(j).destination]) {
					distance[edges.get(j).destination] = newDistance;
					parent[edges.get(j).destination] = edges.get(j).source;
				}

				// System.out.println("Udaljenost" + edges.get(j).source + "->"
				// + edges.get(j).destination + ": " + edges.get(j).weight);
				// System.out.println(Arrays.toString(distance));
				// System.out.println(j + ", " +
				// parent[edges.get(j).destination] + ", " + source + "," +
				// distance[edges.get(j).destination]);
				path(edges.get(j).destination, parent, source, distance, result);
			}
			System.out.println("\n");
		}
	}

	/*
	 * Provjera da li postoji negativna grana, ako postoji postavi varijablu
	 * negative na true.
	 */
	
	/**
	 * Metoda checkNegativeEdge provjerava da li postoji negativna udaljenost
	 * između čvorova nakon V-1 iteracija.
	 * @param distance - polje integera u koje spremamo udaljenosti do svih čvorova u topologiji 
	 * @return negative - 
	 */
	private boolean checkNegativeEdge(int[] distance) {
		boolean negative = false;

		for (int i = 0; i < distance.length; i++) {
			if (distance[i] < 0) {
				negative = true;
			}
		}

		return negative;
	}

	/*
	 * Ako postoji negativna grana onda treba napraviti jos jedan prolaz kroz
	 * sve prijelaze da se provjere udajenosti.
	 */
	@SuppressWarnings("deprecation")
	private boolean negativCycle(int[] distance, ArrayList<Edge> edges,
			TextArea result) {
		boolean ok = true;

		for (int j = 0; j < edges.size(); j++) {
			if (distance[edges.get(j).source] != INF
					&& distance[edges.get(j).destination] > distance[edges
							.get(j).source] + edges.get(j).weight) {
				result.appendText("\nOtkrivena petlja negativne težine!\n"); // Negative
																				// edge
																				// weight
																				// cycles
																				// detected
				ok = false;
				break;
			}
		}

		return ok;
	}

	/*
	 * Ispisuje konacan rezultat izvođenja algoritma
	 */
	@SuppressWarnings("deprecation")
	private void printResult(int source, int[] distance, int[] parent,
			TextArea result) {
		result.appendText("\n##### REZULTAT IZVODENJA ALGORITMA #####\n");

		for (int i = 0; i < distance.length; i++) {
			path(i, parent, source, distance, result);
			// System.out.println(parent[i]);
		}
	}

	/*
	 * RaÄŤuna put od izvorisnog do odredisnog ÄŤvora Zapisuje sve cvorove kojim
	 * smo prosli
	 */
	private void path(int arg, int[] parent, int source, int[] distance,
			TextArea result) {
		int destination = arg;
		ArrayList<Integer> rPath = new ArrayList<Integer>();
		rPath.add(destination);

		if (destination != source) {
			int par = parent[destination];
			// System.out.println("Par: " + par);
			rPath.add(par);
			// System.out.println(rPath);
			// System.out.println(parent[par]);
			while (par != source) {
				par = parent[par];
				rPath.add(par);
			}
		}
		printPath(source, destination, distance, rPath, result);
	}

	/*
	 * Ispisuje sve cvorove kojima smo prosli od izvorista do odrediste. Na
	 * kraju ispisuje i udaljenost koja je predena.
	 */
	@SuppressWarnings("deprecation")
	private void printPath(int source, int destination, int[] distance,
			ArrayList<Integer> rPath, TextArea result) {
		result.appendText("Put od izvorišta " + source + " do čvora "
				+ destination + ": \n");
		for (int i = rPath.size() - 1; i >= 0; i--) {
			result.appendText(rPath.get(i) + "");
			if (i != 0) {
				result.appendText(" -> ");
			}
			if (i == 0) {
				if (distance[destination] == INF) {
					result.appendText(": \u221E\t \n");
				} else {
					result.appendText(": " + distance[destination] + "\n");
				}
			}
		}
	}
}
