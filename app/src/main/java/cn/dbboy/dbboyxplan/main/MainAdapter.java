package cn.dbboy.dbboyxplan.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.dbboy.dbboyxplan.R;

/**
 * Created by DB_BOY on 2019/3/5.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    List<String> list;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_main_adapter,null));
    }

    public void setList(List<String> list){
        this.list = list;
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.bind(list.get(i));
        if(listener!=null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.click(i);
                }
            });
        }
    }
    
    private ItemClickListener listener;
    public interface ItemClickListener{
        void click(int position);
    }
    
    public void setListener(ItemClickListener listener){
        this.listener = listener;
    }
    

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv= itemView.findViewById(R.id.title);
        }
        void bind(String title){
            tv.setText(title);
        }
    }
    
}
