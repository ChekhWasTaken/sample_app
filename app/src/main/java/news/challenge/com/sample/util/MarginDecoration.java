package news.challenge.com.sample.util;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MarginDecoration extends RecyclerView.ItemDecoration {
    private final int size;

    public MarginDecoration(int size) {
        this.size = size;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = size;
        }

        outRect.right = size;
        outRect.bottom = size;
        outRect.left = size;
    }
}
