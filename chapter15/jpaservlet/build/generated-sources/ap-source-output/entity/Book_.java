package entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-08-03T16:23:32")
@StaticMetamodel(Book.class)
public class Book_ { 

    public static volatile SingularAttribute<Book, String> author;
    public static volatile SingularAttribute<Book, BigDecimal> price;
    public static volatile SingularAttribute<Book, Long> id;
    public static volatile SingularAttribute<Book, String> title;

}