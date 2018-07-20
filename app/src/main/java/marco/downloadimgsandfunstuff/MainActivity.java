package marco.downloadimgsandfunstuff;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    ImageView downloadedImage;

    public void downloadImage(View view) {

        //https://upload.wikimedia.org/wikipedia/commons/thumb/3/31/Monica_Bellucci%2C_Women%27s_World_Awards_2009_b.jpg/220px-Monica_Bellucci%2C_Women%27s_World_Awards_2009_b.jpg


        ImageDownloader task = new ImageDownloader();
        Bitmap whoPicture;

        try {
            whoPicture = task.execute("https://upload.wikimedia.org/wikipedia/commons/thumb/3/31/Monica_Bellucci%2C_Women%27s_World_Awards_2009_b.jpg/220px-Monica_Bellucci%2C_Women%27s_World_Awards_2009_b.jpg").get();
            downloadedImage.setImageBitmap(whoPicture);
        } catch (Exception e) {

            e.printStackTrace();

        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downloadedImage = findViewById(R.id.imageView);


    }

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {


        @Override
        protected Bitmap doInBackground(String... urls) {

            try {

                URL url = new URL(urls[0]);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.connect();

                InputStream inputStream = connection.getInputStream();

                Bitmap who = BitmapFactory.decodeStream(inputStream);

                return who;



            } catch (MalformedURLException e) {

                e.printStackTrace();

            } catch (IOException e) {

                e.printStackTrace();
            }

            return null;

        }
    }
}
