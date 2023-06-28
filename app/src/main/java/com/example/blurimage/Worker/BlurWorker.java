package com.example.blurimage.Worker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.blurimage.R;
import com.example.blurimage.utils.WorkerUtils;

public class BlurWorker extends Worker {
    private static final String TAG = "BlurWorker";

    public static  Bitmap output;
    public static Uri outputUri;
    public BlurWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        try{
            Bitmap picture= BitmapFactory.decodeResource(getApplicationContext().getResources(),
                    R.drawable.profile);
            output= WorkerUtils.blurImage(picture,getApplicationContext());
            Log.d(TAG, "image blurred successfully "+output);
            outputUri=WorkerUtils.WriteBitmapToFile(getApplicationContext(),output);
            Log.d(TAG, "Write to file "+outputUri);

        }
        catch (Throwable t)
        {
            Log.d(TAG, "Exception "+t.getMessage());
            return Result.failure();
        }
        return Result.success();
    }
}
