package com.ql.list;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import javax.annotation.Nullable;
import java.util.List;

@Slf4j
public class TestListsTransform {


    public static void main(String[] args) {

        List<Student> students = Lists.newArrayList(Student.builder().id(1).name("sdd").build()
                ,Student.builder().id(2).name("meiling").build()
                );

        List<Employ> employs = Lists.transform(students, new Function<Student, Employ>() {
            @Nullable
            @Override
            public Employ apply(@Nullable Student input) {
                Employ employ = new Employ();
                BeanUtils.copyProperties(input,employ);
                return employ;
            }
        });
        System.out.println("before employs"+employs);
        for(Employ employ : employs){
            employ.setName("xxxxx");
        }
        System.out.println("after employs"+employs);

    }

}
