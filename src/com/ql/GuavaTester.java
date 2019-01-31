package com.ql;

import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * ------------------------------------
 *
 * @author qiuliang02@meituan.com
 * @date 2018/6/21
 * @time 上午10:24
 * ------------------------------------
 */
public class GuavaTester {
//    public static void main(String args[]){
//        List<Integer> numbers = new ArrayList<Integer>();
//        numbers.add(new Integer(5));
//        numbers.add(new Integer(2));
//        numbers.add(new Integer(15));
//        numbers.add(new Integer(51));
//        numbers.add(new Integer(53));
//        numbers.add(new Integer(35));
//        numbers.add(new Integer(45));
//        numbers.add(new Integer(32));
//        numbers.add(new Integer(43));
//        numbers.add(new Integer(16));
//        Ordering ordering = Ordering.from(new Comparator<Integer>() {
//
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o1.intValue() - o2.intValue();
//            }
//        });
//        System.out.println("=================");
//        System.out.println(ordering.max(numbers.iterator()));
//        System.out.println(ordering.min(numbers.iterator()));
//        Collections.sort(numbers,ordering);
//        System.out.println(numbers);
//        System.out.println(ordering.binarySearch(numbers,16));
//        System.out.println(ordering.greatestOf(numbers,2));
//        System.out.println(ordering.leastOf(numbers,2));
//        System.out.println(Lists.partition(numbers,3));
//
//
////        Ordering ordering = Ordering.natural();
////        System.out.println("Input List: ");
////        System.out.println(numbers);
////
////        Collections.sort(numbers,ordering );
////        System.out.println("Sorted List: ");
////        System.out.println(numbers);
////
////        System.out.println("======================");
////        System.out.println("List is sorted: " + ordering.isOrdered(numbers));
////        System.out.println("Minimum: " + ordering.min(numbers));
////        System.out.println("Maximum: " + ordering.max(numbers));
////
////        Collections.sort(numbers,ordering.reverse());
////        System.out.println("Reverse: " + numbers);
////
////        numbers.add(null);
////        System.out.println("Null added to Sorted List: ");
////        System.out.println(numbers);
////
////        Collections.sort(numbers,ordering.nullsFirst());
////        System.out.println("Null first Sorted List: ");
////        System.out.println(numbers);
////        System.out.println("======================");
////
////        List<String> names = new ArrayList<String>();
////        names.add("Ram");
////        names.add("Shyam");
////        names.add("Mohan");
////        names.add("Sohan");
////        names.add("Ramesh");
////        names.add("Suresh");
////        names.add("Naresh");
////        names.add("Mahesh");
////        names.add(null);
////        names.add("Vikas");
////        names.add("Deepak");
////
////        System.out.println("Another List: ");
////        System.out.println(names);
////
////        Collections.sort(names,ordering.nullsFirst().reverse());
////        System.out.println("Null first then reverse sorted list: ");
////        System.out.println(names);
//    }

//    public static void main(String args[]){
//        //create a cache for employees based on their employee id
//        LoadingCache employeeCache =
//                CacheBuilder.newBuilder()
//                        .maximumSize(100) // maximum 100 records can be cached
//                        .expireAfterAccess(30, TimeUnit.MINUTES) // cache will expire after 30 minutes of access
//                        .build(new CacheLoader<String,Employee>(){ // build the cacheloader
//                            @Override
//                            public Employee load(String empId) throws Exception {
//                                //make the expensive call
//                                return getFromDatabase(empId);
//                            }
//                        });
//
//        try {
//            //on first invocation, cache will be populated with corresponding
//            //employee record
//            System.out.println("Invocation #1");
//            System.out.println(employeeCache.get("100"));
//            System.out.println(employeeCache.get("103"));
//            System.out.println(employeeCache.get("110"));
//            //second invocation, data will be returned from cache
//            System.out.println("Invocation #2");
//            System.out.println(employeeCache.get("100"));
//            System.out.println(employeeCache.get("103"));
//            System.out.println(employeeCache.get("110"));
//
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static Employee getFromDatabase(String empId){
//        Employee e1 = new Employee("Mahesh", "Finance", "100");
//        Employee e2 = new Employee("Rohan", "IT", "103");
//        Employee e3 = new Employee("Sohan", "Admin", "110");
//
//        Map database = new HashMap();
//        database.put("100", e1);
//        database.put("103", e2);
//        database.put("110", e3);
//        System.out.println("Database hit for" + empId);
//        return (Employee) database.get(empId);
//    }
    public static void main(String args[]){
        GuavaTester guavaTester = new GuavaTester();

        Integer value1 =  null;
        Integer value2 =  new Integer(10);
        //Optional.fromNullable - allows passed parameter to be null.
        Optional<Integer> a = Optional.fromNullable(value1);
        //Optional.of - throws NullPointerException if passed parameter is null
        Optional<Integer> b = Optional.of(value2);

        System.out.println(guavaTester.sum(a,b));
    }

    public Integer sum(Optional<Integer> a, Optional<Integer> b){
        //Optional.isPresent - checks the value is present or not
        System.out.println("First parameter is present: " + a.isPresent());

        System.out.println("Second parameter is present: " + b.isPresent());

        //Optional.or - returns the value if present otherwise returns
        //the default value passed.
        Integer value1 = a.or(new Integer(0));

        //Optional.get - gets the value, value should be present
        Integer value2 = b.get();

        return value1 + value2;
    }
}

class Employee {
    String name;
    String dept;
    String emplD;

    public Employee(String name, String dept, String empID){
        this.name = name;
        this.dept = dept;
        this.emplD = empID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDept() {
        return dept;
    }
    public void setDept(String dept) {
        this.dept = dept;
    }
    public String getEmplD() {
        return emplD;
    }
    public void setEmplD(String emplD) {
        this.emplD = emplD;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(Employee.class)
                .add("Name", name)
                .add("Department", dept)
                .add("Emp Id", emplD).toString();
    }
}
