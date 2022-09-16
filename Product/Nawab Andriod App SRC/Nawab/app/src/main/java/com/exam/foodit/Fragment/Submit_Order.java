package com.exam.foodit.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exam.foodit.R;
import com.exam.foodit.Sqlite.DatabaseHelper;
public class Submit_Order extends Fragment {

    DatabaseHelper mydb;
    String email = "eatitrestaurant@gmail.com";

    public Submit_Order() {
        // Required empty public constructor;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Intent chooser = null;
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, email);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Food It Restaurant Order  ");
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }

        return inflater.inflate(R.layout.fragment_submit__order, container, false);
    }
}