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
}
