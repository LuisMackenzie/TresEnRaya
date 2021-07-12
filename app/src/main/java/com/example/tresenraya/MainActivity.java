package com.example.tresenraya;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {

    private int jugadores, dificultad = 0;
    private int[] casillas;
    private Partida partida;
    private ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Iniciamos el array casillas que identifica cada casilla y la almacena
        casillas = new int[9];
        casillas[0] = R.id.a1;
        casillas[1] = R.id.a2;
        casillas[2] = R.id.a3;
        casillas[3] = R.id.b1;
        casillas[4] = R.id.b2;
        casillas[5] = R.id.b3;
        casillas[6] = R.id.c1;
        casillas[7] = R.id.c2;
        casillas[8] = R.id.c3;

    }

    public void aJugar(View vista) {

        // bucle para vaciar las casillas
        for (int cadaCasilla:casillas) {
            imagen = (ImageView)findViewById((cadaCasilla));
            imagen.setImageResource(R.drawable.casilla2);
        }
        // Algo aqui
        jugadores = 1;
        // Si se pulsa 2 jugadores cambio la variable jugador
        if (vista.getId() == R.id.btn2) {
            jugadores = 2;

        }

        RadioGroup configDificultad = (RadioGroup)findViewById(R.id.configD);

        int id = configDificultad.getCheckedRadioButtonId();

        if (id == R.id.rb_normal) {
            dificultad = 1;
        } else if (id == R.id.rb_dificil) {
            dificultad = 2;
        }

        partida = new Partida(dificultad);

        ((Button)findViewById(R.id.btn1)).setEnabled(false);
        ((Button)findViewById(R.id.btn2)).setEnabled(false);
        ((RadioGroup)findViewById(R.id.configD)).setAlpha(0);

    }

    public void Toque(View miVista) {

        if(partida == null) {
            return;
        }

        int casilla = 0;
        // Bucle: mientra i sea menor a 9
        for (int i = 0; i < 9; i++) {
            if (casillas[i] == miVista.getId()){
                casilla = i;
                break;

            }
        }
        Toast.makeText(this, "Has pulsado la casilla " + casilla, Toast.LENGTH_SHORT).show();

        // Metodo simplificado de
        // if (!partida.comprueba_casilla(casilla))
            // return;

        if (partida.comprueba_casilla(casilla) == false) {
            return;
        }

        Marca(casilla);
        int resultado = partida.turno();
        if (resultado > 0) {
            Terminar(resultado);
            return;
        }

        casilla = partida.ia();

        while(partida.comprueba_casilla(casilla)!=true) {
            casilla = partida.ia();
        }

        Marca(casilla);
        resultado = partida.turno();
        if (resultado > 0) {
            Terminar(resultado);
        }

    }

    private void Terminar(int resultado){
        String mensaje;
        if(resultado == 1) {
            mensaje = "Ganan los Circulos";
        } else if (resultado == 2) {
            mensaje = "Ganan las Cruzes";
        } else {
            mensaje = "Empate";
        }
        Toast toast = Toast.makeText(this,mensaje, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
        partida = null;
        ((Button)findViewById(R.id.btn1)).setEnabled(true);
        ((Button)findViewById(R.id.btn2)).setEnabled(true);
        ((RadioGroup)findViewById(R.id.configD)).setAlpha(1);

    }

    private void Marca(int casilla) {
        ImageView imagen = (ImageView)findViewById(casillas[casilla]);
        if (partida.jugador == 1) {
            imagen.setImageResource(R.drawable.circulo2);
        } else {
            imagen.setImageResource(R.drawable.aspa2);
        }
    }

}