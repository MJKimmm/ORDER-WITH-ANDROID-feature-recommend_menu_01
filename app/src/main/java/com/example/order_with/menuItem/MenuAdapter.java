package com.example.order_with.menuItem;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.order_with.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    ArrayList<Menu> myItems;
    ImageView imageView;
    Bitmap bitmap;

    public MenuAdapter(ArrayList<Menu> items){
        myItems = items;
    }

    public interface MyClickListener{
        void onItemClicked(Menu menu, int position);

    }
    MyClickListener mListener;

    public void setOnItemClickListener(MyClickListener listener){
        mListener = listener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView menu;
        TextView price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = (ImageView)itemView.findViewById(R.id.imageView);
            menu = (TextView)itemView.findViewById(R.id.tvMenu);
            price = (TextView)itemView.findViewById(R.id.tvPrice);
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.menu_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final Menu item = myItems.get(i);
        viewHolder.menu.setText(item.getTitle());
        viewHolder.price.setText(item.getPrice());

        if (mListener != null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClicked(item, getItemCount());
                }
            });
        }

            Thread mThread = new Thread() {
                URL url;
                HttpURLConnection conn;
                InputStream is;

                @Override
                public void run() {
                    try {

                         switch (i) {
                             case 0: // 치즈김밥
                                 url = new URL("http://mm.sookmyung.ac.kr/~it1516487/food/1935589795_5pny9aCF_ECB998ECA688EAB980EBB0A5.jpg");
                                 break;

                             case 1: // 묵은지김밥
                                 url = new URL("http://mm.sookmyung.ac.kr/~it1516487/food/43fbc9210a19b18bcf91f4a658300a891.jpg");
                                 break;

                             case 2: // 회냉면
                                 url = new URL("http://mm.sookmyung.ac.kr/~it1516487/food/CmNEBfHUsAAGLlC.jpg");
                                 break;

                             case 3: // 갈비탕
                                 url = new URL("http://mm.sookmyung.ac.kr/~it1516487/food/2017032702956_0.jpg");
                                 break;

                             case 4: // 김치라면
                                 url = new URL("http://mm.sookmyung.ac.kr/~it1516487/food/0060080000042.jpg");
                                 break;

                             case 5: // 미역국
                                 url = new URL("http://mm.sookmyung.ac.kr/~it1516487/food/0000001734.jpg");
                                 break;

                             case 6: // 쌀국수
                                 url = new URL("http://mm.sookmyung.ac.kr/~it1516487/food/15f14b61b41e5cc53a273ae1a3e346ca1.jpg");
                                 break;

                             case 7: // 불고기 "http://mm.sookmyung.ac.kr/~it1516487/food/df939769792632a48a0eba8bc895e8601.jpg"
                                 url = new URL("http://mm.sookmyung.ac.kr/~it1516487/food/df939769792632a48a0eba8bc895e8601.jpg");
                                 break;

                             case 8: // 김치만두
                                 url = new URL("http://mm.sookmyung.ac.kr/~it1516487/food/maxresdefault.jpg");
                                 break;

                             case 9: // 마라탕
                                 url = new URL("http://mm.sookmyung.ac.kr/~it1516487/food/P20160602_192439004_AF6B9A00-C01B-4B55-9B0D-7C13C66CD37A.jpeg");
                                 break;

                             case 10: // 새우감바스
                                 url = new URL("http://mm.sookmyung.ac.kr/~it1516487/food/1000028374437_i1_1200.jpg");
                                 break;

                             default:
                                 url = new URL("http://mm.sookmyung.ac.kr/~it1516487/food/df939769792632a48a0eba8bc895e8601.jpg");
                                 break;

                         }

                        conn = (HttpURLConnection) url.openConnection();
                        conn.setDoInput(true);
                        conn.connect();

                        is = conn.getInputStream();
                        bitmap = BitmapFactory.decodeStream(is);


                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };

            mThread.start();

            try {
                mThread.join();

                imageView.setImageBitmap(bitmap);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }

      // 하나만 설정 가능
   /*     if(i==1) {
            Thread mThread = new Thread() {
                URL url;
                @Override
                public void run() {
                    try {

                                url = new URL("https://movie-phinf.pstatic.net/20190417_212/1555463319238lklmS_JPEG/movie_image.jpg?type=m665_443_2");


                                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                conn.setDoInput(true);
                                conn.connect();

                                InputStream is = conn.getInputStream();
                                bitmap = BitmapFactory.decodeStream(is);


                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };

            mThread.start();

            try {
                mThread.join();

                imageView.setImageBitmap(bitmap);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        } */
    }

    @Override
    public int getItemCount() {
        return myItems.size();
    }

}
