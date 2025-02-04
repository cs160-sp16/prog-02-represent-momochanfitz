package com.example.maurafitzgerald.prog02;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by maurafitzgerald on 2/29/16.
 */
public class CustomFragment extends Fragment {

    TextView nameView;
    ImageView iconView;
    TextView partyView;
    ImageButton graph;
    ImageButton info;
    String obama;
    String romney;
    String county_state;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sample_card, container, false);

        Typeface fauna = Typeface.createFromAsset(getResources().getAssets(), "FaunaOne-Regular.ttf");
        Typeface playfair = Typeface.createFromAsset(getResources().getAssets(),  "PlayfairDisplay-Regular.ttf");



        iconView = (ImageView) view.findViewById(R.id.iconview);
        nameView = (TextView) view.findViewById(R.id.nameview);
        partyView = (TextView) view.findViewById(R.id.partyName);
        TextView roleView = (TextView) view.findViewById(R.id.roleview);
        roleView.setText(getArguments().getString("Role"));
        roleView.setTypeface(playfair);

        obama = (String) getArguments().get("Obama");
        romney = (String) getArguments().get("Romney");
        county_state = getArguments().get("County") + ", " +
                getArguments().get("State");

        String name = getArguments().getString("Name");
        nameView.setText(name);
        nameView.setTypeface(playfair);
        nameView.setTextSize(19);

        iconView.setImageResource(getArguments().getInt("Party"));
        partyView.setText(getArguments().getString("PartyName"));
        partyView.setTypeface(fauna);

        graph = (ImageButton) view.findViewById(R.id.graph);
        info = (ImageButton) view.findViewById(R.id.more_info);

        graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Graph.class);
                intent.putExtra("obama", obama);
                intent.putExtra("romney", romney);
                intent.putExtra("countyState", county_state);
                getActivity().startActivity(intent);
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(getActivity(), WatchToPhoneService.class);
                sendIntent.putExtra("RepID", getArguments().getInt("RepID"));
                getActivity().startService(sendIntent);
            }
        });


        return view;
    }
}
