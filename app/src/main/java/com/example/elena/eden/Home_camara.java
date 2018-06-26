package com.example.elena.eden;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import java.io.File;
import java.io.FileNotFoundException;

public class Home_camara extends AppCompatActivity implements View.OnClickListener {
    ImageView IMG_CONTAINER ;
    private int MEDIA_CODE = 100;
    private int CAMERA_CODE = 101;
    private String PATHIMAGE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_camara);

        checkPermissionForCameraAndStorage();
        LoadComponents();
    }

    private void checkPermissionForCameraAndStorage() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M){
            return;

        }
        if (this.checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED || this.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED || this.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){
            return;
        }else {
            this.requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 99);
        }
        return;
    }

    private void LoadComponents() {
        Button media = (Button)this.findViewById(R.id.boton_galeria);
        Button photo = (Button)this.findViewById(R.id.boton_camara);
        Button upload = (Button)this.findViewById(R.id.boton_enviar);
        IMG_CONTAINER = (ImageView)this.findViewById(R.id.img_camara);
        media.setOnClickListener(this);
        photo.setOnClickListener(this);
        upload.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.boton_galeria:{
                LoadMediaData();
                break;
            }
            case R.id.boton_camara:{
                LoadCameraData();
                break;
            }
            case R.id.boton_enviar:{
                try {
                    SendData();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            }

        }

    }

    private void SendData() throws FileNotFoundException{
        if (PATHIMAGE != null){
            AsyncHttpClient client = new AsyncHttpClient();
            File file = new File(PATHIMAGE);
            RequestParams params = new  RequestParams();
            params.put("IMAGE", file);
            //client.post("");
        }else {
            Toast.makeText(this,"seleccione una imagen primero", Toast.LENGTH_LONG).show();
        }
        return;

    }

    private File createFile(){
        File file = new File(Environment.getExternalStorageDirectory(),"image/misimagenes");
        if(!file.exists()){
            file.mkdirs();
        }
        String name="";
        if (file.exists()){
            name= "IMG.jpg" + System.currentTimeMillis()/1000 + "jpg";
        }
        PATHIMAGE = file.getAbsolutePath() + File.separator +name;
        File newfile =new File(PATHIMAGE);
        return newfile;
    }

    private void LoadCameraData() {

        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File file = createFile();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            FileProvider.getUriForFile(this, "com.example.elena.eden.provider", file);
            Uri uri =null;

        }else {
            camera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        }
        this.startActivityForResult(camera, CAMERA_CODE);
    }

    private void LoadMediaData() {
        Intent media =new Intent (Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        media.setType("image/");
        this.startActivityForResult(media.createChooser(media, "la aplicacion"), MEDIA_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MEDIA_CODE){
            if (data !=null){
                Uri img = data.getData();
                IMG_CONTAINER.setImageURI(img);
            }

        }
        if(requestCode == CAMERA_CODE){
            if (data !=null) {
                //Bundle information = data.getExtras();
                ///Bitmap img = (Bitmap) information.get("data");
                //IMG_CONTAINER.setImageBitmap(img);

                Bitmap img = BitmapFactory.decodeFile(PATHIMAGE);
                IMG_CONTAINER.setImageBitmap(img);
            }
        }
    }
}
