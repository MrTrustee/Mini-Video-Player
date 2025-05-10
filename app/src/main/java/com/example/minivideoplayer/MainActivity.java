package com.example.minivideoplayer;

import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.media3.common.MediaItem;
import androidx.media3.common.Player;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private PlayerView playerView;
    private ExoPlayer player;

    private final List<String> videoUrls = Arrays.asList(
            "https://videos.pexels.com/video-files/2519660/2519660-sd_640_360_24fps.mp4",
            "https://videos.pexels.com/video-files/2023708/2023708-sd_360_640_30fps.mp4",
            "https://videos.pexels.com/video-files/3629511/3629511-sd_360_450_24fps.mp4"
    );

    private int currentVideoIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerView = findViewById(R.id.player_view);
        initializePlayer();
    }

    private void initializePlayer() {
        player = new ExoPlayer.Builder(this).build();
        playerView.setPlayer(player);
        setVideo(videoUrls.get(currentVideoIndex));

        player.addListener(new Player.Listener() {
            @Override
            public void onPlaybackStateChanged(int playbackState) {
                if (playbackState == Player.STATE_ENDED) {
                    showCompletionDialog();
                }
            }
        });
    }


    private void setVideo(String url) {
        MediaItem mediaItem = MediaItem.fromUri(url);
        player.setMediaItem(mediaItem);
        player.prepare();
        player.play();
    }

    private void showCompletionDialog() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Playback Finished!")
                .setMessage("Want to replay or play next video?")
                .setIcon(R.mipmap.ic_launcher)
                .setPositiveButton("Replay", (dialogInterface, i) -> {
                    player.seekTo(0);
                    player.play();
                })
                .setNegativeButton("Next", (dialogInterface, i) -> {
                    currentVideoIndex = (currentVideoIndex + 1) % videoUrls.size();
                    setVideo(videoUrls.get(currentVideoIndex));
                })

                .create();

        dialog.show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        player.release();
    }
}