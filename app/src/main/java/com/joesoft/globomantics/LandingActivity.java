package com.joesoft.globomantics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.joesoft.globomantics.services.MessageService;
import com.joesoft.globomantics.services.ServiceBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LandingActivity extends AppCompatActivity {

    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        mTextMessage = findViewById(R.id.message);
//        message.setText("This is hardcoded, but thanks for visiting the app!  Our next hackathon is scheduled for the end of Q3.  We hope to see you there, be sure to add your ideas to the app!");

        MessageService messageService = ServiceBuilder.buildService(MessageService.class);
        Call<String> call = messageService.getMessages();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                mTextMessage.setText(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                mTextMessage.setText(t.getMessage());
            }
        });

    }

    public void GetStarted(View view){
        Intent intent = new Intent(this, IdeaListActivity.class);
        startActivity(intent);
    }
}
