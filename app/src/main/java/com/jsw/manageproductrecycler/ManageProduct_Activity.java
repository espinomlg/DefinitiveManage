package com.jsw.manageproductrecycler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jsw.manageproductrecycler.Adapter.ReciclerAdapter;

/*Cuando hacemos que herede de ListActivity Internamente ya tiene un tipo definido que es la lista
*
* Para que android haga la vinculacion del xml con nuestra lista interna, es decir
* que lo infle de forma automatica, tenemos que añadir al listview el id "android:id/list"*/
public class ManageProduct_Activity extends AppCompatActivity {

    private ReciclerAdapter mAdapter; //Adapter
    private RecyclerView mReciclerView; //Recycler View

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_product);
        mAdapter = new ReciclerAdapter(this); //Add the adapter
        mReciclerView = (RecyclerView)findViewById(R.id.rv_vista); //View instance
        mReciclerView.setLayoutManager(new LinearLayoutManager(this)); //Set the layout manager with a linearlayout
        mReciclerView.setAdapter(mAdapter); //Add the adapter with the view
    }

    /**
     * Floating Add Button actions.
     * It opens the AddProduct Activity
     * @param v Button View
     */
    public void añadir(View v){
        Intent intent = new Intent(this, AddProduct_Activity.class);
        startActivity(intent);
    }
}
