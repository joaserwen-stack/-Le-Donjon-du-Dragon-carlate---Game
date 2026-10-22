package bdd;

import logics.Choix;
import logics.Paragraphe;
import logics.TypeFin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private String url;
    private String user;
    private String password;

    public DatabaseManager(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    // Récupérer un paragraphe unique par son ID
    public Paragraphe getParagraphe(int id) {
        String sql = "SELECT texte, type FROM paragraphes WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(this.url, this.user, this.password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String texte = resultSet.getString(1);
                    // Conversion du texte de la base en constante de l'énumération TypeFin
                    TypeFin type = TypeFin.valueOf(resultSet.getString(2));
                    return new Paragraphe(id, texte, type);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Récupérer tous les choix associés à un paragraphe, triés par ordre
    public List<Choix> getChoix(int paragrapheId) {
        List<Choix> listeChoix = new ArrayList<>();
        String sql = "SELECT texte, cible_id FROM choix WHERE paragraphe_id = ? ORDER BY ordre";

        try (Connection connection = DriverManager.getConnection(this.url, this.user, this.password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, paragrapheId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String texte = resultSet.getString(1);
                    int cibleId = resultSet.getInt(2);
                    listeChoix.add(new Choix(texte, cibleId));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeChoix;
    }
}