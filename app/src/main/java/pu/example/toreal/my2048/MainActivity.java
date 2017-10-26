package pu.example.toreal.my2048;

import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.solver.ArrayLinkedVariables;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
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
        myview.setBackgroundColor(0xffffffff);
       // layout = (FrameLayout) findViewById(R.id.aniview);

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
         nwidth = w/n;

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



        layout = new FrameLayout(getApplicationContext());
      //  layout.setBackgroundColor( 0x88ffff00);
        myview.addView(layout);

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

                            animate(ni, i,j,checki , cards[i][j],cards[ni][j]) ;


                            merge = true;
                            i++;
                            break;

                        }else if (checki == curri)
                        {
                            cards[i][j].setNum(checki*2);
                            cards[ni][j].setNum(0);

                            animate(ni, i,j,checki , cards[i][j],cards[ni][j]) ;
                            merge=true;

                        }else //checki > 0 && curri!=0
                        {
                            break;
                        }


                    }


                }

            }

            if ( merge) {
                addNum();

                checkComplete();
            }
        Toast.makeText(getApplicationContext(),"right", Toast.LENGTH_SHORT  ).show();
    }

    void mleft(){
        Toast.makeText(getApplicationContext(),"left", Toast.LENGTH_SHORT  ).show();

    }


    void checkComplete()
    {
        boolean bend = true;


        for ( int i = 0 ; i < 4 ; i ++)
            for ( int j = 0 ; j<4; j++)
            {
                int curr=cards[i][j].getNum();
                int a =-1;
                if ( i > 0 )
                a=cards[i-1][j].getNum();
                int b = -1;
                if (i <3)
                b=cards[i+1][j].getNum();


                int c =-1;
                if ( j>0)
                c=cards[i][j-1].getNum();


                int d =-1;
                if (j < 3)
                    d=cards[i][j+1].getNum();


                if ( curr==0 || curr == a || curr ==b   || curr== c || curr==d )
                {
                    bend = false;

                    break;

                }

            }


            if ( bend)
            {
                 new AlertDialog.Builder(getApplicationContext()).setMessage("Game Over")
                         .setPositiveButton("start new game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        initGame();
                    }
                }).show();


            }


    }

    void mdown(){

        boolean merge= false;
        for ( int i = 0 ; i < nrows; i++)
            for ( int j=  nrows -1 ; j >= 0 ; j -- )
            {
                for ( int nj = j-1; nj >=0 ; nj--)
                {
                    int curri = cards[i][j].getNum();
                    int checki = cards[i][nj].getNum();

                    if ( checki > 0)
                    {
                        if (curri ==0 )
                        {
                            cards[i][j].setNum(checki);
                            cards[i][nj].setNum(0);
                            merge = true;
                            j++;
                            break;

                        }else if (checki == curri)
                        {
                            cards[i][j].setNum(checki*2);
                            cards[i][nj].setNum(0);
                            merge=true;

                        }


                    }


                }

            }

        if ( merge)
            addNum();




        Toast.makeText(getApplicationContext(),"down", Toast.LENGTH_SHORT  ).show();

    }
    void mup(){
        Toast.makeText(getApplicationContext(),"up", Toast.LENGTH_SHORT  ).show();

    }


    void animate(int fromx  , int tox , int row , int num  , final Card ca1, final Card ca2)
    {

        ca1.setVisibility(View.INVISIBLE);
       // ca2.setVisibility(View.INVISIBLE);
        final Card ca = new Card(getApplicationContext(),nwidth);

        ca.setNum(num);


        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(nwidth,nwidth);

        lp.setMargins(fromx*nwidth, row*nwidth,0,0);

        layout.addView(ca,lp);


        final Card cat = new Card(getApplicationContext(),nwidth);

        cat.setNum(0);


        FrameLayout.LayoutParams lpt = new FrameLayout.LayoutParams(nwidth,nwidth);

        lpt.setMargins(tox*nwidth, row*nwidth,0,0);

        layout.addView(cat,lpt);



        TranslateAnimation ani= new TranslateAnimation(0, nwidth *(tox-fromx),0, 0);


        ani.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                ca1.setVisibility(View.VISIBLE);
                ca.setVisibility(View.INVISIBLE);
                cat.setVisibility(View.INVISIBLE);


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        ani.setDuration(300);
        ca.startAnimation(ani);

    }

    int nwidth;
    int nrows= 4;
    FrameLayout layout;
    Card [][] cards =new Card[nrows][nrows];

    ArrayList<Point>  emptyList= new ArrayList<Point>();

    void initGame()
    {
        for ( int j = 0 ; j < nrows; j++)
            for ( int i = 0 ; i < nrows; i++)
            {
                cards[i][j].setNum(0);

            }

            addNum();
            addNum();


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