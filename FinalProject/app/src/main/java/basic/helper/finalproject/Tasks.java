package basic.helper.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Tasks extends AppCompatActivity {

    ArrayBasic total_arr;
    public void getstrokes(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://leyaks.github.io/RusExam.gitpod.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ClientService clientService = retrofit.create(ClientService.class);
        retrofit2.Call<ArrayBasic> call = clientService.getres();
        call.enqueue(new Callback<ArrayBasic>() {
            @Override
            public void onResponse(retrofit2.Call<ArrayBasic> call, Response<ArrayBasic> response) {
                if(response.isSuccessful()){
                    total_arr = response.body();
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
        setContentView(R.layout.tasks);
        TextView button_back = (TextView)findViewById(R.id.button_back);
        getstrokes();
        button_back.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(Tasks.this, MainActivity.class);
                startActivity(intent);
                finish();
            }catch (Exception e){

            }
        });
        TextView task1 = (TextView)findViewById(R.id.task1);
        TextView task2 = (TextView)findViewById(R.id.task2);
        TextView task3 = (TextView)findViewById(R.id.task3);
        TextView task4 = (TextView)findViewById(R.id.task4);
        TextView task5 = (TextView)findViewById(R.id.task5);
        TextView task6 = (TextView)findViewById(R.id.task6);
        task1.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(Tasks.this, FirstTask.class);
                startActivity(intent);
                finish();
            }catch (Exception e){

            }
        });
        task2.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(Tasks.this, LevelCollector.class);
                intent.putExtra("key1", total_arr.task_10);
                intent.putExtra("task_key1", 3);
                startActivity(intent);
                finish();
            }catch (Exception e){

            }
        });
        task3.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(Tasks.this, LevelCollector.class);
                intent.putExtra("key1", total_arr.task_11);
                intent.putExtra("task_key1", 4);
                startActivity(intent);
                finish();
            }catch (Exception e){

            }
        });
        task4.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(Tasks.this, LevelCollector.class);
                intent.putExtra("key1", total_arr.task_12);
                intent.putExtra("task_key1", 5);
                startActivity(intent);
                finish();
            }catch (Exception e){

            }
        });
        task5.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(Tasks.this, LevelCollector.class);
                intent.putExtra("key1", total_arr.task_13);
                intent.putExtra("task_key1", 6);
                startActivity(intent);
                finish();
            }catch (Exception e){

            }
        });
        task6.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(Tasks.this, LevelCollector.class);
                intent.putExtra("key1", total_arr.task_14);
                intent.putExtra("task_key1", 7);
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
            Intent intent = new Intent(Tasks.this, MainActivity.class);
            startActivity(intent);
            finish();
        }catch (Exception e){

        }
    }
}