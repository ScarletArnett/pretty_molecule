public class Atome {
    private int numero;
    private String nom;
    private String code;
    private int couleur;
    private int x, y;

    public Atome(int numero, String nom, String code, int couleur) {
        this.numero  = numero;
        this.nom     = nom;
        this.couleur = couleur;
        this.code    = code;
    }

    public Atome(Atome autre) {
        this(autre.numero, autre.nom, autre.code, autre.couleur);
    }

    public int getNumero()  { return numero; }
    public String getCode() { return code; }
    public int getCouleur() { return couleur; }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String toString() {
        return String.format("%-15s %s (%2d)", nom, code, numero);
    }
}
