package toxing.imageloader;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.util.Log;

import java.io.FileOutputStream;
import java.net.URL;

/**
 * Created by Антон on 29.11.2016.
 */

public class ImageService extends IntentService {

    public static final String TAG = "ImageService";

    public ImageService() {
        super("ImageService");
    }

    public void onCreate() {
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.d(TAG, "onHandleIntent");

        try {
            URL url = new URL(MainActivity.IMG_URL);
            Bitmap b = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            FileOutputStream fout = openFileOutput("img.png", Context.MODE_PRIVATE);
            b.compress(Bitmap.CompressFormat.PNG, 100, fout);
            fout.close();
            sendBroadcast(new Intent(MainActivity.BROADCAST_MATCH));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }
}
