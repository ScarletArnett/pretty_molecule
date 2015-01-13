import java.util.ArrayList;

public class Molecule {
    private String nom;
    private String code;
    private ArrayList<Atome> atomes;
    private ArrayList<int[]> liaisons;

    public Molecule(String nom, String code) {
        this.nom      = nom;
        this.code     = code;
        this.atomes   = new ArrayList<Atome>();
        this.liaisons = new ArrayList<int[]>();
    }

    public void ajouterAtome(Atome a) {
        atomes.add(a);
    }

    public void ajouterLiaison(int debut, int fin) {
        liaisons.add(new int[]{ debut, fin });
    }

    public ArrayList<Atome> getAtomes() {
        return atomes;
    }

    public Atome getAtome(int i) {
        return atomes.get(i);
    }

    public ArrayList<int[]> getLiaisons() {
        return liaisons;
    }

    public String getNom() {
        return nom;
    }

    public String toString() {
        String res = "";

        res += nom + "\n";
        res += code + "\n\n";

        res += "Atomes\n";
        for (Atome a : atomes) {
            res += "  " + a + "\n";
        }
        res += "\n";

        res += "liaisons\n";
        for (int[] liaison : liaisons) {
            Atome debut = atomes.get(liaison[0]);
            Atome fin   = atomes.get(liaison[1]);

            res += "   " + debut.getCode() + "----" + fin.getCode() + "\n";
        }

        return res;
    }
}
