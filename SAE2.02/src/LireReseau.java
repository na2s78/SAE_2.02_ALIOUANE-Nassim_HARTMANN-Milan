import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Classe utilitaire permettant de charger le réseau de transport STAN.
 * Elle lit le fichier de configuration des connexions pour initialiser
 * un graphe orienté et pondéré.

 */
public class LireReseau {

    /**
     * Lit le fichier des connexions et construit le graphe.
     * Les nœuds sont ajoutés automatiquement lors de la création des arcs.
     * * @param fichier_stations   le chemin vers le fichier des sommets (non utilisé ici car géré par ajouterArc)
     * @param fichier_connexions le chemin vers le fichier des arcs (ex: stan.edges.txt)
     * @return un objet GrapheListe initialisé avec les sommets et les arcs du réseau
     */
    public static GrapheListe lire(String fichier_stations, String fichier_connexions) {
        // Initialisation du graphe
        GrapheListe graphe = new GrapheListe();

        // Chargement des arcs (les liaisons entre stations)
        try (BufferedReader br = new BufferedReader(new FileReader(fichier_connexions))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                // On ignore les lignes vides
                if (ligne.length() == 0) {
                    continue;
                }

                // On ignore les commentaires qui commencent par #
                if (ligne.charAt(0) == '#') {
                    continue;
                }

                // Variables pour stocker nos 3 informations textuelles
                String depart = "";
                String arrivee = "";
                String poidsStr = "";

                // Compteur pour savoir dans quel champ on se trouve (0 = depart, 1 = arrivee, 2 = poids)
                int numeroChamp = 0;

                // Parcours de la ligne caractère par caractère sans split ni substring
                for (int i = 0; i < ligne.length(); i++) {
                    char c = ligne.charAt(i);

                    if (c == ';') {
                        numeroChamp++; // On passe au champ suivant quand on croise un ';'
                    } else {
                        // On remplit la bonne variable selon le champ actuel
                        if (numeroChamp == 0) {
                            depart += c;
                        } else if (numeroChamp == 1) {
                            arrivee += c;
                        } else if (numeroChamp == 2) {
                            poidsStr += c;
                        }
                    }
                }

                // Une fois la ligne lue, si on a bien trouvé nos 3 informations
                if (depart.length() > 0 && arrivee.length() > 0 && poidsStr.length() > 0) {
                    // Conversion de la chaîne accumulée en double pour le poids
                    double poids = Double.parseDouble(poidsStr);

                    // Ton ajouterArc va créer les nœuds ET l'arc en même temps !
                    graphe.ajouterArc(depart, arrivee, poids);
                }
            }
        } catch (IOException e) {
            System.err.println("[Erreur] Impossible de lire le fichier des connexions : " + e);
        }

        // On renvoie le graphe complété
        return graphe;
    }
}