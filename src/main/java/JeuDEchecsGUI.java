import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JeuDEchecsGUI extends JFrame {
    private JButton[][] boutonsPlateau;  // Représente les cases du plateau
    private Piece[][] plateau;           // Modèle de données pour les pièces

    private int selectionX = -1;         // Coordonnée X de la pièce sélectionnée
    private int selectionY = -1;         // Coordonnée Y de la pièce sélectionnée

    public JeuDEchecsGUI() {
        setTitle("Jeu d'Échecs");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initPlateauGraphique();
        initJeu();
    }

    // Initialisation de l'interface graphique
    private void initPlateauGraphique() {
        JPanel panel = new JPanel(new GridLayout(8, 8));
        boutonsPlateau = new JButton[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boutonsPlateau[i][j] = new JButton();
                boutonsPlateau[i][j].setPreferredSize(new Dimension(80, 80));

                // Alternance de couleurs pour le plateau
                if ((i + j) % 2 == 0) {
                    boutonsPlateau[i][j].setBackground(Color.LIGHT_GRAY);
                } else {
                    boutonsPlateau[i][j].setBackground(Color.DARK_GRAY);
                }

                // Ajout d'un action listener pour chaque case
                final int x = i;
                final int y = j;
                boutonsPlateau[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        boutonClique(x, y);
                    }
                });
                panel.add(boutonsPlateau[i][j]);
            }
        }

        this.add(panel, BorderLayout.CENTER);
    }

    // Initialisation des pièces du jeu
    // Initialisation des pièces du jeu
    // Initialisation des pièces du jeu
    // Initialisation des pièces du jeu
    private void initJeu() {
        plateau = new Piece[8][8];  // Plateau de 8x8 cases

        // Initialiser toutes les cases à null (vide)
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                plateau[i][j] = null;
            }
        }

        // Initialisation des pions blancs et noirs
        for (int i = 0; i < 8; i++) {
            plateau[1][i] = new Pion(1, i, "blanc");  // Pions blancs à la rangée 1
            plateau[6][i] = new Pion(6, i, "noir");   // Pions noirs à la rangée 6
        }

        mettreAJourPlateauGraphique();
    }
    // Mise à jour de l'affichage du plateau en fonction du modèle
    private void mettreAJourPlateauGraphique() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = plateau[i][j];
                if (piece != null) {
                    if (piece instanceof Pion) {
                        boutonsPlateau[i][j].setText(piece.getCouleur().equals("blanc") ? "P" : "p");
                    }
                } else {
                    boutonsPlateau[i][j].setText("");  // Si pas de pièce
                }
            }
        }
    }

    // Gestion du clic sur une case du plateau
    // Gestion du déplacement d'une pièce
    // Gestion du déplacement d'une pièce
    // Gestion du déplacement d'une pièce
    // Gestion du déplacement d'une pièce
    // Gestion du déplacement d'une pièce
    private void boutonClique(int i, int j) {
        if (selectionX == -1 && selectionY == -1 && plateau[i][j] != null) {
            // Sélectionner une pièce
            selectionX = i;
            selectionY = j;
            boutonsPlateau[i][j].setBackground(Color.YELLOW);  // Mettre en évidence la sélection
            System.out.println("Pièce sélectionnée en (" + selectionX + ", " + selectionY + ")");
        } else if (selectionX != -1 && selectionY != -1) {
            // Vérifier si la case sélectionnée peut être une destination valide
            Piece pieceSelectionnee = plateau[selectionX][selectionY];
            if (pieceSelectionnee != null && pieceSelectionnee.deplacementValide(i, j, plateau)) {
                // Déplacer la pièce dans le modèle de données
                plateau[i][j] = pieceSelectionnee;
                plateau[selectionX][selectionY] = null;  // Libérer l'ancienne position
                pieceSelectionnee.deplacer(i, j, plateau);  // Mise à jour des coordonnées de la pièce

                // Afficher les coordonnées avant la réinitialisation
                System.out.println("Déplacement valide de (" + selectionX + ", " + selectionY + ") vers (" + i + ", " + j + ")");

                // Mettre à jour l'affichage du plateau
                mettreAJourPlateauGraphique();

                // Réinitialiser la sélection après le message de débogage
                resetSelection();
            } else {
                System.out.println("Déplacement invalide de (" + selectionX + ", " + selectionY + ") vers (" + i + ", " + j + ")");
                resetSelection();  // Réinitialiser si déplacement invalide
            }
        }
    }
    // Réinitialiser la sélection après un mouvement
    private void resetSelection() {
        // Réinitialiser la couleur des boutons
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    boutonsPlateau[i][j].setBackground(Color.LIGHT_GRAY);
                } else {
                    boutonsPlateau[i][j].setBackground(Color.DARK_GRAY);
                }
            }
        }
        selectionX = -1;
        selectionY = -1;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JeuDEchecsGUI jeu = new JeuDEchecsGUI();
            jeu.setVisible(true);
        });
    }
}

// Classe Piece et sous-classes pour les pions
abstract class Piece {
    protected int positionX;
    protected int positionY;
    protected String couleur;

    public Piece(int positionX, int positionY, String couleur) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.couleur = couleur;
    }

    public abstract boolean deplacementValide(int nouvelleX, int nouvelleY, Piece[][] plateau);

    public void deplacer(int nouvelleX, int nouvelleY, Piece[][] plateau) {
        this.positionX = nouvelleX;
        this.positionY = nouvelleY;
    }

    public String getCouleur() {
        return couleur;
    }
}

// Classe Pion avec mise à jour des coordonnées après chaque déplacement
class Pion extends Piece {
    public Pion(int positionX, int positionY, String couleur) {
        super(positionX, positionY, couleur);
    }

    @Override
    public boolean deplacementValide(int nouvelleX, int nouvelleY, Piece[][] plateau) {
        int differenceY = nouvelleX - positionX;  // Changement sur l'axe Y (vertical)
        int differenceX = nouvelleY - positionY;  // Changement sur l'axe X (horizontal)

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

    // Méthode pour mettre à jour la position du pion après un déplacement
    public void deplacer(int nouvelleX, int nouvelleY, Piece[][] plateau) {
        this.positionX = nouvelleX;
        this.positionY = nouvelleY;
        System.out.println("Nouvelle position du pion : (" + positionX + ", " + positionY + ")");
    }
}