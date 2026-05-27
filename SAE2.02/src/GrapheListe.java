import java.util.ArrayList;
import java.util.List;


public class GrapheListe implements Graphe {
    private List<String> noeuds;
    private List<Arcs> listesAdjacence;

    public GrapheListe(){
        this.noeuds = new ArrayList<>();
        this.listesAdjacence = new ArrayList<>();
    }


    public List<String> getNoeuds(){
        return this.noeuds;
    }

    public Arcs getAdjacence(String noeud){
        int indiceNoeud = this.noeuds.indexOf(noeud);

        if (indiceNoeud < 0){
            return null;
        }
        return this.listesAdjacence.get(indiceNoeud);
    }


    /**
     * Methode qui permet d'ajouter un arc au graphe
     * @param noeudSrc String qui correspond a l'identifiant du noeud
     * @param noeudDest String qui correspond a l'identifiant du noeud
     * @param poids Double qui corredpond au poids de l'arc
     */
    public void ajouterArc(String noeudSrc, String noeudDest, double poids) {
        // Verification du poids
        if (poids < 0) {
            return;
        }

        // Ajout du noeud source si il n'existe pas
        if (this.noeuds.indexOf(noeudSrc) < 0) {
            this.noeuds.add(noeudSrc);
            this.listesAdjacence.add(new Arcs());
        }

        // Ajout du noeud source si il n'existe pas
        if (this.noeuds.indexOf(noeudDest) < 0) {
            this.noeuds.add(noeudDest);
            this.listesAdjacence.add(new Arcs());
        }

        // 3. Maintenant qu'on est SUR que les noeuds existent, on recupere le bon indice de la source
        int indiceSrc = this.noeuds.indexOf(noeudSrc);


        // 4. On cree l'arc et on l'ajoute a la liste d'adjacence du noeud source
        Arc a = new Arc(noeudDest, poids);
        this.listesAdjacence.get(indiceSrc).ajouterArc(a);

    }
}


