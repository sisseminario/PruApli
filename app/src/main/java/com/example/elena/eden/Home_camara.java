package com.example.elena.eden;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.elena.eden.DATA.DataApp;
import com.example.elena.eden.DATA.UserData;
import com.example.elena.eden.ItemMenu.ItemMenuStructure;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class Home_camara extends AppCompatActivity implements View.OnClickListener {
    ImageView IMG_CONTAINER;
    private final String CARPETA_RAIZ = "misImagenesPrueba/";
    private final String RUTA_IMAGEN = CARPETA_RAIZ + "misFotos";
    private int MEDIA_CODE = 100;
    private int CAMERA_CODE = 101;
    private Context root;
    private String imageFilePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        root = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_camara);
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        validaPermisos();

        checkPermissionForCameraAndStorage();
        LoadComponents();
    }

    private boolean validaPermisos() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }

        if ((checkSelfPermission(CAMERA) == PackageManager.PERMISSION_GRANTED) &&
                (checkSelfPermission(WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
            return true;
        }

        requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, CAMERA}, 100);


        return false;

    }

    private void checkPermissionForCameraAndStorage() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
            return;

        }
        if (this.checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED || this.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED || this.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            return;
        } else {
            this.requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 99);
        }
        return;
    }

    private void LoadComponents() {
        Button media = (Button) this.findViewById(R.id.boton_galeria);
        Button photo = (Button) this.findViewById(R.id.boton_camara);
        Button upload = (Button) this.findViewById(R.id.boton_enviar);
        IMG_CONTAINER = (ImageView) this.findViewById(R.id.img_camara);
        media.setOnClickListener(this);
        photo.setOnClickListener(this);
        upload.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.boton_galeria) {
            LoadMediaData();
        }
        if (v.getId() == R.id.boton_camara) {
            //tomarFotografia();
            openCameraIntent();
        }
        if (v.getId() == R.id.boton_enviar) {
            try {
                sendPhoto();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

    }

    private void sendPhoto() throws FileNotFoundException {

        if (IMG_CONTAINER.getDrawable() != null) {
            Toast.makeText(root, "container OK", Toast.LENGTH_LONG).show();
            if (imageFilePath != null) {
                File file = new File(imageFilePath);
                RequestParams params = new RequestParams();
                params.put("img", file);
                AsyncHttpClient client = new AsyncHttpClient();
                if (UserData.ID != null) {
                    client.post(DataApp.REST_USERIMG_POST + "/" + UserData.ID, params, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            try {
                                String path = response.getString("path");
                                UserData.PHOTOURL = path;
                                if (path != null) {
                                   /*Intent profile = new Intent(root, Profile.class);
                                    root.startActivity(profile);*/
                                    Intent volver_inicio = new Intent(Home_camara.this, Home_mapa.class);
                                    root.startActivity(volver_inicio);
                                    Toast.makeText(root, "Propiedad Registrada con exito", Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException json) {
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

    private void LoadMediaData() {
        Intent media = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        media.setType("image/");
        startActivityForResult(media.createChooser(media, "Escoja la Aplicacion"), MEDIA_CODE);
    }

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

    private void openCameraIntent() {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = createFile();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri fileuri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", file);
            camera.putExtra(MediaStore.EXTRA_OUTPUT, fileuri);
        } else {
            camera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        }
        startActivityForResult(camera, CAMERA_CODE);
    }

    private File createFile() {
        //Logica de creado
        File file = new File(Environment.getExternalStorageDirectory(), RUTA_IMAGEN);
        if (!file.exists()) {
            file.mkdirs();
        }
        //generar el nombre
        String name = "";
        if (file.exists()) {
            name = "IMG_" + System.currentTimeMillis() / 1000 + ".jpg";
        }
        imageFilePath = file.getAbsolutePath() + File.separator + name;
        File fileimg = new File(imageFilePath);
        return fileimg;
    }

    public static String getRealPathFromURI(Context context, Uri contentURI) {
        String result = null;
        Cursor cursor = context.getContentResolver().query(contentURI,
                null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int idx = cursor
                    .getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == MEDIA_CODE) {
            IMG_CONTAINER.setImageURI(data.getData());

            imageFilePath = getRealPathFromURI(this,data.getData()) ;///en esta linea ...!

        }
        if(requestCode == CAMERA_CODE) {

            //loadImageCamera();
            //Bitmap img = (Bitmap)data.getExtras().get("data");
            //IMG_CONTAINER.setImageBitmap(img);

            loadImageCamera();
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