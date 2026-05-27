public class Arc {
    private double poids;
    private String noeudCible;

    public Arc(String n, double poids) {
        this.noeudCible = n;
        this.poids = poids;
    }



    public double getPoids(){
        return this.poids;
    }

    public String getNoeud(){
        return this.noeudCible;
    }

}
