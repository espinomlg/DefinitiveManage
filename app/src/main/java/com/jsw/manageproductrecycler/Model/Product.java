package com.jsw.manageproductrecycler.Model;

/**
 * Created by usuario on 19/10/16.
 */

public class Product implements Comparable{
    private int mId;
    private int mStock;
    private int mImage;
    private String mName;
    private String mDescription;
    private String mDosage;
    private String mBrandM;
    private double mPrice;

    public Product(int mId, int mStock, int mImage, String mName, String mDescription, String Dosage, String mBrandM, double mPrice) {
        this.mId = mId;
        this.mStock = mStock;
        this.mImage = mImage;
        this.mName = mName;
        this.setmDosage(Dosage);
        this.mDescription = mDescription;
        this.mBrandM = mBrandM;
        this.mPrice = mPrice;
    }

    public int getmId() {
        return mId;
    }

    public int getmStock() {
        return mStock;
    }

    public void setmStock(int mStock) {
        this.mStock = mStock;
    }

    public int getmImage() {
        return mImage;
    }

    public void setmImage(int mImage) {
        this.mImage = mImage;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmBrandM() {
        return mBrandM;
    }

    public void setmBrandM(String mBrandM) {
        this.mBrandM = mBrandM;
    }

    public double getmPrice() {
        return mPrice;
    }

    public void setmPrice(double mPrice) {
        this.mPrice = mPrice;
    }

    public String getFormattedPrice(){
        return String.format("$%s", mPrice);
    }


    public String getmDosage() {
        return mDosage;
    }

    public void setmDosage(String mDosage) {
        this.mDosage = mDosage;
    }


    @Override
    public String toString() {
        return mName + "     " + mStock + "     " + mPrice;

                /*"mId=" + mId +
                ", mStock=" + mStock +
                //", mImage=" + mImage +
                ", mName='" + mName + '\'' +
                //", mDescription='" + mDescription + '\'' +
                //", mBrandM='" + mBrandM + '\'' +
                ", mPrice=" + mPrice +
                '}';*/
    }

    /*Dos prodcutos son iguales cuando tienen:
    -El mismo nombre
    -La misma marca
    -La misma concentración
     */

    /**
     * Equals Method refactored by me.
     * @param  o Object we want to compare.
     * @return Boolean. True if they are the same, false if not.
     */
    @Override
    public boolean equals(Object o) {
        boolean res = false;

        //Upcast
        if(this != o || o == null || getClass() != o.getClass())
            res = false;
        else{
            //Downcast
            Product product = (Product) o;

            if (mName.equals(product.mName) && mDosage.equals(product.mDosage) && mBrandM.equals(product.mBrandM))
                res = true;
        }

        return res;
    }

    @Override
    public int compareTo(Object another) {
        return ((Product)another).getmName().compareTo(this.getmName());
    }
}