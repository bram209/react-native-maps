package com.airbnb.android.react.maps;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;

public class AirMapGeoJsonManager extends ViewGroupManager<AirMapGeoJson> {

  private final DisplayMetrics metrics;

  public AirMapGeoJsonManager(ReactApplicationContext reactContext) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
      metrics = new DisplayMetrics();
      ((WindowManager) reactContext.getSystemService(Context.WINDOW_SERVICE))
          .getDefaultDisplay()
          .getRealMetrics(metrics);
    } else {
      metrics = reactContext.getResources().getDisplayMetrics();
    }
  }

  @Override public String getName() {
    return "AIRMapGeoJson";
  }

  @Override protected AirMapGeoJson createViewInstance(ThemedReactContext reactContext) {
    return new AirMapGeoJson(reactContext);
  }

  @ReactProp(name = "raw")
  public void setGeoJson(AirMapGeoJson view, String geoJson) {
    view.setGeoJson(geoJson);
  }

  @ReactProp(name = "strokeWidth", defaultFloat = 1f)
  public void setStrokeWidth(AirMapGeoJson view, float widthInPoints) {
    float widthInScreenPx = metrics.density * widthInPoints; // done for parity with iOS
    view.setStrokeWidth(widthInScreenPx);
  }

//  @ReactProp(name = "fillColor", defaultInt = Color.RED, customType = "Color")
//  public void setFillColor(AirMapGeoJson view, int color) {
//    view.setFillColor(color);
//  }
//
//  @ReactProp(name = "strokeColor", defaultInt = Color.RED, customType = "Color")
//  public void setStrokeColor(AirMapGeoJson view, int color) {
//    view.setStrokeColor(color);
//  }

}
