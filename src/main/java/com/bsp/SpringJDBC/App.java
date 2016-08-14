package com.bsp.SpringJDBC;
//org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bsp.dao.CustomerDao;
import com.bsp.model.Customer;

public class App {
    private static ApplicationContext context;

	public static void main( String[] args ){
        context = new ClassPathXmlApplicationContext("Main.xml");
        
        CustomerDao dao = (CustomerDao) context.getBean("customerDAO");
        
        dao.insert(new Customer(1, "Russell Banks", "5160 Haas Junction", "rbanks@seesaa.net"));
        dao.insert(new Customer(2, "Tina Peters", "0023 Mandrake Pass", "tpeters1@rambler.ru"));
        dao.insert(new Customer(3, "Jeremy Ward", "6230 Kenwood Circle", "jward2@skyrock.com"));
        
        Customer customer1 = dao.findByCustomerId(1);
        System.out.println(customer1);
    }
}
