package com.app.presupuestosuniajc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.app.presupuestosuniajc.R.id.vista_foto;

public class ActivityImagen extends Activity {
    static final int REQUEST_IMAGE_CAPTURE  = 1;//Request to camera
    static final int REQUEST_TAKE_PHOTO     = 1;
    private String mCurrentPhotoPath        = null;
    String img                              = "0";
    Context context                         = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagen);
    }

    public void mostrarMensaje(String mensaje){
        Toast.makeText(context,mensaje,Toast.LENGTH_SHORT).show();
    }

    public ActivityImagen(Context context) {
        this.context = context;
    }

    //Metodos de fotos
    public File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "PPTO_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES+"/PRESUPUESTOS/");
        mostrarMensaje(storageDir.getAbsolutePath());
        Log.i("URLFOTO",storageDir.getAbsolutePath());
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    public String dispatchTakePictureIntent() {

        String authorities = context.getPackageName()+".fileprovider";
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        authorities,
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
        return img;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            /*vista_foto.setImageBitmap(imageBitmap);
            try {
                createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            setResult(RESULT_OK,data);
        }
    }
}
