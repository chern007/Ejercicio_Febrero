package com.example.chern007.ejercicio_febrero;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class actividad_3 extends AppCompatActivity {


    List<String> listadoArchivos = new ArrayList<>();
    List<String> listadoArchivos2 = new ArrayList<>();
    ListView l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_3);


        String path = Environment.getExternalStorageDirectory().toString() + "/Download";
        Log.d("Files", "Path: " + path);
        File directory = new File(path);
        File[] files = directory.listFiles();
        Log.d("Files", "Size: " + files.length);
        for (int i = 0; i < files.length; i++) {
            Log.d("Files", "FileName:" + files[i].getName());
            listadoArchivos.add(files[i].getName());
        }


        //Planchamos los elementos que hemos obtenido en la lista
        l = (ListView) findViewById(R.id.lstElementos);
        l.setAdapter(new ArrayAdapter<String>(this, R.layout.fila, listadoArchivos));


        /////////////////////////////////////////////////////////////////////////////
        ///////////////CON CONTENT PROVIDER//////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////


        ContentResolver cr = getContentResolver();
        Uri uri = MediaStore.Files.getContentUri("external");

        // every column, although that is huge waste, you probably need
        // BaseColumns.DATA (the path) only.
        String projection[] = {MediaStore.Files.FileColumns._ID, MediaStore.Files.FileColumns.TITLE};

        // exclude media files, they would be here also.
        String selection = MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                + MediaStore.Files.FileColumns.MEDIA_TYPE_NONE;
        String[] selectionArgs = null; // there is no ? in selection so null here

        String sortOrder = null; // unordered
        Cursor allNonMediaFiles = cr.query(uri, projection, selection, selectionArgs, sortOrder);

        allNonMediaFiles.getCount();

        //recorremos el cursor y vamos guardando los elementos en una lista de Strings
        if (allNonMediaFiles.getCount() > 0) {
            while (allNonMediaFiles.moveToNext()) {
                String id = allNonMediaFiles.getString(allNonMediaFiles.getColumnIndex(MediaStore.Files.FileColumns._ID));
                String name = allNonMediaFiles.getString(allNonMediaFiles.getColumnIndex(MediaStore.Files.FileColumns.TITLE));
                listadoArchivos2.add(name);

            }
        }
        allNonMediaFiles.close();//cerramos el cursor



    }
}
