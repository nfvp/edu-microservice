package pt.nos.ms.something.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.nos.ms.something.dao.SomethingDAO;

@Repository
public interface SomethingRepository extends JpaRepository<SomethingDAO, Long> {

    public List<SomethingDAO> findByOneNameOrAnotherName(String oneName, String anotherName);

}