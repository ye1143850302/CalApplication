package com.example.calapplication;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_0 ;
    Button btn_1;
    Button btn_2;
    Button btn_3 ;
    Button btn_4 ;
    Button btn_5 ;
    Button btn_6 ;  //数字按钮
    Button btn_7 ;
    Button btn_8 ;
    Button btn_9 ;
    Button btn_point ;  //小数点按钮
    Button btn_clear ;
    Button btn_del ;
    Button btn_pluse ;
    Button btn_minus ;
    Button btn_multiply ;
    Button btn_divide ;
    Button btn_equle ;
    EditText et_input ;//显示结果
    boolean clear_flag ;//清空标识

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_0 = (Button) findViewById(R.id.btn_0) ;
        btn_1 = (Button) findViewById(R.id.btn_1) ;
        btn_2 = (Button) findViewById(R.id.btn_2) ;
        btn_3 = (Button) findViewById(R.id.btn_3) ;
        btn_4 = (Button) findViewById(R.id.btn_4) ;
        btn_5 = (Button) findViewById(R.id.btn_5) ;
        btn_6 = (Button) findViewById(R.id.btn_6) ;
        btn_7 = (Button) findViewById(R.id.btn_7) ;
        btn_8 = (Button) findViewById(R.id.btn_8) ;
        btn_9 = (Button) findViewById(R.id.btn_9) ;
        btn_point = (Button) findViewById(R.id.btn_point) ;
        btn_clear = (Button) findViewById(R.id.btn_clear) ;
        btn_del = (Button) findViewById(R.id.btn_del) ;
        btn_pluse = (Button) findViewById(R.id.btn_pluse) ;
        btn_minus = (Button) findViewById(R.id.btn_minus) ;
        btn_multiply = (Button) findViewById(R.id.btn_multiply) ;
        btn_divide = (Button) findViewById(R.id.btn_divide) ;
        btn_equle = (Button) findViewById(R.id.btn_equal) ;
//以上实例化按钮
        et_input = (EditText) findViewById(R.id.et_input);  //实例化之后的显示屏

        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_pluse.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_equle.setOnClickListener(this);
        //设置以上按钮的点击事件
    }

    @Override
    public void onClick(View v) {//完成监听
        String str = et_input.getText().toString();//获取文本框的文本
        switch (v.getId()) {
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_point:
                if (clear_flag) {//若清除标志位为1
                    clear_flag =false ;
                    str ="" ;//将文本框的文本置为空
                    et_input.setText("");
                }
                et_input.setText(str + ((Button)v).getText());//如果清除标志不为1 则获取button上的数字文本
                break ;
            case R.id.btn_pluse:
            case R.id.btn_minus:
            case R.id.btn_multiply:
            case R.id.btn_divide:
                if (clear_flag) {//若清除标志位为1
                    clear_flag =false ;
                    str ="" ;
                    et_input.setText("");
                }//将文本框的文本置为空
                et_input.setText(str+ " " + ((Button)v).getText()+" ");//如果清除标志不为1 则获取button上的符号文本
                break;
            case R.id.btn_del:
                if (clear_flag) {
                    clear_flag =false ;
                    str ="" ;
                    et_input.setText("");
                }else if (str!=null&&!str.equals("")){//如果文本中不为空且为数字时候此时删除
                    et_input.setText(str.substring(0,str.length()-1));//将文本中的文本长度减一 并将减去的数字置为0
                }
                break;
            case R.id.btn_clear:
                clear_flag =false ;
                str ="" ;//若按下的键是清除键，则将清除标志置为false 且将文本置为空
                et_input.setText("");
            case R.id.btn_equal:
                getResult();//若按下的键为=调用计算函数
                break ;

        }
    }
    /* 单独的调用运算结果
     *
     *
     * */
    private void getResult(){//计算函数
        String exp = et_input.getText().toString();//获取当前文本的数据
        if (exp == null||exp.equals("")){
            return;
        }
        if(!exp.contains(" ")) {//判断字符串中是否有子字符串
            return;
        }
        if (clear_flag){
            clear_flag = false ;
            return;//当前文本为空，返回空
            //若此时的清零标志为true 则重置 返回空

        }
        clear_flag = true ;//重置清零位
        double result = 0 ;
        String s1 = exp.substring(0,exp.indexOf(" ")); //第一个参数int为开始的索引，对应String数字中的开始位置，
       // 第二个参数是截止的索引位置，对应String中的结束位置  此处获取第一位操作数
        String op = exp.substring(exp.indexOf(" ")+1,exp.indexOf(" ")+2) ;//运算符
        String s2 = exp.substring(exp.indexOf(" ")+3) ;//获取第二位操作数
        if (!s1.equals("")&&!s2.equals("")){//当两位操作数均不为空时
            double d1 = Double.parseDouble(s1) ;
            double d2 = Double.parseDouble(s2) ;//将数字转换为double
            if (op.equals("+")){
                result = d1 + d2 ;//如果操作符为+

            }else  if (op.equals("-")){
                result = d1 - d2 ;//如果操作符为-

            }else  if (op.equals("*")){
                result = d1 * d2 ;//如果操作符为*

            }else  if (op.equals("/")){////如果操作符为/
                if(d2 == 0){
                    result = 0 ;
                }else {
                    result = d1/d2 ;
                }
            }
            et_input.setText(result+"");
        }//两个数字均不为空的情况结束

        else if (!s1.equals("")&&s2.equals("")){//当第一个操作数为不为空 第二个操作数为空时
            //et_input.setText(exp);//直接返回该第一个操作数字
            double d1 = Double.parseDouble(s1) ;
            if (op.equals("+")){
                result = d1 ;//根据操作符加减乘除
            }else  if (op.equals("-")){
                result = d1;
            }else  if (op.equals("*")){
                result = d1 ;
            }else  if (op.equals("/")){
                result = d1;
            }
            et_input.setText(result+"");
        }//第一个操作数不为空 第二个操作数为空的情况结束

        else if (s1.equals("")&&!s2.equals("")){//当第一个操作数为空  第二个操作数不为空
            double d2 = Double.parseDouble(s2) ;
            if (op.equals("+")){
                result = 0 + d2 ;//根据操作符加减乘除

            }else  if (op.equals("-")){
                result = 0 - d2 ;

            }else  if (op.equals("*")){
                result = 0 ;

            }else  if (op.equals("/")){
                result = 0 ;
            }
            et_input.setText(result+"");
        }//当第一个操作位数字为空 第二个操作数字不为空的情况结束

    }
}

