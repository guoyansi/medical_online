package com.yb.medical_online.item.util;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

public class MyTool {
    public static boolean isNumber(String str) {
        return isInt(str) || isDouble(str);
    }
    public static boolean isDouble(String str){
        boolean isDouble = Pattern.compile("^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$").matcher(str).find();
        return isDouble;
    }
    public static boolean isInt(String str){
        boolean isInt = Pattern.compile("^-?[1-9]\\d*$").matcher(str).find();
        return isInt;
    }
    public static boolean isLong(String str){
        boolean isLong = Pattern.compile("^-?[1-9]\\d*$").matcher(str).find();
        return isLong;
    }
    public static <T> T swap(Object origin,T target){
        Field[] tFs = target.getClass().getDeclaredFields();//获得属性
        Field[] oFs = origin.getClass().getDeclaredFields();//获得属性
        for(int i=0;i<tFs.length;i++){
            String tname=tFs[i].getName();
            for(int j=0;j<oFs.length;j++){
                if(oFs[j].getName().equals(tname)){
                    Object val=getValue(origin, tname);
                    setValue(target, tname, val);
                }
            }
        }
        return target;
    }

    /**
     *
     * @param beanObj 实体类
     */
    private static Object getValue(Object beanObj,String name){
        try {
            Field[] fields = beanObj.getClass().getDeclaredFields();//获得属性
            Class clazz = beanObj.getClass();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                String names=field.getName();
                if(!names.equals(name)){
                    continue;
                }
                // 此处应该判断beanObj,property不为null
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                Method getMethod = pd.getReadMethod();
                if (getMethod != null) {
                    //System.out.println(beanObj+"的字段是:"+field.getName()+"，类型是："+field.getType()+"，取到的值是： "+getMethod.invoke(beanObj));
                    return getMethod.invoke(beanObj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     *
     * @param beanObj 实体类
     * @param name 字段名
     * @param value 值
     */
    private static void setValue(Object beanObj,String name,Object value){
        try {
            Field[] fields = beanObj.getClass().getDeclaredFields();//获得属性
            Class clazz = beanObj.getClass();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                String names=field.getName();
                if(!names.equals(name)){
                    continue;
                }

                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), beanObj.getClass());
                Method setMethod = pd.getWriteMethod();
                if (setMethod != null) {
                    setMethod.invoke(beanObj, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getMd5(String str) throws Exception{
        String result = "";
        //String str = "123456";
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update((str).getBytes("UTF-8"));
        byte b[] = md5.digest();

        int i;
        StringBuffer buf = new StringBuffer("");

        for(int offset=0; offset<b.length; offset++){
            i = b[offset];
            if(i<0){
                i+=256;
            }
            if(i<16){
                buf.append("0");
            }
            buf.append(Integer.toHexString(i));
        }

        result = buf.toString();
        return result;
    }
    public static Double toFixed(int i,Double d){
        if(d==null){
            return null;
        }
        String fixed="#.";
        for(int a=0;a<i;a++){
            fixed+="0";
        }
        DecimalFormat df = new DecimalFormat(fixed);
        String s=df.format(d);
        return Double.parseDouble(s);
    }

    /**
     * 分到元的转换
     * @param fen
     * @return
     */
    public static Double fenToYuan(Integer fen){
        if(fen==null){
            return null;
        }
        String fixed="#.";
        for(int a=0;a<2;a++){
            fixed+="0";
        }
        Double d=Double.parseDouble(fen.toString())/100;
        DecimalFormat df = new DecimalFormat(fixed);
        String s=df.format(d);
        return Double.parseDouble(s);
    }

    /**
     * 分到元的转换
     * @param fen
     * @return
     */
    public static Double fenToYuan(Long fen){
        if(fen==null){
            return null;
        }
        String fixed="#.";
        for(int a=0;a<2;a++){
            fixed+="0";
        }
        Double d=Double.parseDouble(fen.toString())/100;
        DecimalFormat df = new DecimalFormat(fixed);
        String s=df.format(d);
        return Double.parseDouble(s);
    }

    /**
     * 获取隔了多少天
     * @param startY
     * @param startM
     * @param startD
     * @param endY
     * @param endM
     * @param endD
     * @return
     */
    public static int getLongDay(int startY,int startM,int startD,int endY,int endM,int endD){
        Calendar start = Calendar.getInstance();
        start.set(startY, startM, startD);
        Calendar end = Calendar.getInstance();
        end.set(endY, endM, endD);
        long oneDay=24*60*60*1000L;
        long startTime=start.getTimeInMillis();
        long endTime=end.getTimeInMillis();
        Long longDay=(endTime-startTime)/oneDay;
        return longDay.intValue()+1;
    }

    /**
     * 获取工作日天数（去除周日，周六）
     * @param startY
     * @param startM
     * @param startD
     * @param endY
     * @param endM
     * @param endD
     * @return
     */
    public static int getWorkDay(int startY,int startM,int startD,int endY,int endM,int endD){
        int count = 0;
        startM++;
        endM++;
        Calendar cal1 = Calendar.getInstance();
        cal1.set(startY, startM, startD);

        Calendar cal2 = Calendar.getInstance();
        cal2.set(endY, endM, endD);

        boolean isAdd=true;
        if(cal1.getTimeInMillis()>cal2.getTimeInMillis()){
            isAdd=false;
        }

        int i=0;
        while (true) {
            Calendar cal = Calendar.getInstance();
            cal.set(startY, startM, startD);
            cal.add(Calendar.DATE, i);
            cal.setFirstDayOfWeek(Calendar.SUNDAY);

            int day = cal.get(Calendar.DAY_OF_WEEK)+1;
            if(day>=8){
                day=1;
            }
            if (!(day == Calendar.SUNDAY || day == Calendar.SATURDAY)) {//1,7
                count++;
            }
            if(isAdd){
                i++;
            }else{
                i--;
            }
            if(cal.get(Calendar.YEAR)==endY
                    &&cal.get(Calendar.MONTH)==endM
                    &&cal.get(Calendar.DATE)==endD){
                break;
            }
        }
        if(!isAdd){
            return -1*count;
        }
        return count;
    }

    /**
     * 获取前n个月，降序
     * @param n
     * @param y
     * @param m
     * @param format "yyyyMM"
     * @return
     */
    public static List<String> getBeforeMonthDesc(int n, int y, int m, String format){
        Calendar cal=Calendar.getInstance();
        cal.set(Calendar.YEAR, y);
        cal.set(Calendar.MONTH, m);
        List<String> list=new ArrayList<String>();
        SimpleDateFormat sdf=new SimpleDateFormat(format);
        for(int i=0;i<=n;i++){
            cal.add(Calendar.MONTH, -1);
            list.add(sdf.format(cal.getTime()));
        }
        return  list;
    }
    /**
     * 获取前n个月，升序
     * @param n
     * @param y
     * @param m
     * @return
     */
    public static List<String> getBeforeMonthAsc(int n, int y, int m,String format){
        List<String> list=getBeforeMonthDesc(n,y,m,format);
        List<String> listNew=new ArrayList<String>();
        for(int i=list.size()-1;i>=0;i--){
            listNew.add(list.get(i));
        }
        return  listNew;
    }

    /**
     * 输出excel
     * @param response
     * @param wb
     * @param fileName
     * @throws IOException
     */
    /*public static void outExcel(HttpServletResponse response, XSSFWorkbook wb, String fileName) throws IOException {
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        wb.write(bos);
        response.setBufferSize(512);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        SimpleDateFormat dfs=new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        fileName=fileName+".xlsx";
        response.addHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("UTF-8"), "ISO8859-1"));
        response.addHeader("Content-Length", String.valueOf(bos.toByteArray().length));
        OutputStream os=response.getOutputStream();
        IOUtils.write(bos.toByteArray(),os);
    }*/

    /**
     * 输出图片
     * @param response
     * @param image
     * @throws IOException
     */
    public static void outImg(HttpServletResponse response, BufferedImage image) throws IOException {
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
    }

    /**
     * 上传文件
     * @param file
     */
    public static void upload(MultipartFile file){

    }
    /*public static BufferedImage drawImg(String msg) throws Exception {
        // 字体大小
        int FONT_SIZE = 12;
        int height=80;
        int width=140;
        // 1.创建空白图片
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 2.获取图片画笔
        Graphics g = image.getGraphics();
        Font font= new Font("微软雅黑", Font.ITALIC , FONT_SIZE);
        AffineTransform affineTransform=new AffineTransform();
        affineTransform.rotate(Math.toRadians(-45),0,0);
        Font roratedFont=font.deriveFont(affineTransform);
        // 3.设置背景色
        g.setColor(Color.white);
        // 4.绘制矩形背景
        g.fillRect(0, 0, width, height);

        g.setColor(new Color(205,203,203,80));
        // 设置字体大小
        g.setFont(roratedFont);
        // 画字符
        g.drawString(msg,10, 80);
        //g.dispose();
        // 7.返回图片
        return  image;
    }*/


}
