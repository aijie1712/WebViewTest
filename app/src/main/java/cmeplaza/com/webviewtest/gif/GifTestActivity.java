package cmeplaza.com.webviewtest.gif;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.IOException;

import cmeplaza.com.webviewtest.R;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class GifTestActivity extends AppCompatActivity {
    private ImageView iv_gif1;
    private ImageView iv_gif2;
    private GifImageView iv_gif3;

    //    private String gifUrl = "http://p2.so.qhimgs1.com/bdr/200_200_/t0135ec4e22be52a3ae.gif";
    private String gifUrl = "http://p2.so.qhmsg.com/bdr/200_200_/t010437ad1f6444ab27.gif";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif_test);
        iv_gif1 = findViewById(R.id.iv_gif1);
        iv_gif2 = findViewById(R.id.iv_gif2);
        iv_gif3 = findViewById(R.id.iv_gif3);


        Glide.with(this).load(R.drawable.hi).asGif().into(iv_gif1);
        Glide.with(this).load(gifUrl).asGif().into(iv_gif2);

        try {
            GifDrawable gifFromAssets = new GifDrawable(getResources(), R.drawable.hi);
            if (gifFromAssets != null) {
                iv_gif3.setImageDrawable(gifFromAssets);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
