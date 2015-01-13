import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class PrettyMolecule extends Frame {

    public static final Color BLACK = new Color(0, 0, 0);

    public static void main(String[] args) {
        ArrayList<Atome> mendeleiev = MoleculeTest.mendeleiev("./mendeleiev");
        Molecule molecule = MoleculeTest.chargerMolecule("./data/dioxyde_de_carbone.mol", mendeleiev);
        new PrettyMolecule(molecule);
    }

    private Molecule molecule;

    public PrettyMolecule(Molecule molecule) {
        this.molecule = molecule;


        setBackground(Color.GRAY);
        setTitle(molecule.getNom());
        setSize(500, 500);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }


    public void paint(Graphics g) {
        Graphics2D gg = (Graphics2D) g;
        gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        drawMolecule(gg, molecule);
    }

    public static void drawMolecule(Graphics2D gg, Molecule m) {
        ArrayList<Ellipse2D> ellipses = new ArrayList<Ellipse2D>();

        for (Atome a : m.getAtomes()) {
            ellipses.add(drawAtome(gg, a));
        }

        for (int[] l : m.getLiaisons()) {
            Ellipse2D first = ellipses.get(l[0]);
            Ellipse2D second = ellipses.get(l[1]);

            gg.drawLine((int) first.getCenterX(), (int) first.getCenterY(), (int) second.getCenterX(), (int) second.getCenterY());
        }
    }

    public static Ellipse2D drawAtome(Graphics2D gg, Atome a, int x, int y) {
        Color old = gg.getColor();

        gg.setColor(new Color(a.getCouleur()));

        Ellipse2D ell = new Ellipse2D.Double(x, y, 20.0 + a.getNumero(), 20.0 + a.getNumero());
        gg.draw(ell);
        gg.fill(ell);

        gg.setColor(old);

        return ell;
    }

    public static Ellipse2D drawAtome(Graphics2D gg, Atome a) {
        return drawAtome(gg, a, a.getX(), a.getY());
    }
}
