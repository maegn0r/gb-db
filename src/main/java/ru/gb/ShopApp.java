package ru.gb;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.gb.config.DbConfig;
import ru.gb.dao.OldJdbcProductDao;
import ru.gb.dao.ProductDao;
import ru.gb.entity.Product;

public class ShopApp {

    public static void main(String[] args) {

        OldJdbcProductDao oldJdbcProductDao = new OldJdbcProductDao();
        for (Product product : oldJdbcProductDao.findAll()) {
            System.out.println(product);
        }
        System.out.println("-----------------------");
        System.out.println(oldJdbcProductDao.findById(5L));
        System.out.println("-----------------------");
        System.out.println(oldJdbcProductDao.findTitleById(5L));

        //для остальных реализаций: (для hibernate надо еще поменять AnnotationConfigApplicationContext)

//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DbConfig.class);
//        ProductDao productDao = context.getBean(ProductDao.class);
//        System.out.println(productDao.findTitleById(5L));
//        System.out.println("-----------------------");
//        System.out.println(productDao.findById(5L));
//        System.out.println("-----------------------");
//        for (Product product : productDao.findAll()) {
//            System.out.println(product);
//        }

    }
}
