package view;

import controller.ThreadServer;

import java.util.concurrent.Semaphore;

public class Server {
    public static void main(String[] args){
        int permissoes = 1;
        Semaphore semaforo = new Semaphore(permissoes);
        for(int threadId = 1; threadId < 22; threadId++){
            Thread tServer = new ThreadServer(threadId, semaforo);
            tServer.start();
        }
    }
}
