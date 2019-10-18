package controller.RickAndMortyGuesser;

public class RecursionPractice {

    public static void blastOffCountdown(int n) {
        if(n == 0) {
            System.out.println("Blast off!");
        } else {
            System.out.println(n);
            blastOffCountdown(n - 1);
        }
    }

    /**
     * Given a positive
     * @param n
     * @return
     */
    public static int factorial(int n) {
        if(n == 1) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    public static void main(String[] args) {
        blastOffCountdown(12);
        System.out.println(factorial(5));
    }
}
