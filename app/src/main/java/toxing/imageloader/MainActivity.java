package toxing.imageloader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    final static String IMG_URL = "http://immo-games.ru/kartinki/3/ptica_sova_ptenec_1920x1080.jpg";
    final static String BROADCAST_MATCH = "ImageLoaderBroadcast";

    ImageView imageView;
    TextView msgTextView;

    BroadcastReceiver serviceReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate");

        imageView = (ImageView) findViewById(R.id.image_view);
        msgTextView = (TextView) findViewById(R.id.msg_text_view);
        final String imgFilename = getFilesDir().getPath() + "/img.png";

        if (setImage(imgFilename)) {
            imageView.setVisibility(VISIBLE);
            msgTextView.setVisibility(GONE);
        }

        serviceReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                setImage(imgFilename);
                imageView.setVisibility(VISIBLE);
                msgTextView.setVisibility(GONE);
            }
        };
        registerReceiver(serviceReceiver, new IntentFilter(BROADCAST_MATCH));
    }

    boolean setImage(String nameOfFile) {
        Log.d(TAG, "setImage");
        File f = new File(nameOfFile);
        if (f.exists()) {
            imageView.setImageBitmap(BitmapFactory.decodeFile(f.getPath()));
            return true;
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(serviceReceiver);
    }
}
