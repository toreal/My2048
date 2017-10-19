package pu.example.toreal.my2048;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Created by toreal on 2017/10/12.
 */

public class Card extends FrameLayout {



    public Card(@NonNull Context context) {
        super(context);

        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(50 ,50);
        lp.setMargins(10,10,0,0);

        View view = new View(context);
        view.setBackgroundColor(0x33ff00ff);
        addView(view,lp);

         tv= new TextView(context);
       // tv.setText(Integer.toString(0));
        tv.setTextSize(28);
        tv.setGravity(Gravity.CENTER);
        addView(tv,lp);



    }
    TextView tv;
    int value;

    public int getNum()
    {
        return value;
    }

    public void setNum( int num )
    {
        value = num;

        tv.setText(Integer.toString(num));

    }
}
