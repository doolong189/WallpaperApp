package com.example.wallpaperapp.viewmodel;

import android.app.DownloadManager;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import java.io.File;
import java.io.IOException;

public class DetailViewModel extends ViewModel {
    public void downloadImageNew(View view ,String filename, String downloadUrlOfImage){
        Context mContext = view.getContext();
        try {
            DownloadManager dm = (DownloadManager) mContext.getApplicationContext().getSystemService(Context.DOWNLOAD_SERVICE);
            Uri downloadUri = Uri.parse(downloadUrlOfImage);
            DownloadManager.Request request = new DownloadManager.Request(downloadUri);
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                    .setAllowedOverRoaming(false)
                    .setTitle(filename)
                    .setMimeType("image/jpeg")
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, File.separator + filename + ".jpg");
            dm.enqueue(request);
        } catch (Exception e) {
            Toast.makeText(mContext, "Tải thất bại", Toast.LENGTH_SHORT).show();
        }

    }

    public void SetWallpaperEvent(View view , ImageView img) {
        Context mContext = view.getContext();
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(mContext);
        Bitmap bitmap  = ((BitmapDrawable)img.getDrawable()).getBitmap();
        try {
            wallpaperManager.setBitmap(bitmap);
            Toast.makeText(mContext, "Set Wallpaper Successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
