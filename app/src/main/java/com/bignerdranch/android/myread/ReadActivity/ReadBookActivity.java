package com.bignerdranch.android.myread.ReadActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.bignerdranch.android.myread.R;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class ReadBookActivity extends AppCompatActivity {
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_book_textview);
        mTextView = (TextView) findViewById(R.id.text_View_book);
        mTextView.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }

        });
        openbook(getIntent().getStringExtra("address"));

    }
    public void openbook(String strFilePath)  {
        try {
            File file = new File(strFilePath);
            long lLen = file.length();
            int i = (int) lLen;

            MappedByteBuffer mbb= new RandomAccessFile(file, "r").getChannel().map(FileChannel.MapMode.READ_ONLY, 0, lLen);
            byte[]buf=new byte[i];
            for(int j =0; j < lLen; j++)
            {
                if(j>=0&&j<=lLen)
                {
                    buf[j]=mbb.get(j);
                }

            }
            String s=new String(buf,0,buf.length,"utf-8");

            Log.i("OUTPUT",s );
            mTextView.setText(s);

        } catch (FileNotFoundException e)
        {

            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}



