package ed.anpanman;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.CustomViewHolder>
{
    class CustomViewHolder extends RecyclerView.ViewHolder{
        TextView textView;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.textMessage);
        }
    }



    List<ResMessage>responseMessageList;

    public MessageAdapter(List<ResMessage> responseMessageList) {
        this.responseMessageList = responseMessageList;
    }

    @Override
    public int getItemViewType(int position) {
        if (responseMessageList.get(position).isMe())
        {
            return R.layout.me_bubble;
        }
        return R.layout.bot_bubble;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CustomViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(i,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.CustomViewHolder customViewHolder, int i) {

        customViewHolder.textView.setText(responseMessageList.get(i).getTextMessage());

    }

    @Override
    public int getItemCount() {
        return responseMessageList.size();
    }
}
