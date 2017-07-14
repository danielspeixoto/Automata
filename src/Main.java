import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<State> states;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println((int)'\n');
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
        boolean isCharIndexed = true;
        String[] rules = new String[numOfRules];
        for(int i = 0; i < numOfRules; i++) {
            rules[i] = scanner.nextLine();
            rule = rules[i].split(",");
            if(rule[1].matches("[^0-9]") || Integer.valueOf(rule[1]) < 10) {
                isCharIndexed = false;
            }
        }
        for(int i = 0; i < numOfRules; i++) {
            rule = rules[i].split(",");
            if(isCharIndexed) {
                searchForState(rule[0])
                        .addRule((Integer.valueOf(rule[1])), searchForState(rule[2]));
            } else {
                searchForState(rule[0])
                        .addRule(rule[1].charAt(0), searchForState(rule[2]));
            }

        }

        String answer = "REJEITA";
        State current = states.get(0);
        boolean end = false;
        int iterations = 0;
        String line;
        while (!end) {
            line = scanner.nextLine();
            if(iterations > 0 && !(line.length() == 1 && line.charAt(0) == 'q')) {
                current = current.read((char)32);
            }
            for(char letter : line.toCharArray()) {
                if(letter == 'q') {
                    end = true;
                    break;
                }
                // Apagar comentario pra ver caminho percorrido
                // System.out.println(current.getName());
                current = current.read(letter);
            }
            iterations++;
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
