package com.mycompany.garacavalli;

import javax.swing.*;
import java.io.*;
import java.util.*;

/**
 * Classe Gestoregaracavalli
 * --------------------------
 * Questa classe gestisce una simulazione di gara tra cavalli. 
 * L'utente inserisce la lunghezza del percorso e il programma crea 5 cavalli che gareggiano in parallelo (tramite thread).
 * Al termine della gara viene stampata la classifica e l'utente può scegliere di salvarla su file.
 * 
 * Funzionalità principali:
 * - Richiede la lunghezza del percorso all’utente.
 * - Avvia i thread dei cavalli.
 * - Attende la fine della gara di tutti i cavalli.
 * - Mostra la classifica finale.
 * - Permette di salvare la classifica in un file scelto dall’utente.
 * 
 * Nota: È necessario che la classe Cavallo estenda Thread e gestisca internamente
 * la logica della corsa e l’aggiornamento della classifica.
 * 
 * @author 
 * @version 1.0
 */
public class Gestoregaracavalli {

    /**
     * Metodo principale del programma.
     * 
     * Chiede all’utente la lunghezza del percorso, crea e avvia i cavalli partecipanti,
     * attende che tutti terminino la corsa, mostra la classifica finale e consente
     * di salvarla su file.
     *
     * @param args argomenti passati da linea di comando (non utilizzati)
     */
    public static void Main(String[] args) {
        Scanner tastiera = new Scanner(System.in);

        System.out.print("Inserisci la lunghezza del percorso (in metri): ");
        int percorso = tastiera.nextInt();

        ArrayList<String> classifica = new ArrayList<>();
        ArrayList<Cavallo> cavalli = new ArrayList<>();

        // Creazione dei cavalli partecipanti
        for (int i = 1; i <= 5; i++) {
            cavalli.add(new Cavallo("Cavallo" + i, percorso, classifica));
        }

        // Avvio dei thread dei cavalli
        cavalli.forEach(Thread::start);

        // Attesa che tutti i cavalli terminino la gara
        for (Cavallo c : cavalli) {
            try {
                c.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Stampa della classifica finale
        System.out.println("\nCLASSIFICA FINALE");
        for (int i = 0; i < classifica.size(); i++) {
            if (i == 0) {
                System.out.println((i + 1) + "° posto: " + classifica.get(i) + " (vincitore)");
            } else {
                System.out.println((i + 1) + "° posto: " + classifica.get(i));
            }
        }

        // Salvataggio della classifica su file
        salvaSuFile(classifica);
    }

    /**
     * Salva la classifica finale su un file scelto dall’utente tramite JFileChooser.
     * 
     * Mostra una finestra di dialogo per selezionare il percorso di salvataggio.
     * Il file contiene la classifica con i nomi dei cavalli in ordine di arrivo.
     *
     * @param classifica lista contenente i nomi dei cavalli in ordine di arrivo
     */
    private static void salvaSuFile(List<String> classifica) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Scegli dove salvare la classifica");

        int scelta = fileChooser.showSaveDialog(null);
        if (scelta == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write("CLASSIFICA GARA CAVALLI\n\n");
                for (int i = 0; i < classifica.size(); i++) {
                    if (i == 0)
                        writer.write((i + 1) + "° posto: " + classifica.get(i) + " (vincitore)\n");
                    else
                        writer.write((i + 1) + "° posto: " + classifica.get(i) + "\n");
                }
                System.out.println("\nClassifica salvata nel file: " + file.getAbsolutePath());
            } catch (IOException e) {
                System.out.println("Errore nel salvataggio del file: " + e.getMessage());
            }
        } else {
            System.out.println("Salvataggio annullato.");
        }
    }
}
