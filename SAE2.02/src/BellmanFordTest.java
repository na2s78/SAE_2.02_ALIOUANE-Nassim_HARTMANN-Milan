import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BellmanFordTest {

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
        BellmanFord bf = new BellmanFord();
        Valeurs resultat = bf.resoudre(g, "A");

        // On verifie uniquement le cout final vers C
        assertEquals(76.0, resultat.getValeur("C"));
    }

    @Test
    public void testParentDeC() {
        GrapheListe g = creerGrapheSujet();
        BellmanFord bf = new BellmanFord();
        Valeurs resultat = bf.resoudre(g, "A");

        // Le parent direct de C doit etre D
        assertEquals("D", resultat.getParent("C"));
    }

    @Test
    public void testParentDeD() {
        GrapheListe g = creerGrapheSujet();
        BellmanFord bf = new BellmanFord();
        Valeurs resultat = bf.resoudre(g, "A");

        // Le parent de D doit etre E
        assertEquals("E", resultat.getParent("D"));
    }

    @Test
    public void testParentDeE() {
        GrapheListe g = creerGrapheSujet();
        BellmanFord bf = new BellmanFord();
        Valeurs resultat = bf.resoudre(g, "A");

        // Le parent de E doit etre B
        assertEquals("B", resultat.getParent("E"));
    }

    @Test
    public void testParentDeB() {
        GrapheListe g = creerGrapheSujet();
        BellmanFord bf = new BellmanFord();
        Valeurs resultat = bf.resoudre(g, "A");

        // Le parent de B doit etre le depart A
        assertEquals("A", resultat.getParent("B"));
    }

    @Test
    public void testNoeudDepartZero() {
        GrapheListe g = new GrapheListe();
        g.ajouterArc("A", "B", 5.0);

        BellmanFord bf = new BellmanFord();
        Valeurs resultat = bf.resoudre(g, "A");

        // La distance du depart vers lui-meme est de 0.0
        assertEquals(0.0, resultat.getValeur("A"));
        assertEquals(null, resultat.getParent("A"));
    }

    @Test
    public void testNoeudInaccessible() {
        GrapheListe g = new GrapheListe();
        g.ajouterArc("A", "B", 5.0);
        g.ajouterArc("C", "D", 10.0); // Groupe isole

        BellmanFord bf = new BellmanFord();
        Valeurs resultat = bf.resoudre(g, "A");

        // Le noeud isole doit rester a l'infini
        assertEquals(Double.MAX_VALUE, resultat.getValeur("C"));
        assertEquals(null, resultat.getParent("C"));
    }
}
