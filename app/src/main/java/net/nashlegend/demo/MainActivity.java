package net.nashlegend.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import net.nashlegend.anypref.AnyPref;
import net.nashlegend.anypref.SharedPrefs;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.btn);
        assert button != null;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anyPref();
                namedPref();
            }
        });
    }

    private void anyPref() {
        Sample sample = new Sample();
        sample.boolField = true;
        sample.intField = 63;
        sample.floatField = 42.0f;
        sample.stringField = "sample string";
        AnyPref.save(sample);
        AnyPref.read(Sample.class);
    }

    private void namedPref() {
        AnyPref.getPrefs(getLocalClassName())
                .putLong("long", 920394857382L)
                .putInt("int", 63)
                .putString("string", "sample string");

        AnyPref.getPrefs(Sample.class)
                .beginTransaction()
                .putLong("long", 920394857382L)
                .putInt("int", 63)
                .putString("string", "sample string")
                .commit();

        SharedPrefs sharedPrefs = AnyPref.getPrefs(getLocalClassName());
        System.out.println(sharedPrefs.getInt("int", 0));
        System.out.println(sharedPrefs.getLong("long", 0));
        System.out.println(sharedPrefs.getString("string", ""));
    }
}
