import java.util.*;

public class Main {


    public static List<Integer> rzut_kostka(int numberOfDice) {
        Random random = new Random();
        List<Integer> wyniki = new ArrayList<>();
        for (int i = 0; i < numberOfDice; i++) {
            wyniki.add(random.nextInt(6) + 1);
        }
        return wyniki;
    }


    public static int oblicznie(List<Integer> throwsResult) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int result : throwsResult) {
            frequencyMap.put(result, frequencyMap.getOrDefault(result, 0) + 1);
        }

        int punkty = 0;
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() >= 2) {
                punkty += entry.getKey() * entry.getValue();
            }
        }
        return punkty;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            int ilosc_kostek = 0;


            while (ilosc_kostek < 3 || ilosc_kostek > 10) {
                System.out.print("Podaj liczbę kostek (od 3 do 10): ");
                if (scanner.hasNextInt()) {
                    ilosc_kostek = scanner.nextInt();
                } else {
                    scanner.next();
                }
            }


            List<Integer> wyniki_rzutow = rzut_kostka(ilosc_kostek);
            System.out.println("Wyniki rzutów:");
            for (int i = 0; i < wyniki_rzutow.size(); i++) {
                System.out.println("Kostka " + (i + 1) + ": " + wyniki_rzutow.get(i));
            }


            int points = oblicznie(wyniki_rzutow);
            System.out.println("Uzyskano punktów: " + points);


            System.out.print("Czy chcesz zagrać ponownie? (t/n): ");
            String response = scanner.next().toLowerCase();
            playAgain = response.equals("t");
        }

        System.out.println("Dziękujemy za grę!");
        scanner.close();
    }
}
