package com.brainnet.smartremote.sound;
import android.content.Context;

import com.brainnet.smartremote.R;

public class Sounds extends SoundPlayer {

	private static Sounds mInstance;
	
	public static Sounds getInstance() {

		if (mInstance == null) {
			mInstance = new Sounds();
		}

		return mInstance;
	}
	
	
	@Override
	public void init(Context context) {

		super.init(context);
		this.loadSfx(R.raw.bomb);
		this.loadSfx(R.raw.empty);
		this.loadSfx(R.raw.flag);
		this.loadSfx(R.raw.scan);
		this.loadSfx(R.raw.start);
	}
	
	public void playBomb() {
		this.play(R.raw.bomb, 0);
	}
	public void playEmpty() {
		this.play(R.raw.empty, 0);
	}
	
	public void playFlag() {
		this.play(R.raw.flag, 0);
	}
	
	public void playStart() {
		this.play(R.raw.start, 0);
	}
	
	public void playScan() {
		this.play(R.raw.scan, 0);
	}
	

}
