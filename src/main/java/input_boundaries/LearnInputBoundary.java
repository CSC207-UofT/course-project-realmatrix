package input_boundaries;

import entity.Card;
import entity.Pack;

import java.util.ArrayList;

public interface LearnInputBoundary {
    Pack getPack();
    ArrayList<Card> doable();
}
