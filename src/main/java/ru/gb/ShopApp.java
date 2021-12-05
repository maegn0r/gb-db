package ru.gb;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.gb.config.HibernateConfig;
import ru.gb.dao.ManufacturerDao;
import ru.gb.dao.OldJdbcManufacturerDao;
import ru.gb.entity.Manufacturer;

public class ShopApp {



    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);
        ManufacturerDao manufacturerDao = context.getBean(ManufacturerDao.class);
        System.out.println(manufacturerDao.findNameById(3L));
        System.out.println("-----------------------");
        System.out.println(manufacturerDao.findById(3L));
        System.out.println("-----------------------");
        for (Manufacturer manufacturer : manufacturerDao.findAll()) {
            System.out.println(manufacturer);
        }

    }
}
