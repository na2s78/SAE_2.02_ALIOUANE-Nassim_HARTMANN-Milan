import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GrapheListeTest {
    @Test
    public void testAjoutArcsEtNoeuds() {
        GrapheListe g = new GrapheListe();
        g.ajouterArc("A", "B", 12.0);
        g.ajouterArc("A", "D", 87.0);

        List<String> noeuds = g.getNoeuds();

        // On verifie que les trois noeuds distincts ont bien ete crees
        assertEquals(3, noeuds.size());
        assertEquals("A", noeuds.get(0));
        assertEquals("B", noeuds.get(1));
    }

    @Test
    public void testPoidsNegatifIgnore() {
        GrapheListe g = new GrapheListe();
        g.ajouterArc("A", "B", 12.0);

        // Cet ajout doit etre bloque par la condition poids < 0
        g.ajouterArc("A", "C", -5.0);

        // L'adjacence de A ne doit contenir que l'arc vers B, donc taille = 1
        int tailleAdjacenceA = g.getAdjacence("A").getListeArcs().size();
        assertEquals(1, tailleAdjacenceA);
    }

    @Test
    public void testAdjacenceNoeudInexistant() {
        GrapheListe g = new GrapheListe();
        g.ajouterArc("A", "B", 12.0);

        // Un noeud pas dans le graphe doit renvoyer null
        assertEquals(null, g.getAdjacence("Z"));
    }

    @Test
    public void testPasDeDoublonNoeuds() {
        GrapheListe g = new GrapheListe();
        g.ajouterArc("A", "B", 10.0);
        // On rajoute un arc avec des noeuds deja existants
        g.ajouterArc("A", "B", 15.0);

        List<String> noeuds = g.getNoeuds();
        // La taille de la liste globale ne doit pas avoir double
        assertEquals(2, noeuds.size());
    }

    @Test
    public void testPoidsZeroAccepte() {
        GrapheListe g = new GrapheListe();
        // Un poids de 0.0 est valide et ne doit pas etre bloque
        g.ajouterArc("A", "B", 0.0);

        int tailleAdjacenceA = g.getAdjacence("A").getListeArcs().size();
        assertEquals(1, tailleAdjacenceA);

        double poidsArc = g.getAdjacence("A").getListeArcs().get(0).getPoids();
        assertEquals(0.0, poidsArc);
    }

    @Test
    public void testPlusieursArcsMemeSource() {
        GrapheListe g = new GrapheListe();
        g.ajouterArc("A", "B", 10.0);
        g.ajouterArc("A", "C", 20.0);

        List<Arc> arcsDeA = g.getAdjacence("A").getListeArcs();
        // On verifie qu'il y a bien 2 arcs partants de A et qu'ils sont dans le bon ordre
        assertEquals(2, arcsDeA.size());
        assertEquals("B", arcsDeA.get(0).getNoeud());
        assertEquals("C", arcsDeA.get(1).getNoeud());
    }
}
