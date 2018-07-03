package com.example.elena.eden;

import android.Manifest;
import android.content.Context;
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
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.elena.eden.utilsCamara.ParamsConnection;
import com.example.elena.eden.utilsCamara.UserData;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.UserDataHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;

public class Home_camara extends AppCompatActivity implements View.OnClickListener {
    ImageView IMG_CONTAINER ;
    private int MEDIA_CODE = 100;
    private int CAMERA_CODE = 101;
    private String PATHIMAGE;
    private String ABSOULTE_PATH;
    private Context root;


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
                ElegirGaleria();
                break;
            }
            case R.id.boton_camara:{
               TomarFoto();
                break;
            }
            case R.id.boton_enviar:{
                try {
                    EnviarDatos();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            }

        }

    }

    private void EnviarDatos() throws FileNotFoundException {
        if ( IMG_CONTAINER.getDrawable() != null) {
            if (imageFilePath != null) {
                File file = new File(imageFilePath);
                RequestParams params = new RequestParams();
                params.put("img", file);
                AsyncHttpClient client = new AsyncHttpClient();
                if (UserData.ID != null) {
                    client.post(ParamsConnection.REST_USERIMG_POST + "/" + UserData.ID, params, new JsonHttpResponseHandler(){
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            try {

                                String path = response.getString("path");
                                if (path != null) {
                                    Intent profile = new Intent(root, Home_camara.class);
                                    root.startActivity(profile);
                                }
                            }catch(JSONException json){
                                Log.i("ERROR", json.getMessage());
                            }

                        }

                    });

                }
            }
        } else {
            Toast.makeText(this, "No se ha sacado una foto", Toast.LENGTH_LONG).show();
        }
    }
    private String imageFilePath;
    private File createImageFile() throws IOException {
        String timeStamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss",
                        Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        File storageDir =
                getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        imageFilePath = image.getAbsolutePath();
        return image;
    }
    private static final int REQUEST_CAPTURE_IMAGE = 100;

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

    private void TomarFoto() {

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

    private void ElegirGaleria() {
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
    private void loadImageCamera() {
        //File file = new File(imageFilePath);
        Bitmap img = BitmapFactory.decodeFile(imageFilePath);
        if(img != null) {
            IMG_CONTAINER.setImageBitmap(img);

        }

    }
}
