package main.java.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class ModelService {
	List<Track> trks;
	static final int MAX_TRKS = 1000;
	static final int MAX_HT = 100000;
	static final long MAX_RANGE = 500000;
	static final long MIN_RANGE = 100;
	static final long MAX_VEL = 10000;
	static final long MIN_VEL = 0;
	
	public ModelService() {
		trks = new ArrayList<Track>();
		Random rand = new Random(1);
		for (int i = 0; i < MAX_TRKS; i++) {
			double z =  (rand.nextDouble() * MAX_HT);				
			double x;
			double y;
			do {
				x =  (((rand.nextDouble() * (MAX_RANGE - MIN_RANGE)+ MIN_RANGE)*((rand.nextDouble() >= 0.5)?1.0:-1.0)));
				y =  (((rand.nextDouble() * (MAX_RANGE - MIN_RANGE)+ MIN_RANGE)*((rand.nextDouble() >= 0.5)?1.0:-1.0)));
			} while(Math.sqrt(x*x+y*y)>MAX_RANGE);
			double dx;
			double dy;
			double dz;
			do {
				dx =  (((rand.nextDouble() * (MAX_VEL - MIN_VEL)+ MIN_VEL)*((rand.nextDouble() >= 0.5)?1.0:-1.0)));
				dy =  (((rand.nextDouble() * (MAX_VEL - MIN_VEL)+ MIN_VEL)*((rand.nextDouble() >= 0.5)?1.0:-1.0)));
				dz =  (((rand.nextDouble() * (MAX_VEL - MIN_VEL)+ MIN_VEL)*((rand.nextDouble() >= 0.5)?1.0:-1.0)));
			} while(Math.sqrt(dx*dx+dy*dy+dz*dz)>MAX_VEL || Math.sqrt(dx*dx+dy*dy+dz*dz)<MIN_VEL);
			int signal = (int)(rand.nextDouble()*1000);
			String name = ""+i;
			int id = i;
			Track trk = new Track();
			trk.setId(id);
			trk.setSecondaryCode(name);
			trk.setEmetres((long)x);
			trk.setNmetres((long)y);
			trk.setHmetres((long)z);
			trk.setDemetrespersecond((long)dx);
			trk.setDnmetrespersecond((long)dy);
			trk.setDhmetrespersecond((long)dz);
			trk.setSignalstrengthdDm(signal);
			trks.add(trk);
		}
	}
	static Date dold = new Date();
	static boolean firstTime = true;
	public Tracks getTracks() {
		Date dnew = new Date();
		double deltat = (dnew.getTime() - dold.getTime())/1000.0;
		if (firstTime) {
			deltat = 0.0;
			firstTime=false;
		}
		Tracks ts = new Tracks();
		for(Track track:trks) {
			track.setNmetres((long)(track.getNmetres()+track.getDnmetrespersecond()*deltat));
			track.setEmetres((long)(track.getEmetres()+track.getDemetrespersecond()*deltat));
			long h = (long)(track.getHmetres()+track.getDhmetrespersecond()*deltat);
			h = (h > 0)? h: 0L;
			track.setHmetres(h);
		}
		dold = dnew;
		ts.setTracks(trks);
		return ts;
	}



}
