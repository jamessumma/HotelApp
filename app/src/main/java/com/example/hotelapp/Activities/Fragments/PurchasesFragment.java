package com.example.hotelapp.Activities.Fragments;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Products.Product;
import com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Products.Products;
import com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Transactions.Transaction;
import com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Transactions.Transactions;
import com.example.hotelapp.Activities.DatabaseAccess.HotelLoginValidation;
import com.example.hotelapp.R;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.DatabaseTasks.DatabaseTask;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseTable.DatabaseTableRecord;
import com.example.myandroidsupportlibrary.FragmentSupport.DynamicComponents.DataItemToView;
import com.example.myandroidsupportlibrary.FragmentSupport.DynamicComponents.DataListView.DataListView;
import com.example.myandroidsupportlibrary.FragmentSupport.DynamicFragment;

public class PurchasesFragment extends DynamicFragment {

    private Transactions transactions;
    private Products products;

    private DataListView dataListView;

    public PurchasesFragment() {
        super();
        transactions = new Transactions(DatabaseTask.getDatabaseController());
        products = new Products(DatabaseTask.getDatabaseController());
        transactions.setAssociatedFragment(this);
        dataListView = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        if (dataListView == null) {
            RecyclerView recyclerView = fragmentView.findViewById(R.id.recyclerView);

            dataListView = new DataListView(recyclerView,
                            new LinearLayoutManager(this.getContext(),
                            LinearLayoutManager.VERTICAL, false),
                            transactions);
            dataListView.setRetrievalValue(HotelLoginValidation.getUserId());
            dataListView.setItemLayoutId(R.layout.purchases_layout);
            dataListView.setDataItemToViewRenderer(new DataItemToView() {
                @Override
                public boolean transferItemData(View view, DatabaseTableRecord dataItem) {
                    return transferTransactionData(view, (Transaction)dataItem)
                            && transferPurchaseData(view, (Product)dataItem);
                }
            });
            registerDynamicComponent(dataListView);
            dataListView.setRequiresUpdate(true);
            refresh(true);
        }

        return fragmentView;
    }

    private boolean transferTransactionData(View view, Transaction transaction) {

        boolean success = false;
        if (transaction != null) {
            TextView purchaseDateTextView = view.findViewById(R.id.purchase_date);

            purchaseDateTextView.setText("Date: " + transaction.getFieldValue("transactionDate"));
            success = true;
        }
        return success;
    }

    private boolean transferPurchaseData(View view, Product product) {

        boolean success = false;
        if (product != null) {
            TextView purchaseItemTextView = view.findViewById(R.id.purchase_item);
            TextView purchasePriceTextView = view.findViewById(R.id.purchase_price);

            purchaseItemTextView.setText("Purchase: " + product.getFieldValue("productName"));
            purchasePriceTextView.setText("Price: " + product.getFieldValue("productPrice"));
            success = true;
        }
        return success;
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.purchases_layout;
    }
}

