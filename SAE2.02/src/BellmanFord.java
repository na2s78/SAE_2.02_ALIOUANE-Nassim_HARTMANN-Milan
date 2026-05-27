import java.util.List;

public class BellmanFord {
    public Valeurs resoudre(Graphe g, String depart){
        Valeurs resultats = new Valeurs(); // stockage du resultat
        List<String> allNoeuds = g.getNoeuds();

        for(int i = 0; i < allNoeuds.size(); i++){
            String noeud = allNoeuds.get(i);

            if(noeud.equals(depart)){
                resultats.setValeur(noeud, 0.0); // on met une distance de 0 entre le noeud de départ et lui meme
            }else {
                resultats.setValeur(noeud, Double.MAX_VALUE); // On met les autres noeuds à l'infini
            }
            resultats.setParent(noeud, null); // Aucun noeud n'a de parent au debut
        }


    }
}
