import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<State> states;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numOfStates = scanner.nextInt();
        scanner.nextLine();
        states = new ArrayList<>();
        String[] statesNames = scanner.nextLine().split(",");
        for(int i = 0; i < numOfStates; i++) {
            states.add(new State(statesNames[i]));
        }

        // Não é necessário
        scanner.nextInt();
        scanner.nextLine();
        scanner.nextLine();

        int numOfFinals = scanner.nextInt();
        scanner.nextLine();
        String[] finalNames = scanner.nextLine().split(",");
        for(int i = 0; i < numOfFinals; i++) {
            searchForState(finalNames[i]).setFinal(true);
        }

        int numOfRules = scanner.nextInt();
        scanner.nextLine();
        String[] rule;
        for(int i = 0; i < numOfRules; i++) {
            rule = scanner.nextLine().split(",");
            searchForState(rule[0])
                    .addRule(rule[1].charAt(0), searchForState(rule[2]));
        }

        String answer = "REJEITA";
        State current = states.get(0);
        for(char letter : scanner.nextLine().toCharArray()) {
            // Apagar comentario pra ver caminho percorrido
            // System.out.println(current.getName());
            current = current.read(letter);
        }
        // Apagar comentario pra ver caminho percorrido
        // System.out.println(current.getName());
        if(current.isFinal()) {
            answer = "ACEITA";
        }
        System.out.println(answer);
    }

    private static State searchForState(String name) {
        for(State state : states) {
            if(state.getName().equals(name)) {
                return state;
            }
        }
        return null;
    }
}
