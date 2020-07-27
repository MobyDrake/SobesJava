package lesson4;

import lesson4.annotationBd.DbColumn;
import lesson4.annotationBd.DbEntity;
import lesson4.annotationBd.DbId;

@DbEntity(table = "user_tbl")
public class User {
    @DbId
    private Long id;
    @DbColumn(name = "name")
    private String name;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
