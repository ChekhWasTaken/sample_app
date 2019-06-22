package news.challenge.com.sample;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class LauncherActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DataBindingUtil.setContentView(this, R.layout.activity_launcher);
    }

//    @Override
//    public void onNewsItemSelected(NewsEntity entity) {
//        Bundle args = new Bundle();
//        args.putParcelable(NewsDetailFragment.KEY_NEWS_ENTITY, entity);
//
//        NewsDetailFragment fragment = new NewsDetailFragment();
//        fragment.setArguments(args);
//
//        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
//    }
}
