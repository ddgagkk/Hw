package noname.hw5vs6;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hp on 10/18/2017.
 */

public class AdapterRecyler extends RecyclerView.Adapter<AdapterRecyler.RecylerHolder> {

    ArrayList<User> listUser = new ArrayList<User>();

    public AdapterRecyler(ArrayList<User> list){
        listUser=list;
    }

    @Override
    public RecylerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view= inflater.inflate(R.layout.item_layout,parent,false);
        return  new RecylerHolder(view);
    }

    @Override
    public void onBindViewHolder(RecylerHolder holder, int position) {
        holder.tv.setText(listUser.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    public class RecylerHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public RecylerHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tVInfor);
        }
    }
}
