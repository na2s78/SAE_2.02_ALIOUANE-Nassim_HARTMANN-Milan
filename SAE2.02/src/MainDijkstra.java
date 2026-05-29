import java.util.List;

public class MainDijkstra {
    public static void main(String[] args){
        //creation du graphe
        GrapheListe g = new GrapheListe();

        g.ajouterArc("A", "B", 12.0);
        g.ajouterArc("A", "D", 87.0);
        g.ajouterArc("C", "A", 19.0);
        g.ajouterArc("D", "B", 23.0);
        g.ajouterArc("B", "E", 11.0);
        g.ajouterArc("D", "C", 10.0);
        g.ajouterArc("E", "D", 43.0);


        //calcul du chemin le plus cours à partir de A
        Dijkstra dk = new Dijkstra();
        String depart = "A";
        String arrivee = "C";

        System.out.println("Depart à : " + depart);
        Valeurs resultat = dk.resoudre(g, depart);


        // Affichage du chemin
        List<String> chemin = resultat.calculerChemin(arrivee);

        System.out.println("\n resultat pour la destination " + arrivee + " :");
        System.out.println(resultat.getValeur(arrivee));
        System.out.print("Chemin : ");
        for(int i = 0; i < chemin.size(); i++){
            System.out.print(chemin.get(i));
            if(i < chemin.size() - 1){
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }
}
