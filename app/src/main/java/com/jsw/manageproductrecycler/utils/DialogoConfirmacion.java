package com.jsw.manageproductrecycler.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.jsw.manageproductrecycler.Model.Product;
import com.jsw.manageproductrecycler.Presenter.ProductPresenter;
import com.jsw.manageproductrecycler.interfaces.IProductPresenter;

/**
 * Created by usuario on 21/11/16.
 */

public class DialogoConfirmacion extends DialogFragment {

    public interface OnDeleteProductListener{
        void deleteProduct(Product product);
    }

    Product p;
    IProductPresenter mPresenter;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        p = (Product)getArguments().getParcelable("product");
        return crear(p.getmName());
    }

    public Dialog crear(String name){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("¿Desea eliminar el elemento " + name + "?")
                .setTitle("Confirmación")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        mPresenter.deleteProduct(p);
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

    public void setPresenter(ProductPresenter presenter){
        this.mPresenter = presenter;
    }
}
