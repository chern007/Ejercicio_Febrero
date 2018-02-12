package com.example.chern007.ejercicio_febrero;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.MimeTypeMap;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class actividad_3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_3);


        String[] proyeccion = {MediaStore.Files.FileColumns._ID,
                MediaStore.Files.FileColumns.TITLE,
                MediaStore.Files.FileColumns.SIZE,
                MediaStore.Files.FileColumns.DATE_MODIFIED};



//        String filtro = MediaStore.Files.FileColumns.TITLE + " like ?";
//        String args_filtro[] = {"%" + ".pdf" + "%"};
//        ContentResolver cr = getContentResolver();
//        Cursor cur = cr.query(MediaStore.Files.getContentUri("internal"), proyeccion, filtro, args_filtro, null);


        ContentResolver cr = getContentResolver();
        Uri uri = Uri.fromFile(new File("/storage/emulated/0/Download/"));
        String selectionMimeType = MediaStore.Files.FileColumns.MIME_TYPE + "=?";
        String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension("pdf");
        String[] selectionArgsPdf = new String[]{ mimeType };
        Cursor cur = cr.query(uri, proyeccion, selectionMimeType, selectionArgsPdf, null);



        List<String> lista_archivos = new ArrayList<String>();

        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                //obtener id de contacto
                String id = cur.getString(cur.getColumnIndex(MediaStore.Files.FileColumns._ID));
                //obtener nombre de contacto
                String name = cur.getString(cur.getColumnIndex(MediaStore.Files.FileColumns.TITLE));
                lista_archivos.add(name);
            }

            Log.i("HOLA", "ok");

        }
    }
}
