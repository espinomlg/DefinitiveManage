package com.jsw.manageproductrecycler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.jsw.manageproductrecycler.Adapter.ReciclerAdapter;
import com.jsw.manageproductrecycler.Model.Product;
import com.jsw.manageproductrecycler.Settings.AccountSettings;
import com.jsw.manageproductrecycler.Settings.GeneralSettings;

/*Cuando hacemos que herede de ListActivity Internamente ya tiene un tipo definido que es la lista
*
* Para que android haga la vinculacion del xml con nuestra lista interna, es decir
* que lo infle de forma automatica, tenemos que añadir al listview el id "android:id/list"*/
public class RecyclerList_Activity extends AppCompatActivity {

    private ReciclerAdapter mAdapter; //Adapter
    private RecyclerView mReciclerView; //Recycler View
    private static final int ADD_PRODUCT = 0;
    private Intent intent;
    boolean sorted = false;

    private static final int EDIT_PRODUCT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_product);
        mAdapter = new ReciclerAdapter(this); //Add the adapter
        mReciclerView = (RecyclerView)findViewById(R.id.rv_vista); //View instance
        mReciclerView.setLayoutManager(new LinearLayoutManager(this)); //Set the layout manager with a linearlayout
        mReciclerView.setAdapter(mAdapter); //Add the adapter with the view
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.product_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Si devolvemos true es que el evento lo hemos consumido, si devolvemos false, no hemos consumido el evento,
     * es decir he recibido el evento, he realizado operaciones y no quiero que se propague para arriba.
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_sort_product:
                mAdapter.sortProducts();
                break;
            case R.id.action_account_settings:
                intent = new Intent(this, AccountSettings.class);
                startActivity(intent);
                break;
            case R.id.action_general_settings:
                intent = new Intent(this, GeneralSettings.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Floating Add Button actions.
     * It opens the AddProduct Activity
     * @param v Button View
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Product p = new Product(
                    R.drawable.diazepam,
                    data.getExtras().getString("Name"),
                    data.getExtras().getString("Trademark"),
                    data.getExtras().getString("Dosage"),
                    data.getExtras().getString("Stock"),
                    Double.parseDouble(data.getExtras().getString("Price")),
                    data.getExtras().getString("Description"));

            mAdapter.addProduct(p);
            mAdapter.notifyDataSetChanged();
        }
    }

    /**
     * Floating Add Button actions.
     * It opens the AddProduct Activity
     * @param v Button View
     */
    public void añadir(View v){
        intent = new Intent(this, AddProduct_Fragment.class);
        startActivityForResult(intent, 0);
    }
}
