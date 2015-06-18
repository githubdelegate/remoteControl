package com.brainnet.smartremote.sound;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.SparseIntArray;

public class SoundPlayer {

	private Context context;
	private SoundPool soundPool;
	private SparseIntArray soundPoolMap = new SparseIntArray();
	private int streamVolume;
	private boolean mIsEnabled = true;

	private static SoundPlayer mInstance;

	public static SoundPlayer getInstance() {

		if (mInstance == null) {
			mInstance = new SoundPlayer();
		}

		return mInstance;
	}

	public void init(Context context) {
		
		this.context = context;	
		this.streamVolume = ((AudioManager) context.getSystemService("audio"))
				.getStreamVolume(3);
	}
	
	
	public boolean isEnabled() {
		return  mIsEnabled;
	}
	
	public void setEnabled(boolean enabled) {
		mIsEnabled = enabled;
	}
	
	public SoundPlayer() {
		this.soundPool = new SoundPool(100, 3, 100);
	}

	public void loadSfx(int resId) {
		this.soundPoolMap.put(resId, Integer
				.valueOf(this.soundPool.load(this.context, resId, resId)));
	}

	public void play(int resId, int paramInt2) {
		
		if(mIsEnabled) {
			this.soundPool.play(this.soundPoolMap.get(resId), this.streamVolume,
				this.streamVolume, 1, paramInt2, 1F);
		}
	}

	public void stop(int resId) {
		this.soundPool.stop(this.soundPoolMap.get(resId));
	}
}
