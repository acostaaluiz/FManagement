package com.apptest.accenture.accentureinterview.utility;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.apptest.accenture.accentureinterview.R;

public class ProgressDialog extends DialogFragment {

    ImageView imageView_1,imageView_2,imageView_3,imageView_4,imageView_5;
    TextView txtViewLoadMessage;
    int position=0;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.progress_bar, container);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme);
    }

    /*private void changeImageSlider() {
        position++;
        switch (position)
        {
            case 1:{ imageView_1.setImageResource(R.drawable.pointer_change); }break;
            case 2:{ imageView_2.setImageResource(R.drawable.pointer_change); }break;
            case 3:{ imageView_3.setImageResource(R.drawable.pointer_change); }break;
            case 4:{ imageView_4.setImageResource(R.drawable.pointer_change); }break;
            case 5:{ imageView_5.setImageResource(R.drawable.pointer_change); }break;

            case 6:{ imageView_1.setImageResource(R.drawable.pointer_black); }break;
            case 7:{ imageView_2.setImageResource(R.drawable.pointer_black); }break;
            case 8:{ imageView_3.setImageResource(R.drawable.pointer_black); }break;
            case 9:{ imageView_4.setImageResource(R.drawable.pointer_black); }break;
            case 10:{ imageView_5.setImageResource(R.drawable.pointer_black);position=0; }
            break;
            default:{position=0;}
        }


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                changeImageSlider();
            }
        }, 500);
    }*/
}
