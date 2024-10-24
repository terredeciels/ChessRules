
class Pion extends Piece {
    public Pion(int positionX, int positionY, String couleur) {
        super(positionX, positionY, couleur);
    }

    @Override
    public boolean deplacementValide(int nouvelleX, int nouvelleY, Piece[][] plateau) {
        int differenceY = nouvelleX - positionX;  // Changement vertical
        int differenceX = nouvelleY - positionY;  // Changement horizontal

        // Debug : Imprimer les informations critiques
        System.out.println("Position actuelle : (" + positionX + ", " + positionY + ")");
        System.out.println("Position de destination : (" + nouvelleX + ", " + nouvelleY + ")");
        System.out.println("Différence Y (Vertical) : " + differenceY);
        System.out.println("Différence X (Horizontal) : " + differenceX);
        System.out.println("État de la case destination : " + (plateau[nouvelleX][nouvelleY] == null ? "Libre" : "Occupée"));

        // Mouvement normal pour un pion blanc (avance vers le bas du tableau)
        if (couleur.equals("blanc")) {
            System.out.println("Pion blanc en mouvement...");
            if (differenceY == 1 && differenceX == 0) {
                if (plateau[nouvelleX][nouvelleY] == null) {
                    return true;
                }
            }

            if (positionX == 1 && differenceY == 2 && differenceX == 0) {
                if (plateau[nouvelleX][nouvelleY] == null && plateau[positionX + 1][positionY] == null) {
                    return true;
                }
            }
        }

        // Mouvement normal pour un pion noir (avance vers le haut du tableau)
        else if (couleur.equals("noir")) {
            System.out.println("Pion noir en mouvement...");
            if (differenceY == -1 && differenceX == 0) {
                if (plateau[nouvelleX][nouvelleY] == null) {
                    return true;
                }
            }

            if (positionX == 6 && differenceY == -2 && differenceX == 0) {
                if (plateau[nouvelleX][nouvelleY] == null && plateau[positionX - 1][positionY] == null) {
                    return true;
                }
            }
        }

        return false;
    }

    public void deplacer(int nouvelleX, int nouvelleY, Piece[][] plateau) {
        this.positionX = nouvelleX;
        this.positionY = nouvelleY;
        System.out.println("Nouvelle position du pion : (" + positionX + ", " + positionY + ")");
    }
}
