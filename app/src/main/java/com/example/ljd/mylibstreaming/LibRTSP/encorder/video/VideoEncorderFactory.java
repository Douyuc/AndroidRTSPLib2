package com.example.ljd.mylibstreaming.LibRTSP.encorder.video;

import android.media.projection.MediaProjection;

import com.example.ljd.mylibstreaming.LibRTSP.encorder.AbstractEncorderFactory;
import com.example.ljd.mylibstreaming.LibRTSP.encorder.MediaEncorder;
import com.example.ljd.mylibstreaming.LibRTSP.quality.MediaQuality;
import com.example.ljd.mylibstreaming.LibRTSP.quality.VideoQuality;
import com.example.ljd.mylibstreaming.LibRTSP.session.Session;

/**
 * Created by ljd-pc on 2016/7/4.
 */
public class VideoEncorderFactory extends AbstractEncorderFactory {

    private static volatile VideoEncorderFactory videoEncorderFactory;

    private VideoEncorderFactory(){}

    public static VideoEncorderFactory getInstance(){
        if(videoEncorderFactory == null){
            synchronized (VideoEncorderFactory.class){
                if(videoEncorderFactory == null){
                    videoEncorderFactory = new VideoEncorderFactory();
                }
            }
        }
        return videoEncorderFactory;
    }

    @Override
    public MediaEncorder CreateEncorder(Session session) {
        MediaEncorder mediaEncorder;
        if(session.getSessionType() == 1) {
            mediaEncorder =  new H264Encorder(session.getVideoQuality(), session.getMediaProjection());
        } else if(session.getSessionType() == 3) {
            mediaEncorder =new MP4Encorder(session);
        } else if(session.getSessionType() == 2){
            mediaEncorder =new CameraEncorder(session);
        }else{
            return null;
        }
        return mediaEncorder;
    }
}
