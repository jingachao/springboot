package cn.itrip.common;

import lombok.extern.apachecommons.CommonsLog;
import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * 汉字转拼音的工具类
 */
@CommonsLog
public class ChineseToEnglish {


    public static String getPinYin(String word){
        char c=word.charAt(0);
        String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c);
        log.info("word:"+word+" pingyin:"+pinyinArray[0].substring(0,pinyinArray[0].length()-1));
        return  pinyinArray[0].substring(0,pinyinArray[0].length()-1);
    }

    public static void main(String args[]){
        String result=getPinYin("北京");
    }
}