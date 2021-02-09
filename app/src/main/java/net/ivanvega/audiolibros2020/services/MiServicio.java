package net.ivanvega.audiolibros2020.services;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MiServicio extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MSAL", "servicio creado");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Este método se manda llamar cuando invocas el servicio con startService()
        //tarea pesado debe ir en un subproceso y desencadenarse asquí
        Log.d("MSAL", "Iniciando la tarea pesada ");
        try {
            Thread.sleep(5000);

                AsyncTask<Integer,
                        Integer, Boolean> task = new AsyncTask<Integer, Integer, Boolean>() {
                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    //Inicializacion de objetos
                }

                @Override
                protected Boolean doInBackground(Integer... integers) {

                    //código de tarea pesada
                    //consultar un API web o un recurso

                    for(int i=0; i < integers.length; i++){
                        Log.d("MSAL", "Iniciando la tarea pesada " + integers[i]);
                        onProgressUpdate(i,i);
                    }

                    return integers.length > 0;
                }

                @Override
                protected void onProgressUpdate(Integer... values) {
                    super.onProgressUpdate(values);
                    Log.d("MSAL", "Iniciando la tarea pesada " + values[0]);

                }

                @Override
                protected void onPostExecute(Boolean aBoolean) {
                    super.onPostExecute(aBoolean);
                    if(aBoolean){
                        Log.d("MSAL", "Tarea exhautiva finalizada");
                    }
                }
            };

            task.execute(1,2,3,4);
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //stopSelf();
        Log.d("MSAL", "Tarea pesada finalizada");

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MSAL", "Servicio destruido");
    }
}
