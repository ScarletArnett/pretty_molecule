import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class MoleculeTest {

    public static final String BASE_DATA = "./data";

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        for (String[] mm : lister(new File(BASE_DATA))) {
            System.out.printf("%s (%s)\n", mm[0], mm[1]);
        }

        ArrayList<Atome> mendeleiev = mendeleiev("./mendeleiev");

        System.out.print("quelle molécule voulez-vous visualiser ? ");
        String molecule = stdin.nextLine();

        Molecule m = chargerMolecule(cheminMolecule(molecule), mendeleiev);

        System.out.println(m);

        new PrettyMolecule(m);
    }

    public static String cheminMolecule(String nom) {
        return BASE_DATA + "/" + nom.replace(" ", "_") + ".mol";
    }

    public static ArrayList<Atome> mendeleiev(String file) {
        ArrayList<Atome> atomes = new ArrayList<Atome>();

        try {
            BufferedReader r = new BufferedReader(new FileReader(file));

            String line;
            for (int numero = 1; (line = r.readLine()) != null; numero++) {
                String[] data = line.split(":");

                atomes.add(new Atome(
                        numero,
                        data[0],
                        data[1],
                        Integer.parseInt(data[2])
                ));
            }

            r.close();
        } catch (Exception e) {
            throw new Error(e);
        }

        return atomes;
    }

    public static Molecule chargerMolecule(String fichier, ArrayList<Atome> mendeleiev) {
        try {
            BufferedReader r = new BufferedReader(new FileReader(fichier));

            Molecule m = new Molecule(r.readLine(), r.readLine());

            String[] atomesData = r.readLine().split(":");
            for (String atomeData : atomesData) {
                m.ajouterAtome(new Atome(mendeleiev.get(Integer.parseInt(atomeData) - 1)));
            }

            int i = 0;
            String[] positionAtomes = r.readLine().split(":");
            for (String positionAtome : positionAtomes) {
                String[] data = positionAtome.split(",");
                Atome atome = m.getAtome(i);
                atome.setX(Integer.parseInt(data[0]));
                atome.setY(Integer.parseInt(data[1]));
                i++;
            }

            String line;
            while ((line = r.readLine()) != null) {
                String[] liaisonData = line.split(":");
                m.ajouterLiaison(Integer.parseInt(liaisonData[0]), Integer.parseInt(liaisonData[1]));
            }

            r.close();

            return m;
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public static ArrayList<String[]> lister(File directory) {
        if (!directory.isDirectory()) {
            throw new Error(directory + " n'existe pas");
        }

        ArrayList<String[]> res = new ArrayList<String[]>();
        try {
            for (File f : directory.listFiles()) {
                BufferedReader bf = new BufferedReader(new FileReader(f));
                res.add(new String[]{ bf.readLine(), bf.readLine() });
                bf.close();
            }
        } catch (Exception e) {
            throw new Error(e);
        }
        return res;
    }

    public static ArrayList<String[]> lister(String directory) {
        return lister(new File(directory));
    }
}
