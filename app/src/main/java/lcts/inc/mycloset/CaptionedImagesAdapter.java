package lcts.inc.mycloset;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

public class CaptionedImagesAdapter extends RecyclerView.Adapter<CaptionedImagesAdapter.ViewHolder> {

    private String[] captions;
    private byte[][] imageIds;


    public CaptionedImagesAdapter(String[] captions, byte[][] imageIds) {
        this.captions = captions;
        this.imageIds = imageIds;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_captioned_image, viewGroup, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        CardView cardView = viewHolder.cardView;
        ImageView imageView = cardView.findViewById(R.id.info_image_view);
        imageView.setImageDrawable(new BitmapDrawable(BitmapFactory.decodeByteArray(imageIds[position], 0, imageIds[position].length)));
        TextView textView = cardView.findViewById(R.id.info_text_view);
        textView.setText(captions[position]);

    }

    @Override
    public int getItemCount() {
        return captions.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public ViewHolder(@NonNull CardView cv) {
            super(cv);
            cardView = cv;
        }
    }
}
