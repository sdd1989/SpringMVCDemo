package com.ql;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Function;
import com.google.common.collect.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

public class Main {

    static Random ran = new Random();

    public static void main(String[] args) {
//        Multimap<String,String> paramMap = ArrayListMultimap.create();
//        paramMap.put("a","1");
//        paramMap.put("a","2");
//        paramMap.put("a","3");
//        paramMap.put("a","4");
//        paramMap.put("a","5");
//        paramMap.put("b","22");
//        paramMap.put("c","33");
//        paramMap.put("d","44");
//        System.out.println(paramMap);
//        Map<String,String[]> map1 = Maps.newHashMap();
//        Map<String,Collection<String>> map = paramMap.asMap();
//        for(Map.Entry<String,Collection<String>> entry : map.entrySet()){
//            map1.put(entry.getKey(),entry.getValue().toArray(new String[0]));
//        }
//        System.out.println("==map1:"+ JSON.toJSONString(map1));
        List<TestVO> vos = Lists.newArrayList();
        for(int i=0;i<100;i++){
            TestVO vo = new TestVO();
//            int ranI = ran.nextInt(100);
//            vo.setId(ranI+"");
            vo.setId(i+"");
            vo.setName("name"+i);
            vos.add(vo);
        }
//        vos.add(null);
//        System.out.println(vos);
//        //list转multimap
        Multimap<String,TestVO> testVOMultimap = Multimaps.index(vos, new Function<TestVO, String>() {
            @Override
            public String apply(TestVO testVO) {
                return testVO.getId();
            }
        });
        System.out.println("==multipleMap===="+testVOMultimap);
//        list转map
        Map<String,TestVO> map = Maps.uniqueIndex(vos, new Function<TestVO, String>() {
            @Override
            public String apply(TestVO testVO) {
                return testVO.getId();
            }
        });
        System.out.println("===map===="+map);
//        System.out.println("===key->list===="+testVOMultimap.keySet());
//        System.out.println("===value->list===="+testVOMultimap.values());
//        Map hashMap = new HashMap();
//        hashMap.put(null,null);
//        hashMap.put(null,"sdd");
//        hashMap.put("sss",null);
//        hashMap.put("aaa","aaa");
//        hashMap.put("bbb","bbb");
//        hashMap.put("ccc","ccc");
//        System.out.println("===hashMap==="+hashMap);
//        Map linkedHashMap = new LinkedHashMap();
//        linkedHashMap.put(null,null);
//        linkedHashMap.put(null,"sdd");
//        linkedHashMap.put("sss",null);
//        linkedHashMap.put("cc",3);
//        linkedHashMap.put("aa",1);
//        linkedHashMap.put("bb",2);
//        System.out.println("===linkedHashMap==="+linkedHashMap);
//        Map treeMap = new TreeMap();
////        treeMap.put(null,null);
////        treeMap.put(null,"sdd");
//        treeMap.put("sss",null);
//        treeMap.put("c",3);
//        treeMap.put("b",2);
//        treeMap.put("a",1);
//        System.out.println("===treeMap==="+treeMap);

    }
}
