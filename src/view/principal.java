package view;

import java.util.concurrent.Semaphore;

import controller.ThreadCarro;

public class principal {

	public static void main(String[] args) {
		String[] direcoes = { " Norte para o Sul", " Leste para o Oeste", " Oeste para o Leste", " Sul para o Norte" };
		Semaphore semaforo = new Semaphore(1);
		for (int i = 1; i < 5; i++) {
			ThreadCarro car = new ThreadCarro(direcoes[i - 1], semaforo);
			car.start();
		}
	}
}