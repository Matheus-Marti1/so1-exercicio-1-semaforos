package controller;

import java.util.concurrent.Semaphore;

public class ThreadServer extends Thread {

    private final int threadId;
    private final Semaphore semaforo;

    public ThreadServer(int threadId, Semaphore semaforo) {
        this.threadId = threadId;
        this.semaforo = semaforo;
    }

    @Override
    public void run() {
        switch (threadId % 3) {
            case 1:
                Transacao1();
                break;
            case 2:
                Transacao2();
                break;
            case 0:
                Transacao0();
                break;
        }
    }

    private void Transacao0() {
        double tempoCalc = (Math.random() * 1) + 1;
        double tempoTransacao = 1.5;
        calc(tempoCalc);
        try {
            semaforo.acquire();
            calcTransacao(tempoTransacao);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaforo.release();
            tempoCalc = (Math.random() * 1) + 1;
            calc(tempoCalc);
        }
        try {
            semaforo.acquire();
            calcTransacao(tempoTransacao);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaforo.release();
            tempoCalc = (Math.random() * 1) + 1;
            calc(tempoCalc);
        }
        try {
            semaforo.acquire();
            calcTransacao(tempoTransacao);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaforo.release();
        }
    }

    private void Transacao2() {
        double tempoCalc = (Math.random() * 1) + 0.5;
        double tempoTransacao = 1.5;
        calc(tempoCalc);
        try {
            semaforo.acquire();
            calcTransacao(tempoTransacao);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaforo.release();
            tempoCalc = (Math.random() * 1) + 0.5;
            calc(tempoCalc);
        }
        try {
            semaforo.acquire();
            calcTransacao(tempoTransacao);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaforo.release();
            tempoCalc = (Math.random() * 1) + 0.5;
            calc(tempoCalc);
        }
        try {
            semaforo.acquire();
            calcTransacao(tempoTransacao);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaforo.release();
        }
    }

    private void Transacao1() {
        double tempoCalc = (Math.random() * 0.8) + 0.2;
        double tempoTransacao = 1;
        calc(tempoCalc);
        try {
            semaforo.acquire();
            calcTransacao(tempoTransacao);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaforo.release();
            tempoCalc = (Math.random() * 0.8) + 0.2;
            calc(tempoCalc);
        }
        try {
            semaforo.acquire();
            calcTransacao(tempoTransacao);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaforo.release();
        }
    }

    private void calc(double tempoCalc) {
        System.out.println("Thread #" + threadId + " está fazendo cálculos durante " + tempoCalc + " s.");
        try {
            sleep((long) (tempoCalc * 1000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Thread #" + threadId + " terminou de fazer cálculos.");
    }

    private void calcTransacao(double tempoTransacao) {
        System.out.println("Thread #" + threadId + " está fazendo uma transação durante " + tempoTransacao + " s., aguarde...");
        try {
            sleep((long) (tempoTransacao * 1000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Thread #" + threadId + " terminou de fazer uma transação.");
    }
}
