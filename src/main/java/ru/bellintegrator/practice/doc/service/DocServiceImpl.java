package ru.bellintegrator.practice.doc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.common.mapper.MapperFacade;
import ru.bellintegrator.practice.doc.dao.DocDao;
import ru.bellintegrator.practice.doc.dto.DocDto;
import ru.bellintegrator.practice.doc.model.Doc;

import java.util.List;

@Service
public class DocServiceImpl implements DocService {

    private final DocDao docDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public DocServiceImpl(DocDao docDao, MapperFacade mapperFacade) {
        this.docDao = docDao;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public List<DocDto> documents() {
        List<Doc> docs = docDao.documents();
        return mapperFacade.mapAsList(docs, DocDto.class);
    }
}
