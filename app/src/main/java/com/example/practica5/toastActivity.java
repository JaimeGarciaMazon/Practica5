package com.example.practica5;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class toastActivity extends Activity {

    Spinner spinnerHorizontal, spinnerVertical;
    EditText editTextMensajeToast;
    Map<String, String> mapaSpinnerOrientacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
        setTitle(R.string.tituloToast);

        spinnerHorizontal = findViewById(R.id.spinnerHorizontal);
        spinnerVertical = findViewById(R.id.spinnerVertical);
        editTextMensajeToast = findViewById(R.id.editTextMensajeToast);

        mapaSpinnerOrientacion = new HashMap<String, String>() {{
            put("Arriba", getString(R.string.arriba));
            put("Abajo", getString(R.string.debajo));
            put("Derecha", getString(R.string.derecha));
            put("Izquierda", getString(R.string.izquierda));
            put("Centro", getString(R.string.centro));
        }};

        ArrayAdapter<CharSequence> adapterHorizontal = ArrayAdapter.createFromResource(this,
                R.array.spinnerHorizontal, android.R.layout.simple_spinner_item);
        spinnerHorizontal.setAdapter(adapterHorizontal);

        ArrayAdapter<CharSequence> adapterVertical = ArrayAdapter.createFromResource(this,
                R.array.spinnerVertical, android.R.layout.simple_spinner_item);
        spinnerVertical.setAdapter(adapterVertical);

    }

    public void lanzarToast(View view) {
        String texto = editTextMensajeToast.getText().toString();
        Toast t = Toast.makeText(this, texto, Toast.LENGTH_LONG);

        int orientacionVertical = getOrientacion(spinnerVertical.getSelectedItem().toString());
        int orientacionHorizontal = getOrientacion(spinnerHorizontal.getSelectedItem().toString());

        t.setGravity(orientacionVertical | orientacionHorizontal , 0, 0);

        t.show();
    }

    private int getOrientacion(String orientacion) {
        int res = 0;
        switch (mapaSpinnerOrientacion.get(orientacion)) {
            case "TOP":
                res = Gravity.TOP;
                break;
            case "BOTTOM":
                res = Gravity.BOTTOM;
                break;
            case "LEFT":
                res = Gravity.LEFT;
                break;
            case "RIGHT":
                res = Gravity.RIGHT;
                break;
        }
        return res;
    }
}
