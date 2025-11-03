/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.garacavalli;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner tastiera = new Scanner(System.in);

        System.out.print("Inserisci la lunghezza del percorso (in metri): ");
        int percorso = tastiera.nextInt();

        ArrayList<String> classifica = new ArrayList<>();
        ArrayList<Cavallo> cavalli = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            cavalli.add(new Cavallo("Cavallo" + i, percorso, classifica));
        }

        cavalli.forEach(Thread::start);

        for (Cavallo c : cavalli) {
            try {
                c.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\nCLASSIFICA FINALE");
        for (int i = 0; i < classifica.size(); i++) {
            if (i == 0) {
                System.out.println((i + 1) + "° posto: " + classifica.get(i) + " (vincitore)");
            } else {
                System.out.println((i + 1) + "° posto: " + classifica.get(i));
            }
        }

        salvaSuFile(classifica);
    }

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
