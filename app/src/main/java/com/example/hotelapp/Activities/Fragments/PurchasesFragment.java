package com.example.hotelapp.Activities.Fragments;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Products.Product;
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

    private DataListView dataListView;

    public PurchasesFragment() {
        super();
        transactions = new Transactions(DatabaseTask.getDatabaseController());
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PurchasesFragment#newInstance} factory method to
 * create an instance of this fragment.
 *//*

public class PurchasesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PurchasesFragment() {
        // Required empty public constructor
    }

    */
/**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PurchasesFragment.
     *//*

    // TODO: Rename and change types and number of parameters
    public static PurchasesFragment newInstance(String param1, String param2) {
        PurchasesFragment fragment = new PurchasesFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_purchases, container, false);
    }
}*/
