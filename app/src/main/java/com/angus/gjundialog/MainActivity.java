package com.angus.gjundialog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button btn, btn2, btn3, btn4, btn5, btn6;
    TextView tv;
    ConstraintLayout layout;
    int mYear, mMonth, mDay, mHour, mMin;
    ProgressDialog pDialog;
    Handler handler;
    int num = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button)findViewById(R.id.button);
        btn2 = (Button)findViewById(R.id.button2);
        btn3 = (Button)findViewById(R.id.button3);
        btn4 = (Button)findViewById(R.id.button4);
        btn5 = (Button)findViewById(R.id.button5);
        btn6 = (Button)findViewById(R.id.button6);
        tv = (TextView) findViewById(R.id.textView);
        layout = (ConstraintLayout) findViewById(R.id.layout);
         Calendar calendar = Calendar.getInstance();
         mYear = calendar.get(Calendar.YEAR);
         mMonth = calendar.get(Calendar.MONTH) + 1;
         mDay = calendar.get(Calendar.DAY_OF_MONTH);
         mHour = calendar.get(Calendar.HOUR);
         mMin = calendar.get(Calendar.MINUTE);
         tv.setText("當前日期:" + mYear + "-" + mMonth + "-" + mDay);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCreateDialog(1);
//                AlertDialog.Builder AB = new AlertDialog.Builder(MainActivity.this);
//                AB.setTitle("巨匠電腦");
//                AB.setMessage("確定結束程式");
////                AB.setIcon(R.drawable.ic_launcher_background);
//                AB.setPositiveButton("確定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        finish();
//                    }
//                });
//                AB.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.cancel();
//                    }
//                });
//                AB.setCancelable(false);
//                AB.show();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCreateDialog(2);
//                showDialog(2);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateDialog(3);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateDialog(4);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                onCreateDialog(5);
                showDialog(5);
                num = 0;
                pDialog.setProgress(5);
                handler.sendEmptyMessage(0);
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                onCreateDialog(5);
                showDialog(6);
            }
        });
        handler =  new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (num < 100){
                    num = num +3;
                    pDialog.incrementProgressBy(2);
                    handler.sendEmptyMessageDelayed(0 ,50);
                }else{
                    pDialog.dismiss();
                    Toast.makeText(MainActivity.this, "下載完畢!!", Toast.LENGTH_SHORT).show();
                }
            }
        };



    }
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id){
            case 1:
                return new AlertDialog.Builder(MainActivity.this).setTitle("新竹認證")
                        .setMessage("Android手機程式設計班")
                        .setIcon(R.drawable.ic_launcher_foreground)
                        .setCancelable(false)//設為true為可以點選back button，false則是不行
                        .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).show();

            case 2:
                DialogInterface.OnClickListener DOCL = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                            switch (i){
                                case 0:
                                    layout.setBackgroundColor(Color.RED);
                                    break;
                                case 1:
                                    layout.setBackgroundColor(Color.GREEN);
                                    break;
                                case 2:
                                    layout.setBackgroundColor(Color.BLUE);
                                    break;
                                case 3:
                                    layout.setBackgroundColor(Color.rgb(255, 0, 255));
                                    break;
                            }
                    }
                };

                String item[] = {"紅色", "綠色", "藍色", "紫色"};
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("設定顏色背景")
                        .setItems(item, DOCL)
                        .setIcon(R.drawable.ic_launcher_foreground)
                        .setCancelable(false)//設為true為可以點選back button，false則是不行
                        .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).show();
            case 3:
                final String course[] = {"Android", "IOS", "Java", "CCNA"};
                final boolean checkedItems[] = new boolean[course.length];

                DialogInterface.OnMultiChoiceClickListener listener = new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        Toast.makeText(MainActivity.this, (isChecked?"選購:":"取消:") + course[which], Toast.LENGTH_SHORT).show();
                    }
                };

                return new AlertDialog.Builder(MainActivity.this).setTitle("課程清單")
                        .setMultiChoiceItems(course, checkedItems, listener)
                        .setIcon(R.drawable.ic_launcher_foreground)
                        .setCancelable(false)//設為true為可以點選back button，false則是不行
                        .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                StringBuilder sb = new StringBuilder("購買課程如下\n");
                                for(int j=0; j < course.length; j++){
                                    if(checkedItems[j]){
                                        sb.append(course[j] +"\n");
                                    }
                                }
                                Toast.makeText(MainActivity.this, sb, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).show();
            case 4:
//                Toast.makeText(MainActivity.this, "Hi", Toast.LENGTH_SHORT).show();
                 new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mYear = year;
                        mMonth = month +1;//month 從0~11
                        mDay = dayOfMonth;
                        tv.setText("設定日期" + mYear + "-" + mMonth + "-" +mDay);
                    }
                }, mYear, mMonth, mDay).show();
            case 5:
                pDialog = new  ProgressDialog(MainActivity.this);
                pDialog.setTitle("開始進行下載........");
                pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pDialog.setButton(ProgressDialog.BUTTON_NEGATIVE, "確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                pDialog.setButton(ProgressDialog.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                pDialog.setCancelable(false);
                pDialog.show();
//                return pDialog;
            case 6 :
                new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        mHour = hourOfDay;
                        mMin = minute;
                        tv.setText("設定時間:" + mHour + ":" + mMin);
                    }
                }, mHour, mMin, true).show();
        }
        return super.onCreateDialog(id);
    }
}
