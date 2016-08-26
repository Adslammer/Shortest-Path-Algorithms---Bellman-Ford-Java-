/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Klasa u koju spremamo vrijednosti izvorišnog i odredišnog čvora,
 * te tezinu između njih. Vrijednosti izvorišnog i odredišnog čvora,
 * te tezinu između njih prosljeđujemo kroz konstruktor.
 *
 */
class Edge {
    int source;
    int destination;
    int weight;
    
    public Edge() {
    }
    
    /**
     * Konstruktor preko kojeg prosljeđujemo vrijednosti izvorišnog i odredišnog čvora,
     * te težinu između njih.
     * @param s - integer koji predstavlja izvorišni čvor
     * @param d - integer koji predstavlja odredišni čvor
     * @param w - integer koji predstavlja udaljenost između izvorišnog i odredišnog čvora
     */
    public Edge(int s, int d, int w) {
        source = s;
        destination = d;
        weight = w;
    }
}
