package pt.nos.ms.something.utils;

import java.time.LocalDateTime;
import pt.nos.ms.something.dao.SomethingDAO;
import pt.nos.ms.something.dto.SomethingDTO;

public final class MappingUtils {
    
    public static SomethingDTO mapToSomethingDTO(SomethingDAO something) {
        SomethingDTO result = new SomethingDTO();
        result.setId(something.getId());
        result.setFullName(something.getOneName() + " " + something.getAnotherName());
        result.setTimestamp(something.getTimestamp());
        return result;
    }
    
    public static SomethingDAO mapToSomethingDAO(SomethingDTO something) {
        SomethingDAO result = new SomethingDAO();
        result.setId(something.getId());
        result.setOneName(something.getFullName().split(" ")[0]);
        result.setAnotherName(something.getFullName().split(" ")[1]);
        result.setTimestamp(something.getTimestamp());
        return result;
    }
    
    public static SomethingDTO mapToSomethingDTO(String something) {
        SomethingDTO result = new SomethingDTO();
        result.setId(Long.valueOf(something.split("\\|")[0]));
        result.setFullName(something.split("\\|")[1]);
        result.setTimestamp(LocalDateTime.parse(something.split("\\|")[2]));
        return result;
    }
    
}
