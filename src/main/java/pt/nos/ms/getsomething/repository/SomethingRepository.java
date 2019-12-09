package pt.nos.ms.getsomething.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.nos.ms.getsomething.dao.SomethingDAO;

@Repository
public interface SomethingRepository extends JpaRepository<SomethingDAO, Long> {

}