package com.example.angelbiker.ui.correo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.angelbiker.R;

public class mandarCorreo extends AppCompatActivity {
private EditText correo, titulo, texto;
private Button enviar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mandar_correo);

        correo = (EditText) findViewById(R.id.Correo);
        titulo = (EditText) findViewById(R.id.Titulo);
        texto = (EditText) findViewById(R.id.Texto);
        enviar = (Button) findViewById(R.id.EnviarCorreo);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mandarEmail();
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                "Correo enviado con exito", Toast.LENGTH_SHORT);

                toast1.show();
            }

            private void mandarEmail() {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.setData(Uri.parse("mailto:"));
                email.setType("text/plain");
                email.putExtra(Intent.EXTRA_EMAIL, correo.getText());
                email.putExtra(Intent.EXTRA_SUBJECT, titulo.getText());
                email.putExtra(Intent.EXTRA_TEXT, texto.getText());
                startActivity(Intent.createChooser(email, "Send Email"));
            }
        });
    }
}