package com.example.getlocationapplication;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;
import java.util.concurrent.Executor;

public class DatabaseReader {

    static FirebaseFirestore db = FirebaseFirestore.getInstance();

    public static void getLocation(final OnCompleteListener<LatLng> end) {
        db.collection("location_collection")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> data = document.getData();
                                double latitude = (double) data.get("latitude");
                                double longitude = (double) data.get("longitude");
                                final LatLng location = new LatLng(latitude, longitude);
                                Task<LatLng> endTask = new Task<LatLng>() {
                                    @Override
                                    public boolean isComplete() {
                                        return true;
                                    }

                                    @Override
                                    public boolean isSuccessful() {
                                        return true;
                                    }

                                    @Override
                                    public boolean isCanceled() {
                                        return false;
                                    }

                                    @Nullable
                                    @Override
                                    public LatLng getResult() {
                                        return location;
                                    }

                                    @Nullable
                                    @Override
                                    public <X extends Throwable> LatLng getResult(@NonNull Class<X> aClass) throws X {
                                        return null;
                                    }

                                    @Nullable
                                    @Override
                                    public Exception getException() {
                                        return null;
                                    }

                                    @NonNull
                                    @Override
                                    public Task<LatLng> addOnSuccessListener(@NonNull OnSuccessListener<? super LatLng> onSuccessListener) {
                                        return null;
                                    }

                                    @NonNull
                                    @Override
                                    public Task<LatLng> addOnSuccessListener(@NonNull Executor executor, @NonNull OnSuccessListener<? super LatLng> onSuccessListener) {
                                        return null;
                                    }

                                    @NonNull
                                    @Override
                                    public Task<LatLng> addOnSuccessListener(@NonNull Activity activity, @NonNull OnSuccessListener<? super LatLng> onSuccessListener) {
                                        return null;
                                    }

                                    @NonNull
                                    @Override
                                    public Task<LatLng> addOnFailureListener(@NonNull OnFailureListener onFailureListener) {
                                        return null;
                                    }

                                    @NonNull
                                    @Override
                                    public Task<LatLng> addOnFailureListener(@NonNull Executor executor, @NonNull OnFailureListener onFailureListener) {
                                        return null;
                                    }

                                    @NonNull
                                    @Override
                                    public Task<LatLng> addOnFailureListener(@NonNull Activity activity, @NonNull OnFailureListener onFailureListener) {
                                        return null;
                                    }
                                };
                                end.onComplete(endTask);
                                //Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            //Log.w(TAG, "Error getting documents.", task.getException());
                            System.out.println(task.getException());
                        }
                    }
                });
    }

}
