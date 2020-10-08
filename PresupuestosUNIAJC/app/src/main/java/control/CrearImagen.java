package control;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by jhonyrenteria on 24/05/17.
 */

public class CrearImagen {
    static final int REQUEST_IMAGE_CAPTURE  = 1;//Request to camera
    static final int REQUEST_TAKE_PHOTO     = 1;
    private Context context                 = null;
    String nameImg                          = "";
    public String url_imagen                = "";
    public String imageFileName             = "";
    public CrearImagen(Context context,String nameImg) {
        this.context = context;
        this.nameImg = nameImg;
    }

    public void mostrarMensaje(String mensaje){
        Toast.makeText(context,mensaje,Toast.LENGTH_SHORT).show();
    }
    //Metodos de fotos
    private File createImageFile() throws IOException {
        // Create an image file name
        imageFileName = nameImg;
        url_imagen = Environment.DIRECTORY_PICTURES+"/PRESUPUESTOS/";
        File storageDir = context.getExternalFilesDir(url_imagen);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        return image;
    }

    public Intent dispatchTakePictureIntent() {

        String authorities = context.getPackageName()+".fileprovider";
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(context.getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(context,
                        authorities,
                        photoFile);
               // takePictureIntent.putExtra("url", url_imagen+imageFileName);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                mostrarMensaje(url_imagen+imageFileName);
            }
        }
        return takePictureIntent;
    }
}
