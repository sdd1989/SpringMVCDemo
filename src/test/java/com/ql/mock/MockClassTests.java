package com.ql.mock;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @Description TODO:
 * @Author qiuliang
 * @Time 2019-11-07 17:35
 * @Version 1.0
 **/
@RunWith(PowerMockRunner.class)
@PrepareForTest(PrivateMockDemo.class)
public class MockClassTests {

    @Mock
    private PrivateMockDemo privateMockDemo;

    @Test
    public void testPrivateMethod() throws Exception{

        PowerMockito.when(privateMockDemo,"testPrivate", Mockito.anyInt()).thenReturn(100);


    }
}
