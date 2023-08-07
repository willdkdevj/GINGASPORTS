package br.com.supernova.tech.gingasports.interfaces.util;

import br.com.supernova.tech.gingasports.domain.player.dto.ResponseMapDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class ControllerUtil {

    public static Page<ResponseMapDTO> convertListByPage(Pageable pageable, List<ResponseMapDTO> listResponse){
        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), listResponse.size());
        final Page<ResponseMapDTO> page = new PageImpl<>(listResponse.subList(start, end), pageable, listResponse.size());

        return page;
    }
}
