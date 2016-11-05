package com.example.maxiaofeiday01;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.protocol.HTTP;

import com.bwei.Url.Url;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.btn);
        final ImageView image = (ImageView) findViewById(R.id.image);
        btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new Thread(){
					public void run() {
						try {
							URL url = new URL(Url.url_image);
							HttpURLConnection conn = (HttpURLConnection) url.openConnection();
						    conn.setConnectTimeout(3000);
						    int code = conn.getResponseCode();
						    if(code == 200){
						    	InputStream is = conn.getInputStream();
						    	final Bitmap bm = BitmapFactory.decodeStream(is);
						    	runOnUiThread(new  Runnable() {
									public void run() {
										image.setImageBitmap(bm);
									}
								});
						    }
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					};
				}.start();
				
			}
		});
    }


  
}
