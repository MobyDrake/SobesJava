package lesson4.repository;

import lesson4.annotationBd.DbColumn;
import lesson4.annotationBd.DbEntity;
import lesson4.annotationBd.DbId;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class MyRepositoryImp<T> implements MyRepository<T> {

    private Connection connection;
    private Statement statement;
    private Class<T> clazz;
    private String tableName;

    public MyRepositoryImp(Class<T> type) {
        init();
        this.clazz = type;
        this.tableName = clazz.getAnnotation(DbEntity.class).table();
    }

    @Override
    public T saveOrUpdate(T t) {

        return null;
    }

    @Override
    public T getById(Long id) {
        try(PreparedStatement ps = connection.prepareStatement(String.format("SELECT * FROM %s WHERE id = ?", tableName))) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            return castIntoT(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private void init() {
        try {
            Class.forName("org.postgresql.Driver");
            String user = "postgres";
            String password = "postgres";
            String url = "jdbc:postgresql://localhost:5432/testdb";
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private T castIntoT(ResultSet rs) {
        T entity = null;
        DbColumn column;
        try {
            entity = (T) clazz.getDeclaredConstructor().newInstance();
            Field[] fields = clazz.getDeclaredFields();
            while (rs.next()) {
                for (Field f : fields) {
                    if (f.isAnnotationPresent(DbId.class)) {
                        f.setAccessible(true);
                        f.set(entity, rs.getLong("id"));
                    }
                    if (f.isAnnotationPresent(DbColumn.class)) {
                        f.setAccessible(true);
                        column = f.getAnnotation(DbColumn.class);
                        f.set(entity, rs.getObject(column.name()));
                    }
                }
            }

        } catch (InstantiationException | IllegalAccessException | SQLException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return entity;
    }
}
