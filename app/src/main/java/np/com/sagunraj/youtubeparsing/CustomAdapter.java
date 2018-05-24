package np.com.sagunraj.youtubeparsing;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import np.com.sagunraj.youtubeparsing.entities.Item;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{
    Context context;
    List<Item> data;
    public CustomAdapter(MainActivity mainActivity, List<Item> mydata) {
        context = mainActivity;
        data = mydata;
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { // for layout inflating and MyViewHolder will be used for findViewById()
        View convertView = LayoutInflater.from(context).inflate(R.layout.singleitem, parent, false);
        return new MyViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) { // for setting data since the parameter 'position' is present here
        holder.title.setText(data.get(position).getSnippet().getTitle());
        holder.date.setText(data.get(position).getSnippet().getPublishedAt());
        Glide.with(context).load(data.get(position).getSnippet().getThumbnails().getDefault().getUrl()).into(holder.imv);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imv;
        TextView title, date;
        public MyViewHolder(View itemView) {
            super(itemView);
            imv = itemView.findViewById(R.id.imv);
            title = itemView.findViewById(R.id.title);
            date = itemView.findViewById(R.id.date);
        }
    }
}
