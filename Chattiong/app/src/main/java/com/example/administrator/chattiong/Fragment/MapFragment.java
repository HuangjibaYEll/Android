package com.example.administrator.chattiong.Fragment;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.databinding.library.baseAdapters.BR;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.example.administrator.chattiong.Bean.News;
import com.example.administrator.chattiong.R;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment {


    private TextureMapView map;
    private BaiduMap baiduMap;
    private List<News> list;


    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        map = (TextureMapView) view.findViewById(R.id.mapView);
        baiduMap = map.getMap();
        list = DataSupport.findAll(News.class);
        for (News news : list) {
            addMark(news);
        }
        //设定中心点坐标
        LatLng cenpt = new LatLng(30.663791, 104.07281);
        //定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(cenpt)
                .zoom(3)
                .build();
        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        baiduMap.setMapStatus(mMapStatusUpdate);


        baiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Bundle bundle = marker.getExtraInfo();
                News newsData = (News) bundle.getSerializable("news_data");

                //设定中心点坐标
                LatLng center = new LatLng(newsData.getLatitude(), newsData.getLongitude());
                MapStatus mMapStatus = new MapStatus.Builder().target(center).zoom(15).build(); //最高18 越大标尺距离越小
                MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
                baiduMap.setMapStatus(mMapStatusUpdate);


//                LatLng latLng = marker.getPosition();
//                Point point = baiduMap.getProjection().toScreenLocation(latLng);
//                point.y = 230;
//                LatLng lng = baiduMap.getProjection().fromScreenLocation(point);
                LatLng lng = new LatLng(newsData.getLatitude() + 0.00434, newsData.getLongitude() + 0.00015);
                ViewDataBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_map_news, container, false);
                ImageView close = ((ImageView) binding.getRoot().findViewById(R.id.ivClose));
                binding.setVariable(BR.News, newsData);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        baiduMap.hideInfoWindow();
                    }
                });

                InfoWindow infoWindow = new InfoWindow(binding.getRoot(), lng, 0);
                baiduMap.showInfoWindow(infoWindow);
                return true;
            }
        });

        return view;
    }


    private void addMark(News news) {

        //坐标
        LatLng point = new LatLng(news.getLatitude(), news.getLongitude());
        //图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.mipmap.lalo);
        //坐标对象
        MarkerOptions options = new MarkerOptions()
                .position(point).icon(bitmap);
        baiduMap.addOverlay(options);
        Marker marker = (Marker) baiduMap.addOverlay(options);
        Bundle bundle = new Bundle();
        bundle.putSerializable("news_data", news);
        marker.setExtraInfo(bundle);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        map.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        map.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        map.onPause();
    }

}
