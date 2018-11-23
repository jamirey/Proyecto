package holamundo.proyecto;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class PantallaInicial extends AppCompatActivity {
    private final int duracion = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pantalla_inicial);

        new Handler().postDelayed(new Runnable(){
            public void run(){
                Intent i = new Intent(PantallaInicial.this, Principal.class);
                startActivity(i);
                finish();
            };
        }, duracion);
    }
}
