import java.util.List;
public class BellmanFord {
    /**
     * Fonction qui nous permet d'utiliser la fonction de BellmanFord
     * @param g graphe sur le quel on travaille
     * @param depart point de depart
     * @return le resultat
     */
    public Valeurs resoudre(Graphe g, String depart){
        Valeurs resultats = new Valeurs(); // stockage du resultat
        List<String> allNoeuds = g.getNoeuds();

        for(int i = 0; i < allNoeuds.size(); i++){
            String noeud = allNoeuds.get(i);

            if(noeud.equals(depart)){
                resultats.setValeur(noeud, 0.0); // depart a 0
            }else {
                resultats.setValeur(noeud, Double.MAX_VALUE); // On met les autres noeuds à l'infini
            }
            resultats.setParent(noeud, null); // Aucun noeud n'a de parent au debut
        }

        boolean changement = true; // verification si L(N) a été modifié

        while(changement){
            changement = false; // On part du principe qu'il n'y aura pas de modification

            // On parcourt chaque nœud X du graphe
            for (int i = 0; i < allNoeuds.size(); i++) {
                String x = allNoeuds.get(i);
                double valeurX = resultats.getValeur(x);

                // Si la valeur de X est infinie, on ne peut pas encore calculer de chemin depuis ce nœud
                if (valeurX == Double.MAX_VALUE) {
                    continue;
                }

                // On récupère les nœuds adjacents de X
                Arcs voisins = g.getAdjacence(x);

                if (voisins != null) {
                    List<Arc> listeArc = voisins.getListeArcs();

                    // Pour chaque nœud adjacent N
                    for (int j = 0; j < listeArc.size(); j++) {
                        Arc arc = listeArc.get(j);
                        String n = arc.getNoeud();     // Le nœud adjacent N
                        double poids = arc.getPoids(); // Le poids de l'arc entre X et N

                        // Somme de L(X) et du poids de l'arc
                        double v = valeurX + poids;

                        // Si la nouvelle valeur est plus petite
                        if (v < resultats.getValeur(n)) {
                            resultats.setValeur(n, v); // On modifie L(N)
                            resultats.setParent(n, x);            // On met à jour le parent de N (qui devient X)
                            changement = true;                    // Une valeur a été modifiée, on continue la boucle
                        }
                    }
                }
            }
        }
        return resultats; // On renvoi les resultats
    }
}

