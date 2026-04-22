package jp.ac.gifu_u.info.niino.jissen2_2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = (Button)  findViewById(R.id.button);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        showToast("アプリを終了しました");
        finish();
    }
    public void showToast(String string)
    {
        Toast t = Toast.makeText(
                this,string,Toast.LENGTH_SHORT);
            t.show();
    }
}