package z_aksys.solutions.digiappequitybb.model;

import android.support.v4.app.Fragment;

import java.util.HashMap;

public class PitchSlide {

    public int id;
    public boolean isNative= false;
    public boolean isSectionalHeader= false;
    public Fragment nativeSlide;
    public String path = "";
    public int category = -1;
    public String slideThumbnailPath = "";
    public HashMap<Integer, String> title = new HashMap<>();
}
