package lesson4.repository;

import lesson4.annotationBd.DbColumn;
import lesson4.annotationBd.DbId;
import lesson4.annotationBd.DbTable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReflectionRepository<T> {

    private Class<T> cl;
    private DbWorker dbWorker;

    private String tableName;
    private Field idField;
    private Method getId;
    private Method setId;
    private List<Field> tableFields;
    private List<Method> getters;
    private List<Method> setters;

    private String queryFindId;
    private String queryInsert;

    public ReflectionRepository(Class<T> type, DbWorker dbWorker) throws NoSuchMethodException {
        this.cl = type;
        this.dbWorker = dbWorker;
        this.init();
    }

    public void save(T t) throws InvocationTargetException, IllegalAccessException, SQLException {
        Object[] args = new Object[tableFields.size()];
        for (int i = 0; i < tableFields.size(); i++) {
            args[i] = getters.get(i).invoke(t);
        }
        dbWorker.execute(queryInsert, args);
    }

    public T findById(Long id) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, SQLException {
        T obj = cl.getConstructor().newInstance();
        try (ResultSet rs = dbWorker.select(queryFindId, id)) {
            if (rs.next()) {
                setId.invoke(obj, rs.getLong("id"));
                for (int i = 0; i < tableFields.size(); i++) {
                    setters.get(i).invoke(obj, rs.getObject(tableFields.get(i).getName()));
                }
            } else {
                return null;
            }
        }
        return obj;
    }

    private void init() throws NoSuchMethodException {
        if (!cl.isAnnotationPresent(DbTable.class)) {
            throw new RuntimeException("Invalid class structure: @DbTable missing");
        }

        tableName = cl.getAnnotation(DbTable.class).table();
        Field[] fields = cl.getDeclaredFields();
        tableFields = new ArrayList<>();
        getters = new ArrayList<>();
        setters = new ArrayList<>();
        for (Field f : fields) {
            if (f.isAnnotationPresent(DbId.class)) {
                if (idField != null) {
                    idField = f;
                } else {
                    throw new RuntimeException("Invalid class structure: too many @DbId");
                }
            }
            if (f.isAnnotationPresent(DbColumn.class)) {
                tableFields.add(f);
                getters.add(cl.getDeclaredMethod(generateGetterName(f.getName())));
                setters.add(cl.getDeclaredMethod(generateSetterName(f.getName())));
            }
        }
        getId = cl.getDeclaredMethod("getId");
        setId = cl.getDeclaredMethod("setId");

        StringBuilder sb = new StringBuilder();
        sb.append("select * from " + tableName + " where id = ?");
        queryFindId = sb.toString();
        sb.setLength(0);

        sb.append("insert into " + tableName + " (");
        for (Field f : tableFields) {
            sb.append(f.getName()).append(", ");
        }
        sb.setLength(sb.length() - 2);
        sb.append(") value (");
        for (int i = 0; i < tableFields.size(); i++) {
            sb.append("?, ");
        }
        sb.setLength(sb.length() - 2);
        sb.append(");");
        queryInsert = sb.toString();
    }

    private String generateGetterName(String name) {
        return "get" + Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }

    private String generateSetterName(String name) {
        return "set" + Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }
}
