package com.feicuiedu.eshop_20170518.feature.category;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.feicuiedu.eshop_20170518.R;
import com.feicuiedu.eshop_20170518.base.BaseFragment;
import com.feicuiedu.eshop_20170518.base.wrapper.ToastWrapper;
import com.feicuiedu.eshop_20170518.base.wrapper.ToolBarWrapper;
import com.feicuiedu.eshop_20170518.network.EshopClient;
import com.feicuiedu.eshop_20170518.network.core.UICallBack;
import com.feicuiedu.eshop_20170518.network.entity.CategoryBase;
import com.feicuiedu.eshop_20170518.network.entity.CategoryPrimary;
import com.feicuiedu.eshop_20170518.network.entity.CategoryRsp;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CategoryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    @BindView(R.id.list_category)
    ListView mLvListCategory;
    @BindView(R.id.list_children)
    ListView mLvListChildren;
    @BindView(R.id.standard_toolbar_title)
    TextView mToolBarTitle;
    @BindView(R.id.standard_toolbar)
    Toolbar mToolBar;
    private List<CategoryPrimary> data;
    private CategoryAdapter categoryAdapter;
    private ChildrenAdapter childrenAdapter;

    public CategoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoryFragmentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoryFragment newInstance(String param1, String param2) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    protected void paramInitialize() {
        new ToolBarWrapper(this).setCustomTitle(R.string.category_title);
        categoryAdapter = new CategoryAdapter();
        childrenAdapter = new ChildrenAdapter();
        mLvListCategory.setAdapter(categoryAdapter);
        mLvListChildren.setAdapter(childrenAdapter);
        if (data == null) {
            Call call = EshopClient.getInstance().getCategory();
            call.enqueue(new UICallBack() {

                @Override
                public void onFailureInUi(@NonNull Call call, @NonNull IOException e) {
                    ToastWrapper.show("请求失败" + e.getMessage());
                }

                @Override
                public void onResponseInUi(@NonNull Call call, @NonNull Response response) throws IOException {
                    if (response.isSuccessful()) {
                        CategoryRsp rsp = new Gson().fromJson(response.body().string(), CategoryRsp.class);
                        if (rsp.getStatus().isSucceed()) {
                            data = rsp.getData();
                            categoryAdapter.reset(data);
                            updateData(0);
                            ToastWrapper.show("数据条数：" + data.size());
                        }
                    }
                }
            });
            mLvListCategory.setOnItemClickListener(this);
            mLvListChildren.setOnItemClickListener(this);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_category;
    }

    private void updateData(int position){
        mLvListCategory.setItemChecked(position, true);
        childrenAdapter.reset(categoryAdapter.getItem(position).getChildren());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_category, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getActivity().onBackPressed();
        }
        if (item.getItemId() == R.id.menu_search) {
            //TODO:功能待实现
            ToastWrapper.show("搜索");
        }
        return true;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.list_category:
                CategoryPrimary categoryPrimary = data.get(position);
                List<CategoryBase> children = categoryPrimary.getChildren();
                childrenAdapter.reset(children);
                break;
            case R.id.list_children:
                ToastWrapper.show(childrenAdapter.getItem(position).getName());
                break;
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
