package com.ql;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import org.apache.commons.io.IOUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.util.*;

/**
 * qiuliang02
 */
public class MultiReadHttpServletRequest extends HttpServletRequestWrapper {



    //缓存的输入流
//    private ByteArrayOutputStream cachedBytes;


    private byte[] bytes;

    private byte[] bytesCopy;

    //缓存的paramMap
    private Map<String, String[]> paramMap;

    public MultiReadHttpServletRequest(HttpServletRequest request) {
        super(request);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        if (bytes == null) {
//            cacheInputStream();
            cacheInputAndParamterMap();
        }
        return new CachedServletInputStream();
    }
   //TODO:编码问题
    private void cacheInputAndParamterMap() throws IOException {
        bytes = new byte[this.getContentLength()];
        bytesCopy = new byte[this.getContentLength()];
        IOUtils.read(super.getInputStream(),bytes);
        System.arraycopy(bytes,0,bytesCopy,0,this.getContentLength());
        //post && contenttype:application/x-www-form-urlcoded
        if("POST".equalsIgnoreCase(this.getMethod())) {
            String str = IOUtils.toString(bytes, "utf-8");
            paramMap = parse(str);
        }
    }

    //TODO:checkbox  这种多个值的需要注意
    private Map<String,String[]> parse(String str) {
        Multimap<String,String> paramMap = ArrayListMultimap.create();
        if(str!=null && !str.isEmpty()){
            String [] strs = str.split("&");
            for(String s : strs){
                String[] ss = s.split("=");
                if(ss.length == 2) {
                    paramMap.put(ss[0], ss[1]);
                }
            }
        }
        Map<String,String[]> map1 = Maps.newHashMap();
        Map<String,Collection<String>> map = paramMap.asMap();
        for(Map.Entry<String,Collection<String>> entry : map.entrySet()){
            map1.put(entry.getKey(),entry.getValue().toArray(new String[0]));
        }
        return map1;
    }

    @Override
    public BufferedReader getReader() throws IOException{
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        if(paramMap == null) {
            cacheParamMap();
        }
        return paramMap;
    }

    /**
     * 获取所有参数名, get相关方法重写
     *
     * @return 返回所有参数名
     */
    @Override
    public Enumeration<String> getParameterNames() {
        if(paramMap == null) {
            cacheParamMap();
        }
        Vector<String> vector = new Vector<>(paramMap.keySet());
        return vector.elements();
    }

    /**
     * 获取指定参数名的值，如果有重复的参数名，则返回第一个的值 接收一般变量 ，如text类型
     *
     * @param name
     *            指定参数名
     * @return 指定参数名的值
     */
    @Override
    public String getParameter(String name) {
        if(paramMap == null) {
            cacheParamMap();
        }
        String[] results = paramMap.get(name);
        if (results == null || results.length <= 0) {
            return null;
        } else {
            return results[0];
        }
    }

    /**
     * 获取指定参数名的所有值的数组，如：checkbox的所有数据
     * 接收数组变量 ，如checkobx类型
     */
    @Override
    public String[] getParameterValues(String name) {
        if(paramMap == null) {
            cacheParamMap();
        }
        String[] results = paramMap.get(name);
        if (results == null || results.length <= 0) {
            return null;
        } else {
            return results;
        }
    }


    private void cacheParamMap() {
        paramMap = super.getParameterMap();
    }

//    private void cacheInputStream() throws IOException {
//        /* Cache the inputstream in order to read it multiple times. For
//         * convenience, I use apache.commons IOUtils
//         */
//        cachedBytes = new ByteArrayOutputStream();
//        IOUtils.copy(super.getInputStream(), cachedBytes);
//    }

    /* An inputstream which reads the cached request body */
    public class CachedServletInputStream extends ServletInputStream {
        private ByteArrayInputStream input;

        public CachedServletInputStream() {
            /* create a new input stream from the cached request body */
            input = new ByteArrayInputStream(bytesCopy);
        }

        @Override
        public boolean isFinished() {
            return true;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener readListener) {

        }

        @Override
        public int read() throws IOException {
            return input.read();
        }
    }
}
