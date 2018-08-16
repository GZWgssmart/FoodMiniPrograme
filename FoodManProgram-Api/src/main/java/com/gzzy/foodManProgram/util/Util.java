package com.gzzy.foodManProgram.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by hc on 2017/10/17.
 */
public class Util {

    public static String getFilePreviewUrl(Integer fileId){
        if(fileId == null){
            return null;
        }

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String previewUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/files/preview?fileId=" + (fileId == null ? 0 : fileId);
        return previewUrl;
    }

    public static String getNowYYYYMMDDHHMMSS() {
        String result = "";
        SimpleDateFormat l_sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        result = l_sdf.format(new Date());
        return result;
    }

    public static String getNowYYYYMMDD() {
        String result = "";
        SimpleDateFormat l_sdf = new SimpleDateFormat("yyyyMMdd");
        result = l_sdf.format(new Date());
        return result;
    }

    public static String getNowTm() {
        SimpleDateFormat l_sdf = new SimpleDateFormat("HHmmss");
        return l_sdf.format(new Date());
    }

    public static boolean isEmpty(final String str) {
        if (str == null) {
            return true;
        }
        if (str.trim().length() == 0) {
            return true;
        }
        return false;
    }

    public static String getToday(){
        Date today1=new Date();
        DateFormat fmt=new SimpleDateFormat("yyyyMMdd");
        String mydate=fmt.format(today1);
        return mydate;
    }
    public static Date getDateyyyyMMddHHmmss(final String strdate) {
        SimpleDateFormat l_format = (SimpleDateFormat) DateFormat.getInstance();
        l_format.applyPattern("yyyyMMddHHmmss");
        Date date = null;
        try {
            date = l_format.parse(strdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static int offsethourbyoneday(String startdate,String enddate){
        Date calstart = getDateyyyyMMddHHmmss(startdate);
        Date calend = getDateyyyyMMddHHmmss(enddate);

        if(calstart == null || calend == null){
            return 8;
        }

        Long offsethour = (calend.getTime() - calstart.getTime())/(60*60*1000);
        return offsethour.intValue();
    }

    //日期相减得到秒
    public static int getsecond(String startdate,String enddate){
        long date1=getDateyyyyMMddHHmmss(startdate).getTime();
        long date2=getDateyyyyMMddHHmmss(enddate).getTime();
        Long second = (date2-date1)/1000;
        return second.intValue();
    }

    //日期相减得到分钟
    public static int getminute(String startdate,String enddate){
        long date1=getDateyyyyMMddHHmmss(startdate).getTime();
        long date2=getDateyyyyMMddHHmmss(enddate).getTime();
        Long second = (date2-date1)/(1000*60);
        return second.intValue();
    }
    //日期相减得到天数
    public static int getDaySub(String startdate,String enddate){
        Long day = 0L;
        DateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate;
        Date endDate;
        try {
            beginDate = fmt.parse(startdate);
            endDate= fmt.parse(enddate);
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day.intValue();
    }

    public static Integer convertToInt(String str){
        try{
            return Integer.parseInt(str);
        }catch(Exception e){
            return 0;
        }
    }

    public static Float convertToFloat(String str){
        try{
            return Float.parseFloat(str);
        }catch(Exception e){
            return 0f;
        }
    }


    public static void main(String[] args) throws Exception {
        String a= "";
        for(int i=0; i<3; i ++){
            a += "、我" + i;
        }
        System.out.println(a);
//        System.out.println(a.substring(1));
//        List<OrderKanbaProcessListOutput> returnprocesslist = new ArrayList<>();
//        for(int i=0;i< 8;i++){
//            OrderKanbaProcessListOutput orderKanbaProcessListOutput = new OrderKanbaProcessListOutput();
//            returnprocesslist.add(orderKanbaProcessListOutput);
//        }
//        System.out.println(returnprocesslist.size());

    }

    //根据开始日期获取间隔天数之后的日期
    public static String getDateAfterDate(String startdate, int diffday){
        String day = "";
        SimpleDateFormat l_sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            Date date = l_sdf.parse(startdate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            calendar.add(Calendar.DAY_OF_MONTH, diffday);
            Date date1 = calendar.getTime();
            day = l_sdf.format(date1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return day;
    }

    public static String dateToString(Date date){
        String time = "";
        SimpleDateFormat l_sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        time = l_sdf.format(date);
        return time;
    }

    public static String dateToHHmmss(Date date){
        String time = "";
        SimpleDateFormat l_sdf = new SimpleDateFormat("HHmmss");
        time = l_sdf.format(date);
        return time;
    }


    public static String formatHHmm(Date date) {
        SimpleDateFormat l_sdf = new SimpleDateFormat("HH:mm");
        return l_sdf.format(date);
    }

    public static String getWeekdaydisp(String date){
        SimpleDateFormat l_sdf = new SimpleDateFormat("yyyyMMdd");
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        try {
            Date date1 = l_sdf.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date1);
            int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            if(w < 0){
                w = 0;
            }
            return weekDays[w];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    //  本周开始日
    public static String getStartweek(){

        Calendar calender = Calendar.getInstance();

        int week = calender.get(Calendar.DAY_OF_WEEK);

        int offset = 0;
        if(week == 1){
            offset = -6;
        } else if(week == 2){
            offset = 0;
        } else {
            offset = 2-week;
        }

        calender.add(Calendar.DAY_OF_MONTH, offset);

        return toStrDateYYYYMMDD(calender.getTime());
    }

    public static String getEndweek(){

        Calendar calender = Calendar.getInstance();

        int week = calender.get(Calendar.DAY_OF_WEEK);

        int offset = 0;
        if(week == 1){
            offset = 0;
        } else if(week == 2){
            offset = 6;
        } else {
            offset = 8-week;
        }

        calender.add(Calendar.DAY_OF_MONTH, offset);

        return Util.toStrDateYYYYMMDD(calender.getTime());
    }

    //  指定日期的周开始日
    public static String getStartweek(String datestr){

        Calendar calender = Calendar.getInstance();
        Date date = getDateyyyyMMdd(datestr);
        calender.setTime(date);

        int week = calender.get(Calendar.DAY_OF_WEEK);

        int offset = 0;
        if(week == 1){
            offset = -6;
        } else if(week == 2){
            offset = 0;
        } else {
            offset = 2-week;
        }

        calender.add(Calendar.DAY_OF_MONTH, offset);

        return toStrDateYYYYMMDD(calender.getTime());
    }

    //指定日期的周结束日
    public static String getEndweek(String datestr){

        Calendar calender = Calendar.getInstance();
        Date date = getDateyyyyMMdd(datestr);
        calender.setTime(date);

        int week = calender.get(Calendar.DAY_OF_WEEK);

        int offset = 0;
        if(week == 1){
            offset = 0;
        } else if(week == 2){
            offset = 6;
        } else {
            offset = 8-week;
        }

        calender.add(Calendar.DAY_OF_MONTH, offset);

        return Util.toStrDateYYYYMMDD(calender.getTime());
    }

    public static String toStrDateYYYYMMDD(Date date) {
        SimpleDateFormat l_format = (SimpleDateFormat) DateFormat.getInstance();
        l_format.applyPattern("yyyyMMdd");
        return l_format.format(date);
    }

    public static Date getDateyyyyMMdd(final String strdate) {
        SimpleDateFormat l_format = (SimpleDateFormat) DateFormat.getInstance();
        l_format.applyPattern("yyyyMMdd");
        Date date = null;
        try {
            date = l_format.parse(strdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static List<String> getdatelist(String startdate,String enddate){
        List<String> dates = new ArrayList<>();
        Date start = getDateyyyyMMdd(startdate);
        Calendar calenderstart = Calendar.getInstance();
        calenderstart.setTime(start);

        Date end = Util.getDateyyyyMMdd(enddate);
        Calendar calenderend = Calendar.getInstance();
        calenderend.setTime(end);
        if(end.before(start)){
            return dates;
        }
        do{
            dates.add(toStrDateYYYYMMDD(calenderstart.getTime()));
            calenderstart.add(Calendar.DAY_OF_MONTH, 1);
        } while(!calenderstart.after(calenderend));

        return dates;
    }


    /**
     * 获取纯工作时间秒数
     * @param startdtm   开始施工时间  14位
     * @param officetime 上班时间  6位
     * @param leavetime  下班时间  6位
     * @param dayhour    每天工作时长
     * @return 两头按秒数计算，中间日期按8小时计算
     */
    public static int getWorkhourSeconds(String startdtm, String enddtm, String officetime, String leavetime, BigDecimal dayhour){
        DateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
        int totalseconds = 0;
        try {
            Date date1 = fmt.parse((startdtm));
            Date date2 = fmt.parse((enddtm));
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(date1);
            calendar1.add(Calendar.DAY_OF_MONTH, 1);
            calendar1.set(Calendar.HOUR_OF_DAY, 0);
            calendar1.set(Calendar.MINUTE, 0);
            calendar1.set(Calendar.SECOND, 0);
            calendar1.set(Calendar.MILLISECOND, 0);
            Date date11 = calendar1.getTime();   //开始日期第二天的日期

            calendar1.setTime(date2);
            calendar1.add(Calendar.DAY_OF_MONTH, -1);
            calendar1.set(Calendar.HOUR_OF_DAY, 0);
            calendar1.set(Calendar.MINUTE, 0);
            calendar1.set(Calendar.SECOND, 0);
            calendar1.set(Calendar.MILLISECOND, 0);
            Date date22 = calendar1.getTime();  //结束日期前一天的日期
            if(date22.compareTo(date11)>=0){  //取中间间隔天数，直接乘以每日工时数
                int days = (int) ((date22.getTime() - date11.getTime()) / (1000*3600*24)) + 1;
                totalseconds += new BigDecimal(days).multiply(dayhour).multiply(new BigDecimal(3600)).intValue();
            }

            String date1ym = startdtm.substring(0, 8); //开始时间的日期
            String date2ym = enddtm.substring(0, 8);  //结束时间的日期
            String date1start = date1ym + officetime;  //开始时间的上班开始时间
            String date1end = date1ym + leavetime;  //开始时间的上班结束时间
            String date2start = date2ym + officetime;  //结束时间的开始上班时间
            String date2end = date2ym + leavetime;    //结束时间的下班时间

            if(date1ym.equals(date2ym)) {  //2个日期是同一天，需要单独计算
                if(date1start.compareTo(startdtm)>0){
                    startdtm = date1start;
                }
                if(date1end.compareTo(enddtm)<0){
                    enddtm = date1end;
                }

                int a = getsecond(startdtm, enddtm);
                totalseconds += a;
            } else if(date1ym.compareTo(date2ym) < 0){
                if(date1start.compareTo(startdtm)>0){
                    startdtm = date1start;
                }
                if(startdtm.compareTo(date1end) > 0){
                    startdtm = date1end;
                }
                int a = getsecond(startdtm, date1end);
                totalseconds += a;

                if(enddtm.compareTo(date2end) > 0){
                    enddtm = date2end;
                }
                if(date2start.compareTo(enddtm) > 0){
                    enddtm = date2start;
                }

                int b = getsecond(date2start, enddtm);
                totalseconds += b;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalseconds;
    }

    public static String getMD5Str(String str) {
        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance("MD5");

            messageDigest.reset();

            messageDigest.update(str.getBytes("UTF-8"));
        } catch (Exception e) {
            return "";
        }

        byte[] byteArray = messageDigest.digest();

        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }

        return md5StrBuff.toString();
    }


    public static String getStartDateByMonth(String datestr){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        try {
            Date date = format.parse(datestr);
            Calendar calender = Calendar.getInstance();
            calender.setTime(date);
            calender.set(Calendar.DAY_OF_MONTH, 1);

            return format.format(calender.getTime());
        }catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    public static String getEndDateByMonth(String datestr){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        try {
            Date date = format.parse(datestr);
            Calendar calender = Calendar.getInstance();
            calender.setTime(date);
            calender.add(Calendar.MONTH, 1);
            calender.set(Calendar.DAY_OF_MONTH, 1);
            calender.add(Calendar.DAY_OF_MONTH, -1);

            return format.format(calender.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    public static int getWeekOfDateNumber(String cuurdate) {
        Date date = getDateyyyyMMdd(cuurdate);
        Calendar calendar = Calendar.getInstance();
        if(date != null){
            calendar.setTime(date);
        }
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if(w==0){
            w=7;
        }
        return w;
    }


    public static String formartdispdate(String date){
        if(Util.isEmpty(date)){
            return "";
        } else {
            if(date.length() == 14){
                return getDateYYYYMMDDHHMMSS(date);
            } else if(date.length() == 12){
                return Util.getDateYYYYMMDDHHMMSS(date+"00");
            } else if(date.length() == 8){
                return Util.getDateDisp(date);
            } else{
                return "";
            }

        }
    }

    public static String getDateYYYYMMDDHHMMSS(String strdate){
        DateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
        DateFormat fmt2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mydate = "";
        if(strdate!=null&&!strdate.equals("")&&!"null".equals(strdate)){
            try {
                mydate = fmt2.format(fmt.parse((strdate)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return mydate;
    }

    public static String getDateDisp(String strdate){
        DateFormat fmt=new SimpleDateFormat("yyyyMMdd");
        DateFormat fmt2=new SimpleDateFormat("yyyy-MM-dd");
        String mydate = "";
        if(strdate!=null&&!strdate.equals("")){
            try {
                mydate = fmt2.format(fmt.parse((strdate)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return mydate;
    }

    //生成技师口令
    public static String getteachtoken(){
        int cnt  = 1;
        int max=150;
        int min=1;
        Random random = new Random();
        String domain = "";
        for(int index = 0 ;index <cnt;index++){
            int subindex = 0;
            while(domain.length() <4){
                int s = random.nextInt(max)%(max-min+1) + min;
                if(subindex == 0){
                    if((s>=97 && s<= 122)){
                        subindex++;
                        domain += (char)s;
                    }
                } else {
                    if((s>= 48 && s<= 57)|| (s>=97 && s<= 122)){

                        domain += (char)s;
                    }
                }

            }
        }
        return domain;
    }

    public static String numFormart4(Number data, String format, String unit, int datatype){
        if(data == null){
            return "0";
        }
        String priceStr = data.toString();
        BigDecimal decimalPrice = new BigDecimal(priceStr);
        DecimalFormat df1 = new DecimalFormat("##,##0.00");
        if(datatype == 1){
            return "￥".concat(df1.format(decimalPrice)).concat(unit);
        }
        return(df1.format(decimalPrice).concat(unit));
    }

    public static BigDecimal convertdecimal1(String price){
        if(Util.isEmpty(price)){
            return BigDecimal.ZERO;
        }
        BigDecimal decimalPrice = new BigDecimal(price);
        return decimalPrice;
    }

    public static String priceFormart1(Float price){
        if(price == null){
            return "0.00";
        }
        String priceStr = price.toString();
        BigDecimal decimalPrice = new BigDecimal(price);
        DecimalFormat df1 = new DecimalFormat("##0.00");

        return(df1.format(decimalPrice));
    }

    public static String getDateYYYYMMDDHHMMSS(String strdate, String format){
        if(Util.isEmpty(strdate)){
            return "";
        }
        DateFormat fmt = null;
        if(strdate.length() == 8){
            fmt = new SimpleDateFormat("yyyyMMdd");
        }else if(strdate.length() == 14){
            fmt = new SimpleDateFormat("yyyyMMddHHmmss");
        }else{
            return strdate;
        }
        DateFormat fmt2 = new SimpleDateFormat(format);
        String mydate = "";
        if(strdate!=null&&!strdate.equals("")&&!"null".equals(strdate)){
            try {
                mydate = fmt2.format(fmt.parse((strdate)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return mydate;
    }

    public static String numFormart5(Number dddd){
        if(dddd == null){
            return "0.00";
        }
        String priceStr = dddd.toString();
        BigDecimal decimalPrice = new BigDecimal(priceStr);
        DecimalFormat df1 = new DecimalFormat("##,##0.00");
        return(df1.format(decimalPrice));
    }

    /**
     * 读取文件字节
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static int getBytesFromFile(File file)  {
        InputStream is = null;
        try{
            is = new FileInputStream(file);
            long length = file.length();
            if (length > Integer.MAX_VALUE) {
                return 0;
            }
            byte[] bytes = new byte[(int) length];
            int offset = 0;
            int numRead = 0;
            while ((offset < bytes.length) && ((numRead = is.read(bytes, offset, bytes.length - offset)) >= 0)) {
                offset += numRead;
            }
            if (offset < bytes.length) {
                throw new IOException("Could not completely read file " + file.getName());
            }
            return bytes.length;
        }catch (Exception e){
            return 0;
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String appendZeroLeft(String strObj, int len){
        if(strObj == null){
            return "";
        }
        if(strObj.length() >= len){
            return strObj;
        }
        return (StringUtils.repeat("0", len - strObj.length()) + strObj);
    }

    public static String getStrDateForYYYYMMDDHHMMSS(Date date) {
        String result = "";
        if(date == null){
            return result;
        }
        SimpleDateFormat l_sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        result = l_sdf.format(date);
        return result;
    }

}
