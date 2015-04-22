package uk.andrewjack.android.bass.ui.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import uk.andrewjack.android.bass.R;
import uk.andrewjack.android.bass.data.model.FiveHundredPxPhoto;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private final Picasso picasso;
    private final List<FiveHundredPxPhoto> data;
    private final Listener listener;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.card_view)
        CardView cardView;

        @InjectView(R.id.name)
        TextView textInfo;

        @InjectView(R.id.image)
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }

    public MainAdapter(Picasso picasso,
                       List<FiveHundredPxPhoto> data,
                       Listener listener) {
        this.picasso = picasso;
        this.data = data;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final FiveHundredPxPhoto item = data.get(position);
        holder.textInfo.setText(item.getName());
        picasso.load(item.getImageUrl()).into(holder.imageView);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClicked(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static interface Listener {
        void onItemClicked(FiveHundredPxPhoto photo);
    }

}
