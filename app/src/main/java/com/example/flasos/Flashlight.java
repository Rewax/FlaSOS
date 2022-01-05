package com.example.flasos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Flashlight extends AppCompatActivity {

    private Button flashOnBtn;
    private Button flashOffBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashlight);

        flashOnBtn = findViewById(R.id.flashOnBtn);
        flashOffBtn = findViewById(R.id.flashOffBtn);

        flashOnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flashSwitch(true);
            }
        });

        flashOffBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flashSwitch(false);
            }
        });
    }

    private void flashSwitch(boolean input) {
        if (this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                CameraManager camManager = (CameraManager) getSystemService(CAMERA_SERVICE);
                String cameraID = null;
                try {
                    cameraID = camManager.getCameraIdList()[0];
                    camManager.setTorchMode(cameraID, input);
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }

        } else {
            Toast.makeText(this, "No flash light found", Toast.LENGTH_SHORT).show();
        }
    }
}