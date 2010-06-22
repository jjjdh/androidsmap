package com.sds.android.smap.map;

<<<<<<< .mine
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;
import com.sds.android.smap.R;

public class ContactItemizedOverlay extends SMapItemizedOverlay{
	
	private final int RESIZE = 100;
	
	// projection
	private String[] projection = {ContactsContract.CommonDataKinds.StructuredPostal.CONTACT_ID,
			ContactsContract.CommonDataKinds.StructuredPostal.STREET, 
			ContactsContract.CommonDataKinds.StructuredPostal.CITY,
			ContactsContract.CommonDataKinds.StructuredPostal.TYPE};
	
	
	
	public ContactItemizedOverlay(Activity caller,Drawable marker) {
		super(caller,boundCenterBottom(marker));
		
		Cursor cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI, 
				projection, 
				ContactsContract.CommonDataKinds.StructuredPostal.TYPE + "=" + ContactsContract.CommonDataKinds.StructuredPostal.TYPE_HOME,
				null, 
				null);
		
		Geocoder geocoder = new Geocoder(context);
		while(cursor.moveToNext()){
			try {
				// ���� �ڴ����� �ּ� ����
				Log.d("kepricon", getStreet(cursor) + " " + getContactID(cursor));
				Address address = geocoder.getFromLocationName(getStreet(cursor), 1).get(0);
				
				Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, getContactID(cursor));
				InputStream input =	ContactsContract.Contacts.openContactPhotoInputStream(context.getContentResolver(), uri);
				
				
				Bitmap bmp = null;
				if(input == null){
					bmp = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.contact);
				}else{
					bmp = BitmapFactory.decodeStream(input);
				}
				
				Bitmap resized = Bitmap.createScaledBitmap(bmp, RESIZE, RESIZE,true);
				BitmapDrawable photoMarker = new BitmapDrawable(resized);
				
				// �������� �߰� 
				addOverlay(address, photoMarker);
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void onIconTap(int index) {
		// TODO Auto-generated method stub
	}

	
	/**
	 * �ּҷϿ��� Street�� �ش��ϴ� ������ �����´�
	 * @param cursor
	 * @return
	 */
	public String getStreet(Cursor cursor){
		int streetColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.STREET);
		return cursor.getString(streetColumnIndex);
	}
	
	/**
	 * �ּҷ� ID�� �����´�
	 * @param cursor
	 * @return
	 */
	public long getContactID(Cursor cursor){
		int idColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.CONTACT_ID);
		return cursor.getLong(idColumnIndex);
	}
	
	
	/**
	 * �ش��ϴ� �ּ��� ��Ŀ�� �������̸� �����ϰ� 
	 * �������� ����Ʈ�� �߰��Ѵ�.
	 * @param address
	 * @param marker
	 */
	public void addOverlay(Address address, Drawable marker) {
		// TODO Auto-generated method stub
		GeoPoint point = new GeoPoint(
				(int)(address.getLatitude() * 1E6), 
				(int)(address.getLongitude() * 1E6));
		
		OverlayItem overlayitem = new OverlayItem(point, "", "");
		overlayitem.setMarker(boundCenterBottom(marker));
		super.addOverlay(overlayitem);
	}
	


}
