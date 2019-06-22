package news.challenge.com.sample.component.newslist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;
import java.util.Objects;

import news.challenge.com.sample.R;
import news.challenge.com.sample.action.NewsActionListener;
import news.challenge.com.sample.databinding.FragmentNewsListBinding;
import news.challenge.com.sample.model.NewsEntity;
import news.challenge.com.sample.util.Data;
import news.challenge.com.sample.util.MarginDecoration;

import static news.challenge.com.sample.util.Utils.createDialog;

public class NewsListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, Observer<Data<List<NewsEntity>>> {
    private NewsListAdapter adapter;
    private FragmentNewsListBinding binding;
    private NewsListViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        viewModel = ViewModelProviders.of(this).get(NewsListViewModel.class);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNewsListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new NewsListAdapter(new NewsActionListener());

        binding.listNews.setAdapter(adapter);
        binding.listNews.addItemDecoration(new MarginDecoration(((int) getResources().getDimension(R.dimen.news_item_margin))));

        binding.containerRefresh.setOnRefreshListener(this);

        binding.setEmpty(true);

        viewModel.fetchNewsList().observe(this, this);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        viewModel.getData().removeObserver(this);
    }

    @Override
    public void onRefresh() {
        viewModel.fetchNewsList(true).observe(this, this);
    }

    @Override
    public void onChanged(@Nullable Data<List<NewsEntity>> listData) {
        switch (Objects.requireNonNull(listData).getState()) {
            case LOADING:
                if (!binding.containerRefresh.isRefreshing()) binding.setLoading(true);
                break;
            case LOADED:
                List<NewsEntity> data = listData.getData();
                if (binding.containerRefresh.isRefreshing()) {
                    adapter.set(data);
                    binding.containerRefresh.setRefreshing(false);
                } else {
                    adapter.submit(data);
                }

                binding.setEmpty(data.size() == 0);
                binding.setLoading(false);
                break;
            case ERROR:
                if (binding.containerRefresh.isRefreshing())
                    binding.containerRefresh.setRefreshing(false);

                createDialog(getContext(),
                        getString(R.string.dialog_err_title),
                        getString(R.string.dialog_err_body))
                        .show();

                binding.setEmpty(true);
                binding.setLoading(false);
                break;
        }
    }
}
