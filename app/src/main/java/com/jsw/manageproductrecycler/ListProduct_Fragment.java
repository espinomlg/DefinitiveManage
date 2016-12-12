package com.jsw.manageproductrecycler;

/*
 * Copyright (c) 2016 José Luis del Pino Gallardo.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *  jose.gallardo994@gmail.com
 */

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.jsw.manageproductrecycler.Adapter.ProductAdapter;
import com.jsw.manageproductrecycler.Model.Product;
import com.jsw.manageproductrecycler.Presenter.ProductPresenter;
import com.jsw.manageproductrecycler.interfaces.IProduct;
import com.jsw.manageproductrecycler.interfaces.IProductPresenter;
import com.jsw.manageproductrecycler.utils.DialogoConfirmacion;

import java.util.List;

public class ListProduct_Fragment extends ListFragment implements IProduct, ProductPresenter.View{

    public static Product p;

    private ProductAdapter mAdapter;
    private ListView mList;
    private int mItemPos;
    private AdapterView<?> mItemParent;
    private TextView mEmpty;
    PopupMenu popup;
    private IProductPresenter mPresenter;

    private IListProductListener mCallback;

    public interface IListProductListener{
        void showManageProduct(Bundle bundle);
    }

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        mAdapter = new ProductAdapter(getContext());
        mPresenter = new ProductPresenter(this);
        setRetainInstance(true);


        /**
         * Esta opcion le dice a la actitivity que este fragment tiene su propio menu y llama a todos
         * los métodos de los fragments.
         */
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle args) {
        super.onCreateView(inflater, container,  args);
        View rootView = inflater.inflate(R.layout.fragment_list_product, container, false);
        mList = getListView();
        mEmpty = (TextView)rootView.findViewById(android.R.id.empty);
        mList.setAdapter(mAdapter);


        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Bundle potatoe = new Bundle();
                potatoe.putParcelable(PRODUCT_KEY, (Product) parent.getItemAtPosition(i));
                mCallback.showManageProduct(potatoe);
            }
        });

        mList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                mItemParent = adapterView;
                mItemPos = i;

                popup  = new PopupMenu(getContext(), view);
                popup.setGravity(Gravity.END);
                popup.getMenuInflater().inflate(R.menu.delete_menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        p = (Product) mItemParent.getItemAtPosition(mItemPos);
                        DialogoConfirmacion dialog = new DialogoConfirmacion();
                        Bundle zip = new Bundle();
                        zip.putParcelable("product", p);
                        dialog.setPresenter((ProductPresenter) mPresenter);
                        dialog.setArguments(zip);
                        dialog.show(getActivity().getFragmentManager(), "");
                        return true;
                    }
                });
                popup.show();
                return true;
            }
        });

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_listproduct, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    public void añadir(View v){
       mCallback.showManageProduct(null);
    }

    public void showProduct(List<Product> products) {
        mAdapter.updateListProduct(products);
    }

    private  void hideList(boolean hide){
        mList.setVisibility(hide ? View.VISIBLE : View.GONE);
        mEmpty.setVisibility(hide ? View.VISIBLE : View.GONE);
    }

    public void showEmptyState(boolean show){
        hideList(show);
    }

    public void showMessage(String message){

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mAdapter = null;
        mPresenter = null;
    }
}