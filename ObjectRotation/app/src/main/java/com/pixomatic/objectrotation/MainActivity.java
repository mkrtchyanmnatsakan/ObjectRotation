package com.pixomatic.objectrotation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    private ImageView generalImg;
    private TextView tvAngle;
    private ImageView smallImgTriangle;
    private ImageView smallImgCube;
    private ImageView smallImgStar;
    private RelativeLayout canvas;

    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private GestureDetector gestureDetector;
    View.OnTouchListener gestureListener;
    final int[] i = {0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        canvas.setOnTouchListener(new OnSlidingTouchListener(this) {
            @Override
            public boolean onSlideLeft() {

                generalImg.setRotation(i[0]++);
                String angleText = "Angle is : " + i[0];
                tvAngle.setText(angleText);
                return true;
            }

            @Override
            public boolean onSlideRight() {


                generalImg.setRotation(i[0]--);
                String angleText = "Angle is : " + i[0];
                tvAngle.setText(angleText);
                return true;
            }

            @Override
            public boolean onSlideUp() {
                return true;
            }

            @Override
            public boolean onSlideDown() {

                return true;
            }
        });

    }

    private void initViews(){

        canvas = findViewById(R.id.canvas);
        tvAngle = findViewById(R.id.tv_angle);
        generalImg =findViewById(R.id.general);
        smallImgTriangle = findViewById(R.id.img_small_triangle);
        smallImgCube = findViewById(R.id.img_small_cube);
        smallImgStar = findViewById(R.id.img_small_star);

        smallImgCube.setBackground(drowrectangle());
        smallImgStar.setBackground(drowStar());
        smallImgTriangle.setBackground(drawTriangle());
        generalImg.setBackground(drowStar());

        smallImgTriangle.setOnClickListener(this);
        smallImgCube.setOnClickListener(this);
        smallImgStar.setOnClickListener(this);

    }


    private BitmapDrawable drowrectangle(){
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#CD5C5C"));
        Bitmap bg = Bitmap.createBitmap(280,800, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bg);
        canvas.drawRect(100,100,400,400,paint);


        return new BitmapDrawable(bg);
    }


    private BitmapDrawable drowStar(){

        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#CD5C5C"));
        Bitmap bg = Bitmap.createBitmap(200,350, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bg);

        float posX = canvas.getWidth()/2;
        float posY = canvas.getHeight()/2;
        int size = 50;

        int hMargin = size/9;
        int vMargin = size/4;

        Point a = new Point((int) (posX + size/2), (int) posY);
        Point b = new Point((int) (posX + size/2 + hMargin), (int) (posY + vMargin));
        Point c = new Point((int) (posX + size), (int) (posY + vMargin));
        Point d = new Point((int) (posX + size/2 + 2*hMargin), (int) (posY + size/2 + vMargin/2));
        Point e = new Point((int) (posX + size/2 + 3*hMargin), (int) (posY + size));
        Point f = new Point((int) (posX + size/2), (int) (posY + size - vMargin));
        Point g = new Point((int) (posX + size/2 - 3*hMargin), (int) (posY + size));
        Point h = new Point((int) (posX + size/2 - 2*hMargin), (int) (posY + size/2 + vMargin/2));
        Point i = new Point((int) posX, (int) (posY + vMargin));
        Point j = new Point((int) (posX + size/2 - hMargin), (int) (posY + vMargin));

        Path path = new Path();
        path.moveTo(a.x, a.y);
        path.lineTo(b.x, b.y);
        path.lineTo(c.x, c.y);
        path.lineTo(d.x, d.y);
        path.lineTo(e.x, e.y);
        path.lineTo(f.x, f.y);
        path.lineTo(f.x, f.y);
        path.lineTo(g.x, g.y);
        path.lineTo(h.x, h.y);
        path.lineTo(i.x, i.y);
        path.lineTo(j.x, j.y);
        path.lineTo(a.x, a.y);

        path.close();

        canvas.drawPath(path, paint);
        smallImgStar.setBackground(new BitmapDrawable(bg));

        return new BitmapDrawable(bg);
    }

    public BitmapDrawable drawTriangle() {
        int halfWidth = 100 / 2;
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#CD5C5C"));
        Bitmap bg = Bitmap.createBitmap(280,350, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bg);
        Path path = new Path();
        path.moveTo(100,  100- halfWidth);
        path.lineTo(100 - halfWidth, 100 + halfWidth);
        path.lineTo(100 + halfWidth, 100 + halfWidth);
        path.lineTo(100, 100 - halfWidth);
        path.close();

        canvas.drawPath(path, paint);

        return new BitmapDrawable(bg);
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_small_triangle:
                generalImg.setBackground(drawTriangle());
                i[0] = 0;

                break;
            case R.id.img_small_cube:
                generalImg.setBackground(drowrectangle());
                i[0] = 0;
                break;
            case R.id.img_small_star:
                generalImg.setBackground(drowStar());
                i[0] = 0;
                break;
        }
    }
}
