import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DijkstraTest {
    // création du graphe de la figure 1
    private GrapheListe creerGrapheSujet() {
        GrapheListe g = new GrapheListe();
        g.ajouterArc("A", "B", 12.0);
        g.ajouterArc("A", "D", 87.0);
        g.ajouterArc("C", "A", 19.0);
        g.ajouterArc("D", "B", 23.0);
        g.ajouterArc("B", "E", 11.0);
        g.ajouterArc("D", "C", 10.0);
        g.ajouterArc("E", "D", 43.0);
        return g;
    }

    @Test
    public void testCoutTotalDestinationC() {
        GrapheListe g = creerGrapheSujet();
        Dijkstra dj = new Dijkstra();
        Valeurs resultat = dj.resoudre(g, "A");

        // On verifie le cout final calcule par Dijkstra vers C (doit faire 76.0)
        assertEquals(76.0, resultat.getValeur("C"));
    }

    @Test
    public void testParentDeC() {
        GrapheListe g = creerGrapheSujet();
        Dijkstra dj = new Dijkstra();
        Valeurs resultat = dj.resoudre(g, "A");

        // Le parent direct de C doit etre D
        assertEquals("D", resultat.getParent("C"));
    }

    @Test
    public void testParentDeD() {
        GrapheListe g = creerGrapheSujet();
        Dijkstra dj = new Dijkstra();
        Valeurs resultat = dj.resoudre(g, "A");

        // Le parent de D doit etre E
        assertEquals("E", resultat.getParent("D"));
    }

    @Test
    public void testParentDeE() {
        GrapheListe g = creerGrapheSujet();
        Dijkstra dj = new Dijkstra();
        Valeurs resultat = dj.resoudre(g, "A");

        // Le parent de E doit etre B
        assertEquals("B", resultat.getParent("E"));
    }

    @Test
    public void testParentDeB() {
        GrapheListe g = creerGrapheSujet();
        Dijkstra dj = new Dijkstra();
        Valeurs resultat = dj.resoudre(g, "A");

        // Le parent de B doit etre le depart A
        assertEquals("A", resultat.getParent("B"));
    }

    @Test
    public void testNoeudDepartZero() {
        GrapheListe g = new GrapheListe();
        g.ajouterArc("A", "B", 5.0);

        Dijkstra dj = new Dijkstra();
        Valeurs resultat = dj.resoudre(g, "A");

        // La distance de depart initiale doit rester a 0.0 et sans parent
        assertEquals(0.0, resultat.getValeur("A"));
        assertEquals(null, resultat.getParent("A"));
    }

    @Test
    public void testNoeudInaccessible() {
        GrapheListe g = new GrapheListe();
        g.ajouterArc("A", "B", 5.0);
        g.ajouterArc("C", "D", 10.0); // Composante isolee du graphe

        Dijkstra dj = new Dijkstra();
        Valeurs resultat = dj.resoudre(g, "A");

        // Le noeud inaccessible doit rester a l'infini sans provoquer de plantage
        assertEquals(Double.MAX_VALUE, resultat.getValeur("C"));
        assertEquals(null, resultat.getParent("C"));
    }
}
