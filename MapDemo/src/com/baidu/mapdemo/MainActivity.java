package com.baidu.mapdemo;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BaiduMap.OnMarkerDragListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.InfoWindow.OnInfoWindowClickListener;

public class MainActivity extends Activity implements OnClickListener{
    MapView mMapView = null;
    BaiduMap mBaiduMap = null;
    Button clear,reset;
    Marker markerA,markerB,markerC,markerD;
	private InfoWindow mInfoWindow;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//��ʹ��SDK�����֮ǰ��ʼ��context��Ϣ������ApplicationContext  
        //ע��÷���Ҫ��setContentView����֮ǰʵ��  

		SDKInitializer.initialize(getApplicationContext());
		setContentView(R.layout.activity_main);
		intitView();
		//mMapView = (MapView)findViewById(R.id.bmapView);
	}
	
	public void intitView(){
		mMapView = (MapView)findViewById(R.id.bmapView);
		clear = (Button)findViewById(R.id.clear);
		reset = (Button)findViewById(R.id.reset);
		clear.setOnClickListener(MainActivity.this);
		reset.setOnClickListener(MainActivity.this);
		
		mBaiduMap = mMapView.getMap();
		//��ͨ��ͼ  
		mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);  
		//���ǵ�ͼ  
		//mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
		//������ͨͼ   
		//mBaiduMap.setTrafficEnabled(true);
		//��������ͼ
	//	mBaiduMap.setBaiduHeatMapEnabled(true);
		
	}
	
	 @Override  
	    protected void onDestroy() {  
	        super.onDestroy();  
	        //��activityִ��onDestroyʱִ��mMapView.onDestroy()��ʵ�ֵ�ͼ�������ڹ���  
	        mMapView.onDestroy();  
	    }  
	    @Override  
	    protected void onResume() {  
	        super.onResume();  
	        //��activityִ��onResumeʱִ��mMapView. onResume ()��ʵ�ֵ�ͼ�������ڹ���  
	        mMapView.onResume();  
	        }  
	    @Override  
	    protected void onPause() {  
	        super.onPause();  
	        //��activityִ��onPauseʱִ��mMapView. onPause ()��ʵ�ֵ�ͼ�������ڹ���  
	        mMapView.onPause();  
	        }

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.clear:
				delBiaozhiWu();
				Toast.makeText(MainActivity.this, "qingchu", Toast.LENGTH_SHORT).show();
				break;
			case R.id.reset:
				 addBiaozhiWu();
				Toast.makeText(MainActivity.this, "reset", Toast.LENGTH_SHORT).show();
				break;
			
			}
		}  
		
		public void delBiaozhiWu() {
			mBaiduMap.clear();//������и�����
			
		}

		public void addBiaozhiWu(){
			//����Maker����� ,��γ�� 
			LatLng llA = new LatLng(39.963175, 116.400244); 
			LatLng llB = new LatLng(39.942821, 116.369199);
			LatLng llC = new LatLng(39.939723, 116.425541);
			LatLng llD = new LatLng(39.952356,116.385684);
			//����Markerͼ��  
			BitmapDescriptor bitmapa = BitmapDescriptorFactory  //���ܷ����ʼ���У���Ӱ���ʼ��
			    .fromResource(R.drawable.icon_marka);  
			BitmapDescriptor bitmapb = BitmapDescriptorFactory  
				    .fromResource(R.drawable.icon_markb); 
			BitmapDescriptor bitmapc = BitmapDescriptorFactory  
				    .fromResource(R.drawable.icon_markc);
			//����MarkerOption�������ڵ�ͼ�����Marker  
			OverlayOptions optionA = new MarkerOptions()  
			    .position(llA)  
			    .icon(bitmapa)
			    .zIndex(9)
			    .draggable(true);//
			ArrayList<BitmapDescriptor> giflist = new ArrayList<BitmapDescriptor>();
			giflist.add(bitmapa);
			giflist.add(bitmapb);
			giflist.add(bitmapc);
			OverlayOptions ooD = new MarkerOptions().position(llD).icons(giflist)
							.zIndex(0).period(10);//���ö���	
			markerD = (Marker) (mBaiduMap.addOverlay(ooD));   
			//�ڵ�ͼ�����Marker������ʾ  
	    	markerA=(Marker)mBaiduMap.addOverlay(optionA);
	    	
	    	OverlayOptions optionB = new MarkerOptions().position(llB).icon(bitmapb).zIndex(5).draggable(true);
	    	markerB=(Marker)mBaiduMap.addOverlay(optionB);
	    	
	    	OverlayOptions optionC = new MarkerOptions().position(llC).icon(bitmapc).zIndex(5).draggable(true);
	    	markerC=(Marker)mBaiduMap.addOverlay(optionC);
	    	
			//����BaiduMap�����setOnMarkerDragListener��������marker��ק�ļ���,���еĶ�����ʹ��
			mBaiduMap.setOnMarkerDragListener(new OnMarkerDragListener() {
			    public void onMarkerDrag(Marker marker) {
			        //��ק��
			    }
			    public void onMarkerDragEnd( Marker marker) {
			        //��ק����
			    	Toast.makeText(
							MainActivity.this,
							"��ק��������λ�ã�" + marker.getPosition().latitude + ", "
									+ marker.getPosition().longitude,
							Toast.LENGTH_LONG).show();
			    //	marker.setPosition(new LatLng(marker.getPosition().latitude, marker.getPosition().longitude));
			    }
			    public void onMarkerDragStart(Marker marker) {
			        //��ʼ��ק
			    }
			});
			
		mBaiduMap.setOnMarkerClickListener(new OnMarkerClickListener() {
			
			
			public boolean onMarkerClick(final Marker marker) {//��marker����Ϊfinal�Ĳ�Ȼ���治��ʹ��marker
				Button button = new Button(getApplicationContext());
				button.setBackgroundResource(R.drawable.popup);
				OnInfoWindowClickListener listener = null;
				if (marker == markerA ) {
					button.setText("����λ��");
					listener = new OnInfoWindowClickListener() {
						public void onInfoWindowClick() {
							LatLng ll = marker.getPosition();
							LatLng llNew = new LatLng(ll.latitude + 0.005,
									ll.longitude + 0.005);
							marker.setPosition(llNew);
							mBaiduMap.hideInfoWindow();
						}
					};
					LatLng ll = marker.getPosition();
					mInfoWindow = new InfoWindow(BitmapDescriptorFactory.fromView(button), ll, -47, listener);
					mBaiduMap.showInfoWindow(mInfoWindow);
				}//else if (marker == markerB) {
//					button.setText("����ͼ��");
//					button.setOnClickListener(new OnClickListener() {
//						public void onClick(View v) {
//							marker.setIcon();
//							mBaiduMap.hideInfoWindow();
//						}
//					});
//					LatLng ll = marker.getPosition();
//					mInfoWindow = new InfoWindow(button, ll, -47);
//					mBaiduMap.showInfoWindow(mInfoWindow);
//				} else if (marker == mMarkerC) {
//					button.setText("ɾ��");
//					button.setOnClickListener(new OnClickListener() {
//						public void onClick(View v) {
//							marker.remove();
//							mBaiduMap.hideInfoWindow();
//						}
//					});
//					LatLng ll = marker.getPosition();
//					mInfoWindow = new InfoWindow(button, ll, -47);
//					mBaiduMap.showInfoWindow(mInfoWindow);
				return false;
			
	}	
		
		 });	
//		
		
	
			
}
}
