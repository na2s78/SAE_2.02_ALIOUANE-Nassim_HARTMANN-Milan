SAE 2.02 ALIOUANE Nassim & HARTMANN Milan
Groupe A
Milan a eu un problème de PC donc tous les push sont sur ceux de Nassim


==== Présentation de la structure du dossier ====

## Structure du Projet

* `MainTransport.jar` : Exécutable Java (racine) utilisé par la GUI.
* `main-gui.py` : Interface graphique Python (affichage de la carte).
* `stan.nodes.txt` / `stan.edges.txt` : Fichiers de données du réseau STAN.

### Code source (SAE2.02/src/)
* Graphe : Graphe.java, GrapheListe.java, Arc.java, Arcs.java (Structure en listes d'adjacence).
* Parsing : LireReseau.java (Lecture des fichiers texte).
* Algorithmes : Dijkstra.java, BellmanFord.java, Valeurs.java (Calculs et reconstruction des chemins).
* Main : MainTransport.java (Point d'entrée de l'application).
* Tests : DijkstraTest.java, BellmanFordTest.java, GrapheListeTest.java (Fichiers de tests JUnit 5).


==== Commande pour générer le fichier JAR (à écrire dans le terminal à la racine du projet) ====
mkdir -p bin
javac -d bin SAE2.02/src/Graphe.java SAE2.02/src/Arc.java SAE2.02/src/Arcs.java SAE2.02/src/BellmanFord.java SAE2.02/src/Dijkstra.java SAE2.02/src/GrapheListe.java SAE2.02/src/LireReseau.java SAE2.02/src/MainTransport.java SAE2.02/src/Valeurs.java
jar cvfe MainTransport.jar MainTransport -C bin .
rm -rf bin

==== Commande pour lancer les tests unitaires (à écrire dans le terminal à la racine du projet) ====
mkdir -p lib && curl -L -o lib/junit-platform-console-standalone.jar [https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.10.2/junit-platform-console-standalone-1.10.2.jar](https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.10.2/junit-platform-console-standalone-1.10.2.jar)
mkdir -p bin
javac -d bin -cp "lib/junit-platform-console-standalone.jar" SAE2.02/src/*.java
java -jar lib/junit-platform-console-standalone.jar --class-path bin --scan-class-path
rm -rf bin
