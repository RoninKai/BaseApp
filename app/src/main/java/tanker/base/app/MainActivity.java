package tanker.base.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import tanker.app.frame.util.LogUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtils.i("hahahhah ");
    }

}