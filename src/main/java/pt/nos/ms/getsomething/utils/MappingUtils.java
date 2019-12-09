package pt.nos.ms.getsomething.utils;

import pt.nos.ms.getsomething.dao.SomethingDAO;
import pt.nos.ms.getsomething.dto.SomethingDTO;

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
    
}
