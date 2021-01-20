package com.example.sportseventv2;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mapbox.android.core.location.LocationEngine;
import com.mapbox.android.core.location.LocationEngineListener;
import com.mapbox.android.core.location.LocationEnginePriority;
import com.mapbox.android.core.location.LocationEngineProvider;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.api.directions.v5.DirectionsCriteria;
import com.mapbox.api.directions.v5.models.DirectionsResponse;
import com.mapbox.api.directions.v5.models.DirectionsRoute;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.plugins.locationlayer.LocationLayerPlugin;
import com.mapbox.mapboxsdk.plugins.locationlayer.modes.CameraMode;
import com.mapbox.mapboxsdk.plugins.locationlayer.modes.RenderMode;
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncher;
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncherOptions;
import com.mapbox.services.android.navigation.ui.v5.route.NavigationMapRoute;
import com.mapbox.services.android.navigation.v5.navigation.NavigationRoute;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MapboxMain extends TopBundMenu implements OnMapReadyCallback, LocationEngineListener,
        PermissionsListener, MapboxMap.OnMapClickListener {

    private MapView mapView;
    private MapboxMap map;
    private View startButton;
    private PermissionsManager permissionsManager;
    private LocationEngine locationEngine;
    private LocationLayerPlugin locationLayerPlugin;
    private Point originPosition;
    private Point destinationPosition;
    private Marker destinationMarker;
    private Marker originMarker;
    private Marker checkPointMarker1;
    private Marker checkPointMarker2;
    private Marker checkPointMarker3;
    private NavigationMapRoute navigationMapRoute;
    private DirectionsRoute currentRoute;
    private static final String TAG = "MapboxMain";
    private LatLng slut;
    private LatLng start;
    private LatLng chp1;
    private LatLng chp2;
    private LatLng chp3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.access_token));
        setContentView(R.layout.activity_mapbox_main);
        mapView = (MapView) findViewById(R.id.mapView);
        startButton = findViewById(R.id.startRute);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);


        getCoordinates();


        startButton.setOnClickListener(new View.OnClickListener() {
            //Hvis det ønskes at simulere en rute, ændres .shouldSimulateRoute til true
            @Override
            public void onClick(View v) {

               NavigationLauncherOptions options = NavigationLauncherOptions.builder()
                       .directionsRoute(currentRoute)
                       .shouldSimulateRoute(true)
                       .build();
                NavigationLauncher.startNavigation(MapboxMain.this, options);}

        });

        //initialiserer og tilknytter/tildeler variabler

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //sætter Løb som hjemmeskærm:
        bottomNavigationView.setSelectedItemId(R.id.løb);

        //Laver itemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.kalender:
                        startActivity(new Intent(getApplicationContext()
                                , Kalender.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.løb:
                        return true;

                    case R.id.profil:
                        startActivity(new Intent(getApplicationContext()
                                , Profil.class));
                        overridePendingTransition(0, 0);
                        return true;

                }
                return false;
            }
        });

    }

    /**
     * spørger om der er kommet startlong med intent.
     * @return
     */
    private boolean isCoordinates() {
        if (getIntent().hasExtra("startlong")) {
            return true;
        }
        return false;
    }

    /**
     * Modtager koordinater fra tilmeldtEvent klassen.
     * og laver de modtagede Strings om til Doubles.
     */
    private void getCoordinates() {
        if (getIntent().hasExtra("startlong")&&getIntent().hasExtra("startlad")&&getIntent().hasExtra("slutlong")&&getIntent().hasExtra("slutlad")
                &&getIntent().hasExtra("check1long")&&getIntent().hasExtra("check1lad")&&getIntent().hasExtra("check2long")&&getIntent().hasExtra("check2lad")
                &&getIntent().hasExtra("check3long")&&getIntent().hasExtra("check3lad")) {
            Log.d(TAG, "getCoordinates: ");
            Intent intent = getIntent();
            Double originLong = Double.parseDouble(intent.getStringExtra("startlong").replace(",", "."));
            Double originLad = Double.parseDouble(intent.getStringExtra("startlad").replace(",", "."));
            Double destinantionLong = Double.parseDouble(intent.getStringExtra("slutlong").replace(",", "."));
            Double destinantionLad = Double.parseDouble(intent.getStringExtra("slutlad").replace(",", "."));

            Double chp1Long = Double.parseDouble(intent.getStringExtra("check1long").replace(",", "."));
            Double chp1Lad = Double.parseDouble(intent.getStringExtra("check1lad").replace(",", "."));
            Double chp2Long = Double.parseDouble(intent.getStringExtra("check2long").replace(",", "."));
            Double chp2Lad = Double.parseDouble(intent.getStringExtra("check2lad").replace(",", "."));
            Double chp3Long = Double.parseDouble(intent.getStringExtra("check3long").replace(",", "."));
            Double chp3Lad = Double.parseDouble(intent.getStringExtra("check3lad").replace(",", "."));

            slut = new LatLng(destinantionLad, destinantionLong);
            start = new LatLng(originLad, originLong);

            chp1 = new LatLng(chp1Lad, chp1Long);
            chp2 = new LatLng(chp2Lad, chp2Long);
            chp3 = new LatLng(chp3Lad, chp3Long);
        }
    }

    @Override
    public void onMapReady(MapboxMap mapboxMap) {
        map = mapboxMap;
        map.addOnMapClickListener(this);
        enableLocation();
    }

    private void enableLocation(){ //Tjekker om der er givet tilladelse til location
        if(PermissionsManager.areLocationPermissionsGranted(this)){
            initializeLocationEngine();
            initializeLocationLayer();
        } else {
            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(this);
        }
    }
    @SuppressWarnings("MissingPermission")
    private void initializeLocationEngine() {
        locationEngine = new LocationEngineProvider(this).obtainBestLocationEngineAvailable();
        locationEngine.setPriority(LocationEnginePriority.HIGH_ACCURACY);
        locationEngine.activate();

        Location lastLocation = locationEngine.getLastLocation();
        if (lastLocation != null) {
            setCameraPosition(new LatLng(lastLocation));
        } else {
            locationEngine.addLocationEngineListener(this);
        }
    }
    @SuppressWarnings("MissingPermission")
    private void initializeLocationLayer() {
        locationLayerPlugin = new LocationLayerPlugin(mapView, map, locationEngine);
        locationLayerPlugin.setLocationLayerEnabled(true);
        locationLayerPlugin.setCameraMode(CameraMode.TRACKING);
        locationLayerPlugin.setRenderMode(RenderMode.COMPASS);
    }

    private void setCameraPosition(LatLng point){
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(point, 70.0));
    }

    @Override
    public void onMapClick(@NonNull LatLng point) {

        if(destinationMarker != null) {
            map.removeMarker(destinationMarker);
            map.removeMarker(originMarker);
            map.removeMarker(checkPointMarker1);
            map.removeMarker(checkPointMarker2);
            map.removeMarker(checkPointMarker3);
        }
        if (isCoordinates()){ //hvis der er et startlong med intent
            destinationMarker = map.addMarker(new MarkerOptions().position(slut));
            originMarker = map.addMarker(new MarkerOptions().position(start));

            checkPointMarker1 = map.addMarker(new MarkerOptions().position(chp1).title("Checkpoint 1"));
            checkPointMarker2 = map.addMarker(new MarkerOptions().position(chp2).title("Checkpoint 2"));
            checkPointMarker3 = map.addMarker(new MarkerOptions().position(chp3).title("Checkpoint 3"));

            destinationPosition = Point.fromLngLat(slut.getLongitude(), slut.getLatitude());
            originPosition = Point.fromLngLat(start.getLongitude(), start.getLatitude());
            Point checkPoint1 = Point.fromLngLat(chp1.getLongitude(), chp1.getLatitude());
            Point checkPoint2 = Point.fromLngLat(chp2.getLongitude(), chp2.getLatitude());
            Point checkPoint3 = Point.fromLngLat(chp3.getLongitude(), chp3.getLatitude());

            getRoute(originPosition, checkPoint1, checkPoint2, checkPoint3, destinationPosition);

            startButton.setEnabled(true);
            startButton.setBackgroundResource(R.color.mapboxBlue);
        }
    }

    private void getRoute(Point origin, Point check1, Point check2, Point check3, Point destination) {
        NavigationRoute.builder()
                .origin(origin)
                .addWaypoint(check1)
                .addWaypoint(check2)
                .addWaypoint(check3)
                .destination(destination)
                .profile(DirectionsCriteria.PROFILE_CYCLING)
                .accessToken(Mapbox.getAccessToken())
                .build()
                .getRoute(new Callback<DirectionsResponse>() {
                    @Override
                    public void onResponse(Call<DirectionsResponse> call, Response<DirectionsResponse> response) {
                        if (response.body() == null) {
                            Log.e(TAG, "Ingen ruter fundet, se efter om du er tilmeldt!");
                            return;
                        }else if (response.body().routes().size() == 0) {
                            Log.e(TAG, "Ingen ruter fundet!");
                            return;
                        }

                        currentRoute = response.body().routes().get(0);

                        if (navigationMapRoute != null) {
                            navigationMapRoute.removeRoute();
                        }else {
                            navigationMapRoute = new NavigationMapRoute(null, mapView, map);
                        }
                        navigationMapRoute.addRoute(currentRoute);
                    }

                    @Override
                    public void onFailure(Call<DirectionsResponse> call, Throwable t) {
                        Log.e(TAG, "Error:" + t.getMessage());
                    }
                });
        setCameraPosition(start);
    }


    @Override
    @SuppressWarnings("MissingPermission")
    public void onConnected() {
        locationEngine.requestLocationUpdates();
    }

    @Override
    public void onLocationChanged(Location location) {
        if(location != null) {
            setCameraPosition(new LatLng(location));
        }
    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
        Toast.makeText(MapboxMain.this, "Prøv igen", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPermissionResult(boolean granted) { //Sender tilbage til enableLocation når location er tilladt
        if(granted){
            enableLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    @SuppressWarnings("MissingPermission")
    protected void onStart() {
        super.onStart();
        if(locationEngine != null) {
            locationEngine.requestLocationUpdates();
        }
        if(locationLayerPlugin != null) {
            locationLayerPlugin.onStart();
        }
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(locationEngine != null) {
            locationEngine.removeLocationUpdates();
        }
        if(locationLayerPlugin != null) {
            locationLayerPlugin.onStop();
        }
        mapView.onStop();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationEngine != null) {
            locationEngine.deactivate();
        }
        mapView.onDestroy();
    }
}