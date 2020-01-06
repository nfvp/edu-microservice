package pt.nos.ms.something.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pt.nos.ms.something.dao.SomethingDAO;

@Repository
public interface SomethingRepository extends JpaRepository<SomethingDAO, Long> {
    
    public List<SomethingDAO> findByOneNameOrAnotherName(String oneName, String anotherName);
    
    @Query(value = "SELECT * FROM T_SOMETHING WHERE SMT_ONE_NAME = 'Something' AND SMT_ANOTHER_NAME = :name", nativeQuery = true)
    public List<SomethingDAO> findSimilarSomething(@Param("name") String userName);

}
