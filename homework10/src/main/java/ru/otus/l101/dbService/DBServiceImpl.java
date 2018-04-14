package ru.otus.l101.dbService;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.otus.l101.base.DBService;
import ru.otus.l101.base.dataSets.AddressDataSet;
import ru.otus.l101.base.dataSets.UserDataSet;
import ru.otus.l101.dbService.dao.UserDAOImpl;

import java.util.function.Function;


public class DBServiceImpl implements DBService {
    private final SessionFactory sessionFactory;

    public DBServiceImpl() {
        Configuration configuration = new org.hibernate.cfg.Configuration();

        configuration.addAnnotatedClass(UserDataSet.class);
        configuration.addAnnotatedClass(AddressDataSet.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        configuration.setProperty("hibernate.connection.url", "jdbc:sqlserver://localhost:1434;databaseName=myORM");
        configuration.setProperty("hibernate.connection.username", "sa");
        configuration.setProperty("hibernate.connection.password", "1qaz!QAZ");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        configuration.setProperty("hibernate.connection.useSSL", "false");
        configuration.setProperty("hibernate.enable_lazy_load_no_trans", "true");

        sessionFactory = createSessionFactory(configuration);
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    private <R> R runInSession(Function<Session, R> function) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            R result = function.apply(session);
            transaction.commit();
            return result;
        }
    }

    public void save(UserDataSet dataSet) {
        try (Session session = sessionFactory.openSession()) {
            UserDAOImpl dao = new UserDAOImpl(session);
            dao.save(dataSet);
        }
    }

    public UserDataSet read(long id) {
        return runInSession(session -> {
            UserDAOImpl dao = new UserDAOImpl(session);
            return dao.read(id);
        });
    }

    @Override
    public void shutdown() {
        sessionFactory.close();
    }
}
