package com.sds.android.smap.geo;

import java.util.Random;

import com.google.android.maps.GeoPoint;

public class RandomGeoPoint {

	static GeoPoint currentPoint = null;
	
	static public GeoPoint getGeoPoint(){
		Random rand = new Random();
		if(currentPoint==null){
			int randomLat = rand.nextInt(80000000);
			int randomLng  = rand.nextInt(180000000);
			if(randomLat/2==0) randomLat*=-1;
			if(randomLng/2==0) randomLng*=-1;
			GeoPoint geo = new GeoPoint(randomLat, randomLng);
			return geo;
		}else{
			int randomLat = rand.nextInt(30000);
			int randomLng  = rand.nextInt(30000);
			if(randomLat%2==0) randomLat*=-1;
			if(randomLng%2==0) randomLng*=-1;
			int latitudeE6 = randomLat + currentPoint.getLatitudeE6();;
			int longitudeE6 = randomLng +currentPoint.getLongitudeE6();;
			GeoPoint geo = new GeoPoint(latitudeE6, longitudeE6);
			return geo;
		}
	}
	
	public static void setCurrentPoint(GeoPoint currentPoint) {
		RandomGeoPoint.currentPoint = currentPoint;
	}
}
