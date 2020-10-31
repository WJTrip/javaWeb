package dao;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class CreateVCImage {
    private static final int width=100;
    private static final int height=30;
    private static final int length=4;  //验证码长度
    private static final int lineCount=20; //干扰线数目

    //验证码的字符库，去掉难以辨别的o01I等字符
    private static final String str="23456789" +
                                    "ABCDEFGHJKLMNOPQRSTUVWXYZ" +
                                    "abcdefghjklmnopqrstuvwxyz";
    private static Random random=new Random();
    /// 语法：public char charAt(int index)
    /// 参数：index--字符的索引
    /// 返回值：返回指定索引处的字符

    ///int java.util.Random.nextInt(int bound)
    ///返回一个从随机数生成序列提取的均匀分布在0（包括）和bound指定的值（不包括）的伪随机数。

    ///append()方法 相当于"+"
    public String createCode(){
        ///使用StringBuilder类创建的字符串可以改变（不用创建新的对象）
        StringBuilder code=new StringBuilder();
        for (int i=0;i<length;i++){
            char c=str.charAt(random.nextInt(str.length()));
            code.append(c);
        }
        return code.toString();
    }

    //获取不同颜色
    public Color getColor(){
        ///rgb配色表Color构造函数中的三个参数都是0~255
        return new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
    }

    //获取字体样式
    public Font getFont(){
        return new Font("Fixedly", Font.BOLD,22);
    }

    //绘制字符
    public void drawChar(Graphics g,String code){
        g.setFont(getFont());
        for(int i=0;i<length;i++){
            char c=code.charAt(i);
            ///setColor(color c)    将文字，边框或要填充的区域为指定颜色
            g.setColor(getColor());
            ///drawString(String str , int x , int y)
            ///用预先设置好的颜色和字体来绘制文本str，文本左下角的坐标为(x,y)
            g.drawString(c+"",20*i+10,20);
        }
    }

    //绘制随机线
    public void drawLine(Graphics g){
        int x=random.nextInt(width);
        int y=random.nextInt(height);
        int x1=random.nextInt(13);
        int y1=random.nextInt(15);
        g.setColor(getColor());
        g.drawLine(x,y,x+x1,y+y1);
    }

    //绘制验证码图片
    public BufferedImage CreateVCodeImage(String vCode){
        //获取画笔
        BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
        Graphics g=image.getGraphics();
        //设置背景颜色并绘制矩形背景
        g.setColor(Color.WHITE);
        ///fillRect(int x , int y , int width , int height)
        ///用预先指定的颜色填充矩形
        g.fillRect(0,0,width,height);
        //验证码的绘制
        drawChar(g,vCode);
        //随机线的绘制
        for(int i=0;i<lineCount;i++){
            drawLine(g);
        }
        //绘制图片
        g.dispose();
        //返回生成的图片
        return image;
    }
}
