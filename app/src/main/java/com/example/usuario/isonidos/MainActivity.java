package com.example.usuario.isonidos;

import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    SoundPool sonidos;
    Field[] nombreCanciones = R.raw.class.getFields();
    int[] arraySonidos = new int[nombreCanciones.length];
    int idCancion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sonidos = new SoundPool(4, AudioManager.STREAM_MUSIC,0);
    }

    @Override
    protected  void onStart() {
        super.onStart();
        for(int i = 0; i<arraySonidos.length; i++){
            try {
                idCancion = nombreCanciones[i].getInt(nombreCanciones[i]);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            arraySonidos[i] = sonidos.load(this, idCancion , 1);
        }
    }

    public void sonido(View vista){
        Button b = (Button) findViewById(vista.getId());
        int numSonido = Integer.valueOf(b.getTag().toString());
        sonidos.play(arraySonidos[numSonido] ,1,1,1,0,1);
    }
}
