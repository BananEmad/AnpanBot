package ed.anpanman;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ibm.cloud.sdk.core.http.HttpConfigOptions;
import com.ibm.cloud.sdk.core.http.ServiceCall;
import com.ibm.cloud.sdk.core.service.security.IamOptions;
import com.ibm.watson.assistant.v1.Assistant;
import com.ibm.watson.assistant.v1.model.MessageInput;
import com.ibm.watson.assistant.v1.model.MessageOptions;
import com.ibm.watson.assistant.v1.model.MessageResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class chat extends AppCompatActivity {

    DatabaseReference reference;
    ImageButton btn_mic, btn_send;

    EditText mess;
    RecyclerView recyclerView;
    List<ResMessage> responseMessageList;
    MessageAdapter messageAdapter;
    private Assistant service  = null;
    String apiKey="RD4g8OhzLmGsypEsSxR3X6YgbmjWc74mBpAsz08GZIBL";
    String ass_url="https://gateway-lon.watsonplatform.net/assistant/api";
    FirebaseUser fuser;
    String workspaceId = "70c63f13-e960-4a01-b330-caeabeedc8fe";
    Assistant assistant=null;
    static MessageInput input = new MessageInput();
    MessageOptions options=options  = new MessageOptions.Builder(workspaceId)
            .input(input)
            .build();
    MessageResponse response;
    ResMessage message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mess = findViewById(R.id.userInput);
        btn_mic = findViewById(R.id.btn_mic);
        btn_send = findViewById(R.id.btn_send);
        recyclerView = findViewById(R.id.conversation);
        responseMessageList = new ArrayList<>();

        messageAdapter = new MessageAdapter(responseMessageList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(messageAdapter);
        fuser=FirebaseAuth.getInstance().getCurrentUser();

        try {
            //for networ error
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //setup assistant
            IamOptions iamOptions = new IamOptions.Builder()
                    .apiKey("RD4g8OhzLmGsypEsSxR3X6YgbmjWc74mBpAsz08GZIBL")
                    .build();
            assistant = new Assistant("2019-02-28", iamOptions);
            assistant.setEndPoint(ass_url);

//send request and recieve message


            options = new MessageOptions.Builder(workspaceId).input(input) .context(null).build();
            response = assistant.message(options).execute().getResult();

            for(int i=0;i<response.getOutput().getText().size();i++) {
                message = new ResMessage(response.getOutput().getText().get(i), false);
                responseMessageList.add(message);
            }

            messageAdapter.notifyDataSetChanged();

            btn_send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String temp=mess.getText().toString();
                    if (temp!="") {


                        input.setText(temp);
                        message = new ResMessage(temp, true);
                        responseMessageList.add(message);
                       options = new MessageOptions.Builder(workspaceId).input(input) .context(response.getContext()).build();
                        response = assistant.message(options).execute().getResult();
                        for(int i=0;i<response.getOutput().getText().size();i++) {
                            message = new ResMessage(response.getOutput().getText().get(i), false);
                            responseMessageList.add(message);

                        }
                        messageAdapter.notifyDataSetChanged();
                        if (!isVisible()) {
                            recyclerView.smoothScrollToPosition(messageAdapter.getItemCount() - 1);
                        }


                    }
                    else
                    {
                        Toast.makeText(chat.this, "لا يمكن ارسال رساله فارغه !!", Toast.LENGTH_LONG).show();
                    }
                    mess.setText("");
                }

            });
        }catch (Exception e) {
            e.printStackTrace();
        }
    }



    public boolean isVisible() {
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int positionOfLastVisibleItem = linearLayoutManager.findLastCompletelyVisibleItemPosition();
        int itemCount = recyclerView.getAdapter().getItemCount();
        return (positionOfLastVisibleItem >= itemCount);
    }

}

