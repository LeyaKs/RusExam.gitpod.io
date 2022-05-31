package basic.helper.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FirstTask extends AppCompatActivity {
    ArrayBasic arr = new ArrayBasic();
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
                    arr = response.body();
                }
            }

            @Override
            public void onFailure(Call<ArrayBasic> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task);
        TextView lvl1 = (TextView)findViewById(R.id.lvl1);
        getstrokes();
        lvl1.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(FirstTask.this,
                        LevelCollector.class);
                intent.putExtra("key1", arr.verifiable_vowel);
                intent.putExtra("task_key1", 0);
                startActivity(intent);
                finish();
            }catch (Exception e){

            }
        });
        TextView lvl2 = (TextView)findViewById(R.id.lvl2);
        lvl2.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(FirstTask.this, LevelCollector.class);
                intent.putExtra("key1", arr.alternating_vowel);
                intent.putExtra("task_key1", 1);
                startActivity(intent);
                finish();
            }catch (Exception e){

            }
        });
        TextView lvl3 = (TextView)findViewById(R.id.lvl3);
        lvl3.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(FirstTask.this, LevelCollector.class);
                intent.putExtra("key1", arr.non_verifiable_vowel);
                intent.putExtra("task_key1", 2);
                startActivity(intent);
                finish();
            }catch (Exception e){

            }
        });
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    @Override
    public void onBackPressed() {
        try {
            Intent intent = new Intent(FirstTask.this, Tasks.class);
            startActivity(intent);
            finish();
        }catch (Exception e){

        }
    }
}