public class Arc {
    private double poids;
    private String noeudCible;

    public Arc(double poids, String n) {
        this.poids = poids;
        this.noeudCible = n;
    }



    public double getPoids(){
        return this.poids;
    }

    public String getNoeud(){
        return this.noeudCible;
    }

}
