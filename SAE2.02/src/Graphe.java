public interface Graphe {

    // récupération de la liste des nœuds du graphe
    List<String> getNoeuds();

    // la récupération, pour chaque nœud, de sa liste d’adjacence
    Arcs getAdjacence(String noeud);

}
