package com.sfu276assg1.yancao.mineseeker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        setTextLink();

    }

    private void setTextLink() {
        TextView textCourse = (TextView) findViewById(R.id.text_courseLink);
        textCourse.setText(Html.fromHtml("<a href=\"http://www.cs.sfu.ca/CourseCentral/276/bfraser/index.html\">courseHome</a>"));
        textCourse.setMovementMethod(LinkMovementMethod.getInstance());

        TextView textIconPic = (TextView) findViewById(R.id.text_picLink);
        textIconPic.setText(Html.fromHtml("<a href=\"https://www.google.ca/search?q=%E5%8A%9F%E5%A4%AB%E7%86%8A%E7%8C%AB+pic&espv=" +
                "2&biw=1938&bih=966&tbm=isch&tbo=u&source=univ&sa=X&ved=0ahUKEwi_lJbAmo7SAhXKrlQKHdC-AqUQsAQIGQ&dpr=2.5#imgrc=am_SUAQQNMSJ6M:\">PictureLinkHongFuPanda</a>"));
        textIconPic.setMovementMethod(LinkMovementMethod.getInstance());

        TextView textGridPic = (TextView) findViewById(R.id.text_picSmall);
        textGridPic.setText(Html.fromHtml("<a href=\"https://www.google.ca/search?q=%E5%8A%9F%E5%A4%AB%E7%86%8A%E7%8C%AB+pic&espv=2&biw=" +
                "1938&bih=966&tbm=isch&tbo=u&source=univ&sa=X&ved=0ahUKEwi_lJbAmo7SAhXKrlQKHdC-AqUQsAQIGQ&dpr=2.5#imgrc=SMTkQK23zwDPMM:\">PictureLinkAppIcon</a>"));
        textGridPic.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
