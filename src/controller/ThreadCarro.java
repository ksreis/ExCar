package controller;

import java.util.concurrent.Semaphore;

public class ThreadCarro extends Thread {

	private int xCarro;
	private String direcao;
	private double tInicial, tFinal, tTotal;
	private Semaphore semaforo;

	public ThreadCarro(String direcao, Semaphore semaforo) {
		this.xCarro = (int) this.getId();
		this.direcao = direcao;
		this.semaforo = semaforo;
	}
	@Override
	public void run() {
		CarAndando();
		try {
			CarEsperando();
			semaforo.acquire();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
		CarCruzando();
	}
	private void CarAndando() {
		int tempo = 1000;
		int distanciaPercorrida = 0;

		while (distanciaPercorrida < 100) {
			distanciaPercorrida += (int) ((Math.random() * 5) + 6);
			try {
				Thread.sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(" Carro " + xCarro + " andou " + distanciaPercorrida + " metros");
		}
	}
	private void CarEsperando() {
		System.out.println(" Carro " + xCarro + " parou no cruzamento");
		tInicial = System.nanoTime();
	}
	private void CarCruzando() {
		tFinal = System.nanoTime();
		tTotal = tFinal - tInicial;
		tTotal = tTotal / Math.pow(10, 9);
		System.out.println(" carro " + xCarro + " aguarda no cruzamento: " + tTotal
				+ "segundos e cruzou da direção " + direcao);
	}
}