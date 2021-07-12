package com.example.tresenraya;

import java.util.Random;

public class Partida {

    public final int dificultad;
    public int jugador;
    private int [] casillas;
    private final int[][] COMBINACIONES = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};


    public Partida (int dificultad) {
        // No se que hace esto
        this.dificultad = dificultad;
        // Establece que jugador es igual a 1
        jugador = 1;

        // Iniciamos el array casillas con valor 0
        casillas = new int[9];
        for (int i = 0; i<9; i++) {
            casillas[i] = 0;
        }

    }


    public int ia() {
        int casilla;

        casilla = dosEnRaya(2);
        if (casilla!=-1) return casilla;
        // dificultad intermedia
        if (dificultad>0) {
            casilla = dosEnRaya(1);
            if(casilla!=-1) return casilla;
        }
        // Dificulatad dificil
        if (dificultad == 2) {
            if (casillas[0] == 0) return 0;
            if (casillas[2] == 0) return 2;
            if (casillas[6] == 0) return 6;
            if (casillas[8] == 0) return 8;
        }
        Random casilla_azar = new Random();
        casilla = casilla_azar.nextInt(9);
        return casilla;

    }

    public boolean comprueba_casilla(int casilla) {

        // Si alguna casilla vale 0 se sale
        if (casillas[casilla]!=0) {
            return false;

        } else {
            // Sino coge el valor jugador y asigna una X o un O dependiendo de lo valga jugador
            casillas[casilla] = jugador;
        }
        return true;
    }

    public int turno() {

        boolean empate = true;
        boolean ult_movimiento = true;


        // le decimos a la maquina klas diferentes combinaciones para ganar
        for (int i=0; i<COMBINACIONES.length; i++) {
            for (int pos:COMBINACIONES[i]) {

                // System.out.println("Valor de la posicion " + pos + " " + casillas[pos]);

                // si las 3 casillas son diferentes de variable jugador devuelve false,
                if (casillas[pos]!= jugador) ult_movimiento = false;

                if (casillas[pos] == 0) {
                    empate = false;
                }

            } // Cierre for anidado

            // Si ult_mov es true retorna la variable jugador
            if(ult_movimiento) return jugador;
            ult_movimiento = true;

        } // Cierre for inicial

        if (empate) {
            return 3;
        }

        jugador++;

        if (jugador>2) {
            jugador = 1;
        }

        return 0;

    }

    public int dosEnRaya(int jugador_turno) {
        int casilla = -1;
        int cuantas_lleva = 0;

        for (int i=0; i<COMBINACIONES.length; i++) {

            for (int pos:COMBINACIONES[i]) {

                if (casillas[pos] == jugador_turno) {
                    cuantas_lleva++;
                }
                if (casillas[pos] == 0) {
                    casilla = pos;
                }
            } // Cierre for anidado

            if (cuantas_lleva == 2 && casilla!=-1) {
                return casilla;
            }
            casilla = -1;
            cuantas_lleva = 0;

        } // Cierre for inicial
        return -1;

    }



}
