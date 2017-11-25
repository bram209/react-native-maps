package com.airbnb.android.react.maps;

import android.content.Context;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.data.geojson.GeoJsonLayer;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AirMapGeoJson extends AirMapFeature {
  private GeoJsonLayer layer;
  private JSONObject geoJson;
  private float strokeWidth = -1;

  public AirMapGeoJson(Context context) {
    super(context);
  }

  public void setGeoJson(String geoJson) {
    try {
      this.geoJson = new JSONObject(geoJson);
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  @Override public void addToMap(GoogleMap map) {
    layer = new GeoJsonLayer(map, geoJson);
    layer.addLayerToMap();

    if (strokeWidth != -1) {
      layer.getDefaultPolygonStyle().setStrokeWidth(strokeWidth);
    }
  }

  @Override public void removeFromMap(GoogleMap map) {
    if (layer != null) {
      layer.removeLayerFromMap();
    }
  }

  @Override public Object getFeature() {
    return layer;
  }

  public void setStrokeWidth(float strokeWidth) {
    if (layer != null) {
      layer.getDefaultPolygonStyle().setStrokeWidth(strokeWidth);
    }
    this.strokeWidth = strokeWidth;
  }
}
