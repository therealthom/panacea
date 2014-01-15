package com.web.panacea.service;

import com.web.panacea.domain.Document;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DocumentServiceImpl implements DocumentService {

    public long countAllDocuments() {
        return Document.countDocuments();
    }

    public void deleteDocument(Document document) {
        document.remove();
    }

    public Document findDocument(Long id) {
        return Document.findDocument(id);
    }

    public List<Document> findAllDocuments() {
        return Document.findAllDocuments();
    }

    public List<Document> findDocumentEntries(int firstResult, int maxResults) {
        return Document.findDocumentEntries(firstResult, maxResults);
    }

    public void saveDocument(Document document) {
        document.persist();
    }

    public Document updateDocument(Document document) {
        return document.merge();
    }
}
