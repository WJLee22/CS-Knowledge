package kr.ac.hansung.cse.dao;
// DAO(Data Access Object) 계층은 데이터베이스와의 상호작용을 담당하는 객체지향 API를 제공하는 계층 이다.
// 즉 데이터베이스에 접근하기위한 API를 제공하는 객체이다. so, 서비스 계층에서 이 Dao의 API를 활용하여 DB에서 데이터를 가져오고, 그 데이터를 가공하여 비지니스 로직을 수행하는 것이다.
// DAO는 데이터베이스에 직접 접근하여 데이터를 가져오고, 그 결과를 Service에게 리턴한다.
// Service는 DAO를 호출하여 비지니스 로직을 수행하고, 그 결과를 Controller에게 리턴한다.
// 즉 DAO 객체는 DB 와 Service 사이의 중간다리 역할을 한다.

import kr.ac.hansung.cse.model.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
// @Repository 어노테이션은 이 클래스가 DAO빈 임을 나타낸다.
// dao-context.xml 설정파일에서 컴포넌트 스캔 을 통해 @Repository 어노테이션이 붙은 이 클래스를 찾아서 DAO 빈으로써 스프링 컨테이너에 생성시킨다.

@Repository //16:22
public class OfferDao {
    // jdbc에 있는 JdbcTemplate 클래스를 사용하여 데이터베이스에 접근한다.
    // JdbcTemplate 클래스는 JDBC API를 사용하여 데이터베이스에 접근하는데 필요한 여러가지 기능을 제공한다.
    // psa(portable service abstraction): 하단에는 mysql, postgresql, oracle 등등 다양한 DBMS가 존재하지만, 스프링에서 그것들을 추상화한 계층.
    // service 계층에서 CRUD 메서드를 이용할 수 있도록 api를 제공하는 JdbcTemplate 클래스.
    // DAO가 바로 이 스프링에서 제공되는 JdbcTemplate 클래스를 사용 -> JdbcTemplate은 JDBC Driver를 사용 -> JDBC Driver는 DBMS에 접근하여 DB에서 데이터를 가져온다.
    // 필요한 의존성이 3개가 있는데,
    // 1. spring-jdbc: JdbcTemplate 클래스와 관련된 의존성
    // 2. JDBC Driver-mysql-connector-java: DBMS에 접근하기 위한 의존성
    // 3. DataSource-commons-dbcp2: 에플리케이션에서 DB에 접근하려고하면 각종 configuration정보가 필요한데, 예를 들어 DB에 접근하려면 username, password, url, driver class name 등등이 필요하다. 이 정보들을 DataSource라는 객체에 담아서 JdbcTemplate에게 전달한다.
    private JdbcTemplate jdbcTemplate;  // psa(portable service abstraction);

    // @Autowired 어노테이션을 사용하여 DataSource를 주입받는다.
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int getRowCount() {
        String sqlStatement= "select count(*) from offers";
        return jdbcTemplate.queryForObject(sqlStatement, Integer.class);

    }

    //데이터베이스를 사용할때 CRUD operation을 사용한다. 마치 수학의 사칙연산처럼. Create, Read, Update, Delete.
    // Create: 데이터베이스에 데이터를 삽입하는 작업
    // Read: 데이터베이스에서 데이터를 조회하는 작업
    // Update: 데이터베이스에 있는 데이터를 수정하는 작업
    // Delete: 데이터베이스에 있는 데이터를 삭제하는 작업

    // Read에 해당하는 getOffers() 메서드
    // query and return single object
    public Offer getOffer(String name) {

        String sqlStatement= "select * from offers where name=?";
        return jdbcTemplate.queryForObject(sqlStatement, new Object[] {name},
                new RowMapper<Offer>() {

                    @Override
                    public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {

                        Offer offer= new Offer();

                        offer.setId(rs.getInt("id"));
                        offer.setName(rs.getString("name"));
                        offer.setEmail(rs.getString("email"));
                        offer.setText(rs.getString("text"));

                        return offer;
                    }
                });
    }

    // Read에 해당하는 getOffers() 메서드
    // query and return multiple objects
    public List<Offer> getOffers() {

        String sqlStatement= "select * from offers";
        return jdbcTemplate.query(sqlStatement, new RowMapper<Offer>() {

            @Override
            public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {

                Offer offer= new Offer();

                offer.setId(rs.getInt("id"));
                offer.setName(rs.getString("name"));
                offer.setEmail(rs.getString("email"));
                offer.setText(rs.getString("text"));

                return offer;
            }
        });
    }

    // Create에 해당하는 insertOffer() 메서드
    public boolean insert(Offer offer) {

        String name= offer.getName();
        String email= offer.getEmail();
        String text = offer.getText();

        String sqlStatement= "insert into offers (name, email, text) values (?,?,?)";

        return (jdbcTemplate.update(sqlStatement, new Object[] {name, email, text}) == 1);
    }

    // update에 해당하는 update() 메서드
    public boolean update(Offer offer) {

        int id = offer.getId();
        String name= offer.getName();
        String email= offer.getEmail();
        String text = offer.getText();

        String sqlStatement= "update offers set name=?, email=?, text=? where id=?";

        return (jdbcTemplate.update(sqlStatement, new Object[] {name, email, text, id}) == 1);
    }

    // delete에 해당하는 delete() 메서드
    public boolean delete(int id) {
        String sqlStatement= "delete from offers where id=?";
        return (jdbcTemplate.update(sqlStatement, new Object[] {id}) == 1);
    }

}
