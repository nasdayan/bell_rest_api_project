package ru.bellintegrator.practice.doc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.doc.dao.DocDao;
import ru.bellintegrator.practice.doc.dto.DocDto;
import ru.bellintegrator.practice.doc.model.Doc;

import java.util.ArrayList;
import java.util.List;

@Service
public class DocServiceImpl implements DocService {

    private final DocDao docDao;

    @Autowired
    public DocServiceImpl(DocDao docDao) {
        this.docDao = docDao;
    }

    @Override
    public List<DocDto> documents() {
        List<Doc> docs = docDao.documents();
        List<DocDto> docDtoList = new ArrayList<>(docs.size());
        for (Doc doc : docs) {
            DocDto docDto = new DocDto();
            docDto.setName(doc.getName());
            docDto.setCode(doc.getCode());
            docDtoList.add(docDto);
        }
        return docDtoList;
    }
}
