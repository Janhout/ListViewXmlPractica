package com.example.administrador.listviewxml;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.ArrayList;


public class DialogoInsertar extends Activity {

    private ArrayList<String> valoresDificultad;
    private ArrayList<String> valoresPublicacion;
    private ArrayList<String> valoresPuntuacion;
    private ArrayList<String> valoresTipo;

    private EditText etNombre, etInformacion;
    private RadioButton rbSi;
    private Spinner spDificultad, spTipo, spPublicacion, spPuntuacion;

    /**********************************************************************************************/
    /**********************************************************************************************/
    /*                                    Métodos on...                                           */
    /**********************************************************************************************/
    /**********************************************************************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogo_nuevo);
        initComponents();
    }


    /**********************************************************************************************/
    /**********************************************************************************************/
    /*                                 Métodos auxiliares                                         */
    /**********************************************************************************************/
    /**********************************************************************************************/

    /*Método auxiliar de sacarDatos que carga los datos que no son obligatorios para el
    funcionamiento de la actividad.*/
    private void datosExtras(Bundle b){
        String nombre = b.getString(getString(R.string.nombreI), "");
        String info = b.getString(getString(R.string.informacionI), "");
        String publi = b.getString(getString(R.string.publicacionI), getString(R.string.otro_tipo));
        String tipo = b.getString(getString(R.string.tipoI), getString(R.string.otro_tipo));
        int dif = b.getInt(getString(R.string.dificultadI), 0);
        int punt = b.getInt(getString(R.string.puntuacionI), 0);
        boolean expan = b.getBoolean(getString(R.string.expansionI), false);
        rellenarDatos(nombre, info, publi, tipo, dif, punt, expan);
    }

    /* Método para inicializar todos los datos.*/
    private void initComponents(){
        spDificultad = (Spinner)findViewById(R.id.spinnerDificultad);
        spTipo = (Spinner)findViewById(R.id.spinnerTipo);
        spPublicacion = (Spinner)findViewById(R.id.spinnerPublicacion);
        spPuntuacion = (Spinner)findViewById(R.id.spinnerPuntuacion);
        etNombre = (EditText)findViewById(R.id.nuevoNombre);
        etInformacion = (EditText)findViewById(R.id.nuevoInfo);
        rbSi = (RadioButton)findViewById(R.id.rbSi);
        sacarDatos();
    }

    /*Metodo auxiliar que rellena los datos Extras en los View de esta actividad*/
    private void rellenarDatos(String nombre, String info, String publi, String tipo, int dif, int punt, boolean expan){
        spTipo.setSelection(valoresTipo.indexOf(tipo));
        spPublicacion.setSelection(valoresPublicacion.indexOf(publi));
        spPuntuacion.setSelection(valoresPuntuacion.indexOf(String.valueOf(punt)));
        spDificultad.setSelection(valoresDificultad.indexOf(String.valueOf(dif)));

        etNombre.setText(nombre);
        etInformacion.setText(info);
        rbSi.setChecked(expan);
    }

    /*Método auxiliar de initComponents que carga los datos que recibe la actividad de la
    * actividad principal*/
    private void sacarDatos(){
        Bundle b = getIntent().getExtras();
        if(b !=null ){
            b = getIntent().getExtras();
            valoresTipo = (ArrayList<String>)b.getSerializable(getString(R.string.valoresTipoI));
            valoresDificultad = (ArrayList<String>)b.getSerializable(getString(R.string.valoresDificultadI));
            valoresPuntuacion = (ArrayList<String>)b.getSerializable(getString(R.string.valoresPuntuacionI));
            valoresPublicacion = (ArrayList<String>)b.getSerializable(getString(R.string.valoresPublicacionI));
            spDificultad.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valoresDificultad));
            spTipo.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valoresTipo));
            spPublicacion.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valoresPublicacion));
            spPuntuacion.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valoresPuntuacion));
            datosExtras(b);
        }
    }

    /**********************************************************************************************/
    /**********************************************************************************************/
    /*                                  Métodos botones                                           */
    /**********************************************************************************************/
    /**********************************************************************************************/

    /*Gestion del boton aceptar*/
    public void aceptar(View view){
        String nombre = etNombre.getText().toString();
        String publicacion = spPublicacion.getSelectedItem().toString();
        int puntuacion = Integer.valueOf(spPuntuacion.getSelectedItem().toString());
        int dificultad = Integer.valueOf(spDificultad.getSelectedItem().toString());
        String tipo = spTipo.getSelectedItem().toString();
        String informacion = etInformacion.getText().toString();
        boolean expansion = rbSi.isChecked();

        Juego juego = new Juego(nombre, publicacion, informacion, tipo, null, dificultad, puntuacion, expansion);

        Intent i = new Intent();
        Bundle bundle = new Bundle();
        bundle.putParcelable(getString(R.string.juegoI), juego);
        i.putExtras(bundle);
        setResult(RESULT_OK, i);
        finish();
    }

    /*Gestion del boton cancelar*/
    public void cancelar(View view){
        setResult(Activity.RESULT_CANCELED, null);
        finish();
    }
}
