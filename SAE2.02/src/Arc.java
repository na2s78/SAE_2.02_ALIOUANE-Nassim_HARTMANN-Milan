public class Arc {
    private double poids;
    private Noeud noeud;

    public Arc(double poids, Noeud n) {
        this.poids = poids;
        this.noeud = n;
    }



    public double getPoids(){
        return this.poids;
    }

    public Noeud getNoeud(){
        return this.noeud;
    }

    
}
