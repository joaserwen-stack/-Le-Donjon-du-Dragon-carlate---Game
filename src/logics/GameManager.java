package logics;

import bdd.DatabaseManager;
import observer.GameListener;
import java.util.List;

public class GameManager {

    private static GameManager instance;

    private DatabaseManager databaseManager;
    private Paragraphe paragrapheCourant;
    private GameListener listener;

    // Constructeur privé : interdit de faire "new GameManager()" ailleurs
    private GameManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    // Appelée une seule fois au démarrage dans le Main
    public static void generateInstance(DatabaseManager databaseManager) {
        if (instance == null) {
            instance = new GameManager(databaseManager);
        }
    }

    // Permet d'accéder au chef d'orchestre depuis n'importe où (ex: les boutons)
    public static GameManager getInstance() {
        return instance;
    }

    // --- 2. Gestion de l'Observateur (Exercice 7) ---
    public void setListener(GameListener listener) {
        this.listener = listener;
    }

    // 3. Logique de navigation (Exercice 6 & 7)
    public void demarrer(int paragrapheDepartId) {
        chargerParagraphe(paragrapheDepartId);
    }

    public void allerVers(int cibleId) {
        chargerParagraphe(cibleId);
    }

    private void chargerParagraphe(int id) {
        this.paragrapheCourant = this.databaseManager.getParagraphe(id);
        List<Choix> choix = this.databaseManager.getChoix(id);

        // Si une fenêtre écoute, on la prévient du changement
        if (this.listener != null) {
            this.listener.onParagrapheChange(this.paragrapheCourant, choix);
        }
    }

    public Paragraphe getParagrapheCourant() {
        return this.paragrapheCourant;
    }
}