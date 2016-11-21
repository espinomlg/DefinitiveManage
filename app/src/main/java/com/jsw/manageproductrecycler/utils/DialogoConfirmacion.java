package com.jsw.manageproductrecycler.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.AdapterView;

import com.jsw.manageproductrecycler.Adapter.ProductAdapter;
import com.jsw.manageproductrecycler.ListProduct_Activity;
import com.jsw.manageproductrecycler.Model.Product;
import com.jsw.manageproductrecycler.Presenter.LoginPresenter;

/**
 * Created by usuario on 21/11/16.
 */

public class DialogoConfirmacion extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage("¿Desea eliminar el elemento?")
                .setTitle("Confirmación")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        ProductAdapter p = new ProductAdapter(getActivity().getApplicationContext());
                        p.deleteProduct(ListProduct_Activity.p);
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                    }
                });

        return builder.create();
    }
}
