package mans.abdullah.abdullah_mansour.mediaplayer;

import android.annotation.TargetApi;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView playy,back,forw,back2,next;
    SeekBar seekBar;
    TextView current_song_name,current_time,song_time;
    MediaPlayer mediaPlayer;
    Handler handler;
    Runnable runnable;
    ArrayList<SongData> songDataArrayList;
    Adapter adapter;
    ListView listView;
    int pos;
    long total_duration,current_duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playy = findViewById(R.id.play_btn);
        back = findViewById(R.id.back_btn);
        forw = findViewById(R.id.for_btn);
        current_song_name = findViewById(R.id.current_song_name);
        current_time = findViewById(R.id.current_time);
        song_time = findViewById(R.id.song_time);
        back2 = findViewById(R.id.back2_btn);
        next = findViewById(R.id.next_btn);
        listView = findViewById(R.id.listview);

        seekBar = findViewById(R.id.seekbar);

        songDataArrayList = new ArrayList<>();

        songDataArrayList.add(new SongData("La Calin", R.drawable.lacalin, R.raw.lacalin));
        songDataArrayList.add(new SongData("Lay Lay", R.drawable.laylay, R.raw.laylay));
        songDataArrayList.add(new SongData("Six Feet Under", R.drawable.belli, R.raw.sixfeet));
        songDataArrayList.add(new SongData("لو عمري يرجع", R.drawable.samo, R.raw.samo));
        songDataArrayList.add(new SongData("Balti - Ya Lili feat. Hamouda", R.drawable.balti, R.raw.balti));

        adapter = new Adapter(getApplicationContext(), 0 , songDataArrayList);

        listView.setAdapter(adapter);

        //mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.idres);
        //mediaPlayer.setLooping(true);
        handler = new Handler();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id)
            {
                pos = position;

                SongData songData = adapter.getItem(position);

                if (mediaPlayer != null)
                {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                }

                if (songData != null)
                {
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), songData.getSong_resource());
                    mediaPlayer.setLooping(true);
                }

                /*mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
                {
                    @Override
                    public void onCompletion(MediaPlayer mp)
                    {
                        SongData songData1 = adapter.getItem(position + 1);

                        if (songData1 != null)
                        {
                            mediaPlayer = MediaPlayer.create(getApplicationContext(), songData1.getSong_resource());
                        }

                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
                        {
                            @Override
                            public void onPrepared(MediaPlayer mp)
                            {
                                seekBar.setMax(mp.getDuration());
                                changeseekbar();
                            }
                        });

                        mediaPlayer.start();
                        changeseekbar();

                        if (songData1 != null)
                        {
                            current_song_name.setText(songData1.getSong_name());
                        }
                    }
                });*/

                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
                {
                    @Override
                    public void onPrepared(MediaPlayer mp)
                    {
                        seekBar.setMax(mp.getDuration());
                        changeseekbar();
                    }
                });

                mediaPlayer.start();
                playy.setImageDrawable(getResources().getDrawable(R.drawable.pause));
                changeseekbar();
                current_song_name.setVisibility(View.VISIBLE);
                if (songData != null)
                {
                    current_song_name.setText(songData.getSong_name());
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                if (mediaPlayer != null)
                {
                    if (fromUser)
                    {
                        mediaPlayer.seekTo(progress);
                    }
                } else
                    {
                        Toast.makeText(getApplicationContext(), "please choose song firstly", Toast.LENGTH_SHORT).show();
                    }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });

        playy.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v)
            {
                if (mediaPlayer != null)
                {
                    if (mediaPlayer.isPlaying())
                    {
                        mediaPlayer.pause();
                        playy.setImageDrawable(getResources().getDrawable(R.drawable.play));
                    } else
                        {
                            mediaPlayer.start();
                            playy.setImageDrawable(getResources().getDrawable(R.drawable.pause));
                            changeseekbar();
                        }
                } else
                    {
                        Toast.makeText(getApplicationContext(), "please choose song firstly", Toast.LENGTH_SHORT).show();
                    }
            }
        });

        forw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (mediaPlayer != null)
                {
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 5000);
                } else
                {
                    Toast.makeText(getApplicationContext(), "please choose song firstly", Toast.LENGTH_SHORT).show();
                }
            }
        });

        forw.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v)
            {
                if (mediaPlayer != null)
                {
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 10000);
                } else
                    {
                        Toast.makeText(getApplicationContext(), "please choose song firstly", Toast.LENGTH_SHORT).show();
                    }
                return true;
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null)
                {
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 5000);
                } else
                    {
                        Toast.makeText(getApplicationContext(), "please choose song firstly", Toast.LENGTH_SHORT).show();
                    }
            }
        });

        back.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v)
            {
                if (mediaPlayer != null)
                {
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 10000);
                } else
                    {
                        Toast.makeText(getApplicationContext(), "please choose song firstly", Toast.LENGTH_SHORT).show();
                    }
                return true;
            }
        });

        next.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(), "next", Toast.LENGTH_SHORT).show();
            }
        });

        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(), "back", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*@Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.pause();
        if (mediaPlayer.isPlaying())
        {
            playy.setImageDrawable(getResources().getDrawable(R.drawable.pause));
        } else
            {
                playy.setImageDrawable(getResources().getDrawable(R.drawable.play));
            }
    }*/

    private void changeseekbar()
    {
        seekBar.setProgress(mediaPlayer.getCurrentPosition());

        if (mediaPlayer.isPlaying())
        {
            runnable = new Runnable()
            {
                @Override
                public void run() {
                    changeseekbar();
                }
            };
            handler.postDelayed(runnable, 1000);
        }
    }
}
