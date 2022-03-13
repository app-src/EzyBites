import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import io.github.ashishthehulk.ezybites.R;

public class Post extends BaseAdapter {

    private Context mContext;
    private int[] imageArray= {
            R.drawable.foodrecipeimage6,R.drawable.foodrecipeimage7,R.drawable.foodrecipeimage8,R.drawable.foodrecipeimage9,R.drawable.foodrecipeimage10
    };

    public Post(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return imageArray.length;
    }

    @Override
    public Object getItem(int i) {
        return imageArray[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(imageArray[i]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(340,350));
        return imageView;
    }
}

