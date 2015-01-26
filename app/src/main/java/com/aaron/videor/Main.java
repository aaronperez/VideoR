package com.aaron.videor;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.MediaController;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.VideoView;


public class Main extends ActionBarActivity {

    public VideoView vv;
    public MediaController mc;
    public int control;
    public Uri data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        vv=(VideoView)findViewById(R.id.videoView);
        Intent intent = getIntent();
        data = intent.getData();
        play(data,control);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(vv.isPlaying()){
            control=vv.getCurrentPosition();
        }
        outState.putInt("control", control);
        outState.putString("data", data.toString());
        Log.v("ControlDestroy",control+"");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        control=savedInstanceState.getInt("control");
        data.parse(savedInstanceState.getString("data"));
        play(data,control);
    }

    public void play (Uri data, int control){
        vv.setVideoPath(data.getPath());
        mc = new MediaController(this);
        mc.setAnchorView(vv);
        vv.setMediaController(mc);
        vv.seekTo(control);
        vv.start();
    }
}
