import java.util.List;

/**
 * Classe principale de l'application de transport.
 * Elle permet de charger le réseau STAN et d'exécuter les algorithmes
 * de Dijkstra et Bellman-Ford tout en mesurant leurs performances.
 */
public class MainTransport {

    public static void main(String[] args) {
        // Vérification des arguments requis
        if (args.length < 2) {
            System.out.println("[Erreur] Tu dois fournir deux identifiants de stations en paramètres.");
            return;
        }

        String depart = args[0];
        String arrivee = args[1];

        String fichierStations = "stan.nodes.txt";
        String fichierConnexions = "stan.edges.txt";

        // Chargement du graphe
        GrapheListe reseauStan = LireReseau.lire(fichierStations, fichierConnexions);

        // ==========================================
        //  MESURE DE DIJKSTRA
        // ==========================================
        Dijkstra dijkstra = new Dijkstra();

        long debutDijkstra = System.nanoTime(); // Chrono Start
        Valeurs valeursDijkstra = dijkstra.resoudre(reseauStan, depart);
        long finDijkstra = System.nanoTime();   // Chrono Stop

        // Calcul de la durée en millisecondes (ms)
        double tempsDijkstra = (finDijkstra - debutDijkstra) / 1000000.0;

        List<String> listeDijkstra = valeursDijkstra.calculerChemin(arrivee);
        String cheminStr = "";
        if (listeDijkstra.size() >= 2 && listeDijkstra.get(0).equals(depart)) {
            for (int i = 0; i < listeDijkstra.size(); i++) {
                cheminStr += listeDijkstra.get(i);
                if (i < listeDijkstra.size() - 1) {
                    cheminStr += ";";
                }
            }
        } else {
            cheminStr = "Aucun chemin trouvé";
        }

        // ==========================================
        //  MESURE DE BELLMAN-FORD
        // ==========================================
        BellmanFord bellman = new BellmanFord();

        long debutBellman = System.nanoTime();  // Chrono Start
        Valeurs valeursBellman = bellman.resoudre(reseauStan, depart);
        long finBellman = System.nanoTime();    // Chrono Stop

        // Calcul de la durée en millisecondes (ms)
        double tempsBellman = (finBellman - debutBellman) / 1000000.0;


        // ==========================================
        //  AFFICHAGE DU TABLEAU
        // ==========================================
        System.out.println("\n========================================================================");


        String format = "| %-10s | %-10s | %-30s | %-20s | %-15s |\n";

        System.out.printf(format, "Départ", "Arrivée", "Chemin", "Temps Bellman-Ford", "Temps Dijkstra");
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.printf(format, depart, arrivee, cheminStr, tempsBellman + " ms", tempsDijkstra + " ms");

        System.out.println("========================================================================\n");

        System.out.println(cheminStr);
    }
}