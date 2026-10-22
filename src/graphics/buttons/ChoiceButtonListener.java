package graphics.buttons;

import logics.GameManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoiceButtonListener implements ActionListener {

    private final int cibleId;

    public ChoiceButtonListener(int cibleId) {
        this.cibleId = cibleId;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Quand on clique, on demande au Singleton d'aller vers le paragraphe cible
        GameManager.getInstance().allerVers(this.cibleId);
    }
}