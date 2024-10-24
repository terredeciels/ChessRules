
class Tour extends Piece {
    public Tour(int positionX, int positionY, String couleur) {
        super(positionX, positionY, couleur);
    }

    @Override
    public boolean deplacementValide(int nouvelleX, int nouvelleY, Piece[][] plateau) {
        int differenceY = nouvelleX - positionX;  // Changement vertical
        int differenceX = nouvelleY - positionY;  // Changement horizontal

        // La tour se déplace soit horizontalement, soit verticalement
        if (differenceX != 0 && differenceY != 0) {
            return false;  // Le mouvement doit être uniquement sur un axe
        }

        // Vérification des obstacles sur le chemin (vertical ou horizontal)
        if (differenceX == 0) {
            int step = (differenceY > 0) ? 1 : -1;
            for (int i = 1; i < Math.abs(differenceY); i++) {
                if (plateau[positionX + i * step][positionY] != null) {
                    return false;  // Il y a un obstacle sur le chemin
                }
            }
        } else {
            int step = (differenceX > 0) ? 1 : -1;
            for (int i = 1; i < Math.abs(differenceX); i++) {
                if (plateau[positionX][positionY + i * step] != null) {
                    return false;  // Il y a un obstacle sur le chemin
                }
            }
        }

        // Valide si la case de destination est vide ou contient une pièce ennemie
        return plateau[nouvelleX][nouvelleY] == null || !plateau[nouvelleX][nouvelleY].getCouleur().equals(this.couleur);
    }

    public void deplacer(int nouvelleX, int nouvelleY, Piece[][] plateau) {
        this.positionX = nouvelleX;
        this.positionY = nouvelleY;
        System.out.println("Nouvelle position de la tour : (" + positionX + ", " + positionY + ")");
    }
}
