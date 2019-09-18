package com.ql;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanCopier;
@Slf4j
@Getter
@Setter
public class TestBeanCopier {

    @Test
    public void testDeepCopy(){
        A a = new A("a",new B(1), Lists.newArrayList(new C("c",1),new C("c2",2)));
        A1 a1 = new A1();
        BeanCopier beanCopier = BeanCopier.create(A.class,A1.class,false);
        beanCopier.copy(a,a1,null);
        log.info("");
    }

    @Test
    public void testCopy(){
        A a = new A("a",new B(1), Lists.newArrayList(new C("c",1),new C("c2",2)));
        A1 a1 = new A1();
        BeanUtils.copyProperties(a,a1);
        log.info("");
    }

    @Test
    public void testCopy2(){
        A a = new A("a",new B(1), Lists.newArrayList(new C("c",1),new C("c2",2)));
        A1 a1 = new A1();
        BeanUtils.copyProperties(a.getName(),a1.getName());
        BeanUtils.copyProperties(a.getB(),a1.getB());
        BeanUtils.copyProperties(a.getCList(),a1.getCList());
        log.info("");
    }

}
