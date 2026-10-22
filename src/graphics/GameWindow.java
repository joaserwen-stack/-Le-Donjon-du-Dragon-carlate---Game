package graphics;

import graphics.buttons.ChoiceButtonListener;
import logics.Choix;
import logics.GameManager;
import logics.Paragraphe;
import observer.GameListener;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class GameWindow extends JFrame implements GameListener {

    // Attributs conservés pour être modifiés à chaque changement de paragraphe
    private JLabel titleLabel;
    private JTextArea textArea;
    private JPanel buttonsPanel;

    public GameWindow() {
        super();

        // 1. Paramètres de la fenêtre
        this.setTitle("Le Donjon du Dragon Écarlate");
        this.setSize(900, 650);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 2. Barre de menus (Exercice 9)
        creerMenuBar();

        // 3. Zone de texte centrale (Exercice 3)
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setOpaque(false);
        textArea.setForeground(Color.WHITE);
        textArea.setFont(new Font("Serif", Font.BOLD, 20));
        textArea.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40)); // Marges intérieures

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);

        // 4. Zone des boutons au Sud (Exercice 3)
        buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.setOpaque(false);

        // 5. Chargement des images et du fond Cover-Fit (Exercice 2)
        final Image backgroundImage;
        try {
            backgroundImage = ImageIO.read(new File("assets/fond_donjon.png"));
            this.setIconImage(ImageIO.read(new File("assets/icone_dragon.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JPanel mainPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int iw = backgroundImage.getWidth(null);
                int ih = backgroundImage.getHeight(null);
                double scale = Math.max((double) getWidth() / iw, (double) getHeight() / ih);
                int w = (int) (iw * scale);
                int h = (int) (ih * scale);
                int x = (getWidth() - w) / 2;
                int y = (getHeight() - h) / 2;
                g.drawImage(backgroundImage, x, y, w, h, null);
            }
        };

        // 6. Titre au Nord (Exercice 2)
        titleLabel = new JLabel("Le Donjon du Dragon Écarlate", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Old English Text MT", Font.PLAIN, 36));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));

        // 7. Assemblage sur le panneau principal
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        this.setContentPane(mainPanel);
    }

    // Méthode dédiée pour construire le menu (Exercice 9)
    private void creerMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuPartie = new JMenu("Partie");

        // Élément "Nouvelle partie" (Ctrl+N)
        JMenuItem itemNouvellePartie = new JMenuItem("Nouvelle partie");
        itemNouvellePartie.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        itemNouvellePartie.addActionListener(e -> GameManager.getInstance().demarrer(1));

        // Élément "Quitter" (Ctrl+Q)
        JMenuItem itemQuitter = new JMenuItem("Quitter");
        itemQuitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
        itemQuitter.addActionListener(e -> System.exit(0));

        // Assemblage du menu
        menuPartie.add(itemNouvellePartie);
        menuPartie.addSeparator(); // Ligne de séparation
        menuPartie.add(itemQuitter);

        menuBar.add(menuPartie);
        this.setJMenuBar(menuBar);
    }

    // Méthode déclenchée automatiquement par GameManager (Exercices 7 & 8)
    @Override
    public void onParagrapheChange(Paragraphe paragraphe, List<Choix> choix) {
        // 1. Mise à jour du texte de l'histoire
        this.textArea.setText(paragraphe.getTexte());
        this.textArea.setCaretPosition(0); // Remonte l'ascenseur tout en haut

        // 2. Mise à jour du titre selon le type de paragraphe (Exercice 8)
        switch (paragraphe.getType()) {
            case VICTOIRE:
                this.titleLabel.setText("Victoire !");
                break;
            case DEFAITE:
                this.titleLabel.setText("Vous avez péri...");
                break;
            case NORMAL:
            default:
                this.titleLabel.setText("Le Donjon du Dragon Écarlate");
                break;
        }

        // 3. Reconstruction dynamique des boutons de choix
        this.buttonsPanel.removeAll(); // On supprime les anciens boutons

        for (Choix c : choix) {
            JButton bouton = new JButton(c.getTexte());
            // On branche notre écouteur avec l'ID de destination
            bouton.addActionListener(new ChoiceButtonListener(c.getCibleId()));
            this.buttonsPanel.add(bouton);
        }

        // 4. Indispensable en Swing quand on modifie les composants à la volée :
        this.buttonsPanel.revalidate(); // Recalcule la disposition des nouveaux boutons
        this.buttonsPanel.repaint();    // Redessine l'écran proprement
    }
}