package kr.ac.hansung.cse.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import kr.ac.hansung.cse.model.Offer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class OfferDao {

    // EntityManager를 의존성 주입받기 위해 @PersistenceContext 어노테이션 사용
    @PersistenceContext
    private EntityManager entityManager;

    public Offer getOffer(String name) {
        // JPQL을 사용하여 조건 쿼리문 생성 & 실행 -> name으로 Offer 객체를 조회
        // o 는 Offer 객체를 의미. 별칭임.
        return entityManager.createQuery("SELECT o FROM Offer o WHERE o.name = :name", Offer.class)
                // named parameter: 이름이 name인 파라미터 세팅
                .setParameter("name", name)
                .getSingleResult();
    }

    public Offer getOffer(int id) {
        return entityManager.find(Offer.class, id);
    }

    public List<Offer> getOffers() {
        return entityManager.createQuery("SELECT o FROM Offer o", Offer.class)
                .getResultList();
    }

    public void insert(Offer offer) {
        entityManager.persist(offer);
    }

    public void update(Offer offer) {
        entityManager.merge(offer);
    }


    public void delete(int id) {
        Offer offer = entityManager.find(Offer.class, id);
        if (offer != null) {
            entityManager.remove(offer);
        }
    }

}
/*@Repository //16:22
public class OfferDao {
 private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int getRowCount() {
        String sqlStatement= "select count(*) from offers";
        return jdbcTemplate.queryForObject(sqlStatement, Integer.class);

    }

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

}*/
