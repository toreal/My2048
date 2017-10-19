package pu.example.toreal.my2048;

import android.graphics.Point;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.solver.ArrayLinkedVariables;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.widget.ListPopupWindow.WRAP_CONTENT;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler h = new Handler();
        h.postDelayed(r,1000);

    }

    Runnable r = new Runnable() {
        @Override
        public void run() {

            myfun();
        }
    };


    float sx,sy;

    void myfun(){

        ConstraintLayout myview = (ConstraintLayout) findViewById(R.id.myview);


        LinearLayout li = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);

        li.setOrientation(LinearLayout.VERTICAL);
      //  addContentView(li, params);
        myview.addView(li,params);


        myview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                float ex,ey;
                switch (motionEvent.getAction()){

                    case MotionEvent.ACTION_DOWN:
                        sx= motionEvent.getX();
                        sy = motionEvent.getY();

                        break;

                    case MotionEvent.ACTION_UP:

                        ex = motionEvent.getX() - sx;
                        ey = motionEvent.getY() - sy;


                        if ( Math.abs(ex) > Math.abs(ey) )
                        {
                            if (ex > 0 )
                                mright();
                            else
                                mleft();

                        }else
                        {
                             if ( ey> 0 )
                                 mdown();
                            else
                                mup();

                        }


                        break;

                }




                return true;
            }
        });


        int w=myview.getWidth();

        int n = 4;
        int nwidth = w/n;

        for (int j = 0; j < n; j++) {

            LinearLayout row = new LinearLayout(getApplicationContext());

            row.setOrientation(LinearLayout.HORIZONTAL);

            for ( int i = 0 ; i < n ; i++ ) {
                Card fl = new Card(getApplicationContext(), nwidth);
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


    void  mright(){

        boolean merge= false;
        for ( int j = 0 ; j < nrows; j++)
            for ( int i =  nrows -1 ; i >= 0 ; i -- )
            {
                for ( int ni = i-1; ni >=0 ; ni--)
                {
                    int curri = cards[i][j].getNum();
                    int checki = cards[ni][j].getNum();

                    if ( checki > 0)
                    {
                        if (curri ==0 )
                        {
                            cards[i][j].setNum(checki);
                            cards[ni][j].setNum(0);
                            merge = true;
                            i++;
                            break;

                        }else if (checki == curri)
                        {
                            cards[i][j].setNum(checki*2);
                            cards[ni][j].setNum(0);
                            merge=true;

                        }


                    }


                }

            }

            if ( merge)
                addNum();

        Toast.makeText(getApplicationContext(),"right", Toast.LENGTH_SHORT  ).show();
    }

    void mleft(){
        Toast.makeText(getApplicationContext(),"left", Toast.LENGTH_SHORT  ).show();

    }

    void mup(){
        Toast.makeText(getApplicationContext(),"up", Toast.LENGTH_SHORT  ).show();

    }
    void mdown(){
        Toast.makeText(getApplicationContext(),"down", Toast.LENGTH_SHORT  ).show();

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