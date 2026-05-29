import java.util.ArrayList;
import java.util.List;

public class Dijkstra {

    /**
     * Calcule les plus courts chemins dans un graphe avec l'algorithme de Dijkstra
     * @param g Le graphe
     * @param depart Le nom du nœud de départ
     * @return Un objet Valeurs qui contient les distances et les parents calculés
     */
    public Valeurs resoudre(Graphe g, String depart) {
        Valeurs resultats = new Valeurs();
        List<String> allNoeuds = g.getNoeuds();

        // Q <- {} // utilisation d'une liste de noeuds à traiter
        List<String> q = new ArrayList<>();

        // Pour chaque sommet v de G faire
        for (int i = 0; i < allNoeuds.size(); i++) {
            String v = allNoeuds.get(i);

            // v.valeur <- Infini
            resultats.setValeur(v, Double.MAX_VALUE);

            // v.parent <- Indéfini
            resultats.setParent(v, null);

            // Q <- Q U {v} // ajouter le sommet v à la liste Q
            q.add(v);
        }

        // A.valeur <- 0
        resultats.setValeur(depart, 0.0);

        // Tant que Q est un ensemble non vide faire
        while (q.size() > 0) {

            // u <- un sommet de Q telle que u.valeur est minimal
            String u = q.get(0);
            double minValeur = resultats.getValeur(u);

            for (int i = 1; i < q.size(); i++) {
                String actuel = q.get(i);
                if (resultats.getValeur(actuel) < minValeur) {
                    minValeur = resultats.getValeur(actuel);
                    u = actuel;
                }
            }

            // // enlever le sommet u de la liste Q
            // Q <- Q \ {u}
            q.remove(u);

            if (minValeur == Double.MAX_VALUE) {
                break;
            }

            // Pour chaque sommet v de Q tel que l'arc (u,v) existe faire
            Arcs voisins = g.getAdjacence(u);
            if (voisins != null) {
                List<Arc> listeArc = voisins.getListeArcs();

                for (int j = 0; j < listeArc.size(); j++) {
                    Arc arc = listeArc.get(j);
                    String v = arc.getNoeud(); // Le nœud cible v de l'arc (u,v)

                    // On vérifie que v est bien encore un "sommet de Q"
                    if (q.contains(v)) {
                        double poidsUV = arc.getPoids(); // poids(u,v)

                        // d <- u.valeur + poids(u,v)
                        double d = minValeur + poidsUV;

                        // Si d < v.valeur
                        if (d < resultats.getValeur(v)) {
                            // Alors v.valeur <- d
                            resultats.setValeur(v, d);
                            // v.parent <- u
                            resultats.setParent(v, u);
                        }
                    }
                }
            }
        }

        // Fin Tant que
        return resultats;
    }
}