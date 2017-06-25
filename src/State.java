import java.util.HashMap;

/**
 * Created by daniel on 24/06/17.
 */
public class State {

    private String name;
    private boolean isFinal;
    private HashMap<Character, State> rules = new HashMap<>();
    public static State dead = new State("DEAD");

    public State(String name) {
        this.name = name;
    }

    public State(String name, boolean isFinal) {
        this.name = name;
        this.isFinal = isFinal;
    }

    public void addRule(char letter, State state) {
        rules.put(letter, state);
    }

    public String getName() {
        return name;
    }


    public State read(char letter) {
        if(rules.containsKey(letter)) {
            return rules.get(letter);
        }
        return dead;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean isFinal) {
        this.isFinal = isFinal;
    }
}
