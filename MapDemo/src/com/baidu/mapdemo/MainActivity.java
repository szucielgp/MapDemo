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
		//在使用SDK各组件之前初始化context信息，传入ApplicationContext  
        //注意该方法要再setContentView方法之前实现  

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
		//普通地图  
		mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);  
		//卫星地图  
		//mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
		//开启交通图   
		//mBaiduMap.setTrafficEnabled(true);
		//开启热力图
	//	mBaiduMap.setBaiduHeatMapEnabled(true);
		
	}
	
	 @Override  
	    protected void onDestroy() {  
	        super.onDestroy();  
	        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理  
	        mMapView.onDestroy();  
	    }  
	    @Override  
	    protected void onResume() {  
	        super.onResume();  
	        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理  
	        mMapView.onResume();  
	        }  
	    @Override  
	    protected void onPause() {  
	        super.onPause();  
	        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理  
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
			mBaiduMap.clear();//清除所有覆盖物
			
		}

		public void addBiaozhiWu(){
			//定义Maker坐标点 ,经纬度 
			LatLng llA = new LatLng(39.963175, 116.400244); 
			LatLng llB = new LatLng(39.942821, 116.369199);
			LatLng llC = new LatLng(39.939723, 116.425541);
			LatLng llD = new LatLng(39.952356,116.385684);
			//构建Marker图标  
			BitmapDescriptor bitmapa = BitmapDescriptorFactory  //不能放在最开始进行，会影响初始化
			    .fromResource(R.drawable.icon_marka);  
			BitmapDescriptor bitmapb = BitmapDescriptorFactory  
				    .fromResource(R.drawable.icon_markb); 
			BitmapDescriptor bitmapc = BitmapDescriptorFactory  
				    .fromResource(R.drawable.icon_markc);
			//构建MarkerOption，用于在地图上添加Marker  
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
							.zIndex(0).period(10);//设置动画	
			markerD = (Marker) (mBaiduMap.addOverlay(ooD));   
			//在地图上添加Marker，并显示  
	    	markerA=(Marker)mBaiduMap.addOverlay(optionA);
	    	
	    	OverlayOptions optionB = new MarkerOptions().position(llB).icon(bitmapb).zIndex(5).draggable(true);
	    	markerB=(Marker)mBaiduMap.addOverlay(optionB);
	    	
	    	OverlayOptions optionC = new MarkerOptions().position(llC).icon(bitmapc).zIndex(5).draggable(true);
	    	markerC=(Marker)mBaiduMap.addOverlay(optionC);
	    	
			//调用BaiduMap对象的setOnMarkerDragListener方法设置marker拖拽的监听,所有的都可以使用
			mBaiduMap.setOnMarkerDragListener(new OnMarkerDragListener() {
			    public void onMarkerDrag(Marker marker) {
			        //拖拽中
			    }
			    public void onMarkerDragEnd( Marker marker) {
			        //拖拽结束
			    	Toast.makeText(
							MainActivity.this,
							"拖拽结束，新位置：" + marker.getPosition().latitude + ", "
									+ marker.getPosition().longitude,
							Toast.LENGTH_LONG).show();
			    //	marker.setPosition(new LatLng(marker.getPosition().latitude, marker.getPosition().longitude));
			    }
			    public void onMarkerDragStart(Marker marker) {
			        //开始拖拽
			    }
			});
			
		mBaiduMap.setOnMarkerClickListener(new OnMarkerClickListener() {
			
			
			public boolean onMarkerClick(final Marker marker) {//将marker定义为final的不然下面不能使用marker
				Button button = new Button(getApplicationContext());
				button.setBackgroundResource(R.drawable.popup);
				OnInfoWindowClickListener listener = null;
				if (marker == markerA ) {
					button.setText("更改位置");
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
//					button.setText("更改图标");
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
//					button.setText("删除");
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
