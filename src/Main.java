import bdd.DatabaseManager;
import graphics.GameWindow;
import logics.GameManager;

public class Main {
    public static void main(String[] args) {
        DatabaseManager databaseManager =
                new DatabaseManager("jdbc:sqlite:assets/donjon.db", "guest", "guest");
        GameManager.generateInstance(databaseManager);

        GameWindow window = new GameWindow();
        GameManager.getInstance().setListener(window);
        GameManager.getInstance().demarrer(1);

        window.setVisible(true);
    }
}