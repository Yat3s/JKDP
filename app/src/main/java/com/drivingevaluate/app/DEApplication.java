package com.drivingevaluate.app;

import android.app.Application;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.testin.agent.TestinAgent;

public class DEApplication extends Application {
    public static DEApplication mInstance;
    public LocationClient blocation;
    public LatLng myLl ;
    public String myAddr = "";
    public String cityCode = "";
    @Override
    public void onCreate() {
        super.onCreate();
        init();
        mInstance = this;
    }

    private void init() {
        initBaidu();
        initImageLoader();
        TestinAgent.init(this, "b5901b96b9b368ed5353139ee7ab5932", "WeTesting");
    }

    private void initImageLoader() {
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                .createDefault(this);
        ImageLoader.getInstance().init(configuration);
    }

    public static DEApplication getInstance() {
        return mInstance;
    }

    /**
     * 初始化百度相关sdk initBaidumap
     * @Title: initBaidumap
     * @Description: TODO
     * @param
     * @return void
     * @throws
     */
    private void initBaidu() {
        // 初始化地图Sdk
        SDKInitializer.initialize(this);
        // 初始化定位sdk
        initBaiduLocClient();
    }

    /**
     * 定位
     */
    public void initBaiduLocClient() {
        blocation = new LocationClient(this.getApplicationContext());
        blocation.registerLocationListener(new BDLocationListener() {

            @Override
            public void onReceiveLocation(BDLocation location) {
                if (location == null)
                    return;
                myLl = new LatLng(location.getLatitude(), location.getLongitude());
                myAddr = location.getAddrStr();
                cityCode = location.getCityCode();
            }
        });
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setIsNeedAddress(true);// 设置地址信息，默认无地址信息
        option.setAddrType("all");
        option.setScanSpan(500); // 设置定位模式，小于1秒则一次定位;大于等于1秒则定时定位
        blocation.setLocOption(option);
        blocation.start();
    }
}
