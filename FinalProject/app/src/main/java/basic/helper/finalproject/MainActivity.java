package basic.helper.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private long backPresseddTime;
    private Toast tooltip;
    ArrayBasic arrayBasic = new ArrayBasic();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView button_start = (TextView)findViewById(R.id.button_start);
        getstrokes();
        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(MainActivity.this, Tasks.class);
                    startActivity(intent);
                    finish();
                    //arrayBasic.start();
                }catch (Exception e){

                }
            }
        });
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void onBackPressed() {
        if (backPresseddTime + 2000 > System.currentTimeMillis()){
            tooltip.cancel();
            super.onBackPressed();
            return;
        }else{
            tooltip = Toast.makeText(getBaseContext(), "Нажмите ещё раз, чтобы выйти", Toast.LENGTH_SHORT);
            tooltip.show();
        }
        backPresseddTime = System.currentTimeMillis();
    }
    public void getstrokes(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/LeyaKs/RusExam.gitpod.io/gh-pages/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ClientService clientService = retrofit.create(ClientService.class);
        retrofit2.Call<ArrayBasic> call = clientService.getres();
        call.enqueue(new Callback<ArrayBasic>() {
            @Override
            public void onResponse(retrofit2.Call<ArrayBasic> call, Response<ArrayBasic> response) {
                if(response.isSuccessful()){
                    ArrayBasic arrayBasic = response.body();
                    Log.d("!!!!!!!!!!!!!!!!!!", arrayBasic.toString());
                }
            }

            @Override
            public void onFailure(Call<ArrayBasic> call, Throwable t) {
                t.printStackTrace();
            }
       });
    }
}