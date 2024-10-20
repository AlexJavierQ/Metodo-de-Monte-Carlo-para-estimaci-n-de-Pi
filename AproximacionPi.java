import java.util.Random;

public class AproximacionPi {
    public static void main(String[] args) {
        int numPuntos = 1000000; // Puedes cambiar la cantidad de puntos para mejorar la precisión
        double aproximacionPi = aproximarPi2(numPuntos);

        System.out.println("Aproximación de Pi con " + numPuntos + " puntos: " + aproximacionPi);
    }

    public static double aproximarPi2(int numPuntos) {
        Random random = new Random();
        int puntosDentroDelCirculo = 0;

        for (int i = 0; i < numPuntos; i++) {
            double x = random.nextDouble() * 2 - 1; // Generar un número entre -1 y 1
            double y = random.nextDouble() * 2 - 1; // Generar un número entre -1 y 1

            if (x * x + y * y <= 1) {
                puntosDentroDelCirculo++;
            }
        }

        return 4.0 * puntosDentroDelCirculo / numPuntos;
    }
}
