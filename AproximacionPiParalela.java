import java.util.Random;

public class AproximacionPiParalela {
    public static void main(String[] args) throws InterruptedException {
        int numPuntos = 1000000; // Número total de puntos
        int numHilos = 4; // Número de hilos

        double aproximacionPi = aproximarPiParalela(numPuntos, numHilos);
        System.out.println("Aproximación de Pi con " + numPuntos + " puntos y " + numHilos + " hilos: " + aproximacionPi);
    }

    public static double aproximarPiParalela(int numPuntos, int numHilos) throws InterruptedException {
        int puntosPorHilo = numPuntos / numHilos;
        int[] resultados = new int[numHilos];
        Thread[] hilos = new Thread[numHilos];

        // Crear y arrancar los hilos
        for (int i = 0; i < numHilos; i++) {
            final int indice = i; // Índice del hilo actual
            hilos[i] = new Thread(() -> {
                Random random = new Random();
                int puntosDentroDelCirculo = 0;

                for (int j = 0; j < puntosPorHilo; j++) {
                    double x = random.nextDouble() * 2 - 1; // Generar un número entre -1 y 1
                    double y = random.nextDouble() * 2 - 1; // Generar un número entre -1 y 1

                    if (x * x + y * y <= 1) {
                        puntosDentroDelCirculo++;
                    }
                }

                resultados[indice] = puntosDentroDelCirculo;
            });
            hilos[i].start();
        }

        // Esperar a que todos los hilos terminen
        for (Thread hilo : hilos) {
            hilo.join();
        }

        // Sumar los puntos dentro del círculo de todos los hilos
        int totalPuntosDentroDelCirculo = 0;
        for (int puntos : resultados) {
            totalPuntosDentroDelCirculo += puntos;
        }

        // Calcular la aproximación de Pi
        return 4.0 * totalPuntosDentroDelCirculo / numPuntos;
    }
}
