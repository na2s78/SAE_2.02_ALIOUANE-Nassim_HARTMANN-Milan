public class Principale{
    public static void main(String[] args){
        GrapheListe graphe = new GrapheListe();

        graphe.ajouterArc("A", "B", 12.0);
        graphe.ajouterArc("A", "D", 87.0);
        graphe.ajouterArc("C", "A", 19.0);
        graphe.ajouterArc("D", "B", 23.0);
        graphe.ajouterArc("B", "E", 11.0);
        graphe.ajouterArc("D", "C", 10.0);
        graphe.ajouterArc("E", "D", 43.0);

        System.out.print(graphe);
    }
}