import java.util.ArrayList;

public class Arcs {
    private List<Arc> listeArcs;

    public Arcs(){
        this.listeArcs = new ArrayList<>();
    }

    public void ajouterArc(Arc a){
        this.listeArcs.add(a);
    }

    public List<Arc> getListeArcs(){
        return this.listeArcs;
    }
}
