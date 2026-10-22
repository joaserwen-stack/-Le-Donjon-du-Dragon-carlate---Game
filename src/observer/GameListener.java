package observer;

import logics.Choix;
import logics.Paragraphe;
import java.util.List;

public interface GameListener {
    void onParagrapheChange(Paragraphe paragraphe, List<Choix> choix);
}