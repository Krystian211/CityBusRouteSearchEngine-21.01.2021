package com.github.krystian211.city.bus.route.search.engine.dao.impl;

import com.github.krystian211.city.bus.route.search.engine.dao.IEmployeeDAO;
import com.github.krystian211.city.bus.route.search.engine.model.Employee;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAOImpl implements IEmployeeDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void persistEmployee(Employee employee) {
        CommonDAOUtilities.persistObject(employee,sessionFactory);
    }
}
