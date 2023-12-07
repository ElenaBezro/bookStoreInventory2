package com.elena.bookStoreInventory2.bookManagement;

import com.elena.bookStoreInventory2.DBConnection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@SpringBootTest
class BookDaoTest {

    @Autowired
    private ApplicationContext context;
    @Autowired
    private BookDao bookDao;

    @Test
    public void testInitLifecycleMethod() {
        assertTrue(bookDao.isCreated());
    }
    @Test
    public void testDestroyLifecycleMethod() {
        bookDao.destroy();
        assertTrue(bookDao.isReadyForDestroy());
    }


}