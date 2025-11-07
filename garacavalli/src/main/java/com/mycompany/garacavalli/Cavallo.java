package com.mycompany.garacavalli;

import java.util.List;

/**
 * Classe Cavallo
 * --------------------------
 * Questa classe rappresenta un cavallo che partecipa alla gara.
 * Ogni cavallo viene eseguito come un thread separato, che simula
 * l'avanzamento del cavallo lungo il percorso fino al traguardo.
 * 
 * Durante la corsa:
 * - Il cavallo avanza di un numero fisso di metri (definito da {@code passo}).
 * - Dopo ogni avanzamento, viene stampato lo stato attuale del cavallo.
 * - Quando raggiunge o supera la distanza totale, entra nella classifica.
 * 
 * Inoltre, il metodo {@link #azzoppa()} può simulare la possibilità
 * che il cavallo venga "azzoppato" e quindi interrotto.
 * 
 * @author 
 * @version 1.0
 */
public class Cavallo extends Thread {

    /** Nome del cavallo. */
    private final String nome;

    /** Distanza totale che il cavallo deve percorrere (in metri). */
    private final int distanza;

    /** Metri percorsi finora dal cavallo. */
    private int metriPercorsi = 0;

    /** Numero di metri percorsi dal cavallo a ogni passo. */
    private final int passo = 5; 

    /** Lista condivisa che rappresenta la classifica dei cavalli arrivati. */
    private final List<String> classifica;

    /**
     * Costruttore della classe Cavallo.
     *
     * @param nome nome del cavallo
     * @param distanza distanza totale da percorrere (in metri)
     * @param classifica lista condivisa dove viene registrato l’ordine di arrivo
     */
    public Cavallo(String nome, int distanza, List<String> classifica) {
        this.nome = nome;
        this.distanza = distanza;
        this.classifica = classifica;
    }

    /**
     * Metodo eseguito quando il thread viene avviato.
     * 
     * Simula la corsa del cavallo:
     * - Avanza gradualmente di {@code passo} metri.
     * - Stampa i progressi sullo schermo.
     * - Quando raggiunge il traguardo, aggiunge il proprio nome alla classifica.
     * - Dopo ogni avanzamento, chiama {@link #azzoppa()} per simulare eventuali imprevisti.
     */
    @Override
    public void run() {
        setName(nome); // Imposta il nome del thread uguale al nome del cavallo

        while (metriPercorsi < distanza && !isInterrupted()) {
            metriPercorsi += passo;
            System.out.println(nome + " ha percorso " + metriPercorsi + " metri");

            // Se ha raggiunto o superato il traguardo, entra in classifica
            if (metriPercorsi >= distanza) {
                synchronized (classifica) {
                    classifica.add(nome);
                }
                System.out.println(nome + " ha tagliato il traguardo!");
                break; // Esce dal ciclo dopo aver terminato la gara
            }

            // Simula un possibile imprevisto (azzoppamento)
            azzoppa();

            try {
                Thread.sleep(200); // Piccola pausa per rendere più realistica la simulazione
            } catch (InterruptedException e) {
                System.out.println(nome + " si è fermato improvvisamente!");
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    /**
     * Simula la possibilità che il cavallo venga "azzoppato" (interrotto).
     * 
     * Con una probabilità casuale, il cavallo può essere interrotto.
     * Se viene interrotto, il suo thread termina prematuramente.
     * 
     * Nota: la logica è volutamente semplificata per scopi didattici.
     */
    public void azzoppa() {
        double rand = Math.random(); // Numero casuale tra 0 e 1
        // Se rand < 0.1 (10% di probabilità), il cavallo viene interrotto
        if (rand < 0.1) {
            System.out.println(nome + " si è azzoppato e non può continuare la corsa!");
            this.interrupt();
        }
    }
}
