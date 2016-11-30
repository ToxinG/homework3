package toxing.imageloader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Антон on 30.11.2016.
 */

public class EventReceiver extends BroadcastReceiver{

    public static final String TAG = "EventReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive");
        context.startService(new Intent(context, ImageService.class));
    }
}
