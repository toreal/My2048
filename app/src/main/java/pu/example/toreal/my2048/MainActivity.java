package pu.example.toreal.my2048;

import android.graphics.Point;
import android.support.constraint.solver.ArrayLinkedVariables;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static android.widget.ListPopupWindow.WRAP_CONTENT;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        LinearLayout li = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);

        li.setOrientation(LinearLayout.VERTICAL);
        addContentView(li, params);

        int n = 4;

        for (int j = 0; j < n; j++) {

            LinearLayout row = new LinearLayout(getApplicationContext());

            row.setOrientation(LinearLayout.HORIZONTAL);

            for ( int i = 0 ; i < n ; i++ ) {
                Card fl = new Card(getApplicationContext());
                cards[i][j]=fl;
                fl.setNum( 0 );

                row.addView(fl);
            }
            li.addView(row);


        }


        //Card obj = new Card(getApplicationContext());

       // initGame();
       addNum();
        addNum();

        addNum();
        addNum();

    }

    int nrows= 4;
    Card [][] cards =new Card[nrows][nrows];

    ArrayList<Point>  emptyList= new ArrayList<Point>();

    void initGame()
    {
        for ( int j = 0 ; j < nrows; j++)
            for ( int i = 0 ; i < nrows; i++)
            {
                emptyList.add(new Point(i,j));

            }



    }


    void addNum()
    {
        emptyList.clear();

        for ( int j = 0 ; j < nrows; j++)
            for ( int i = 0 ; i < nrows; i++)
            {

                int v=cards[i][j].getNum();
                if (v==0)
                emptyList.add(new Point(i,j));

            }




        if ( emptyList.size()>0)
        {
           int r=(int)( Math.random()*emptyList.size());
           Point p=emptyList.remove(r);

           cards[p.x][ p.y].setNum(2);



        }


    }


}