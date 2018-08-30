package com.soren.sagen.imagedownloading;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    ImageView dwmloadImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     dwmloadImg=findViewById(R.id.imageView);
    }

    public class ImageDownloader extends AsyncTask<String,Void,Bitmap>{

        @Override
        protected Bitmap doInBackground(String... urls) {

            try {
                URL url=new URL(urls[0]);

                HttpURLConnection connection= (HttpURLConnection) url.openConnection();

                connection.connect();

                InputStream inputStream=connection.getInputStream();

                Bitmap myBitmap= BitmapFactory.decodeStream(inputStream);

                return myBitmap;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public void downlodImage(View view)
    {
        /**/

        ImageDownloader task=new ImageDownloader();
        Bitmap myImg;
        try {
            myImg=task.execute("https://i2-prod.mirror.co.uk/incoming/article9287204.ece/ALTERNATES/s615/Mickey-mouse.jpg").get();
            dwmloadImg.setImageBitmap(myImg);

        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.i("Interaction ", "Button Tapped");
    }
}
