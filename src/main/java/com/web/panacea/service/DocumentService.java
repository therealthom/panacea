package com.web.panacea.service;

import com.web.panacea.domain.Document;
import java.util.List;

public interface DocumentService {

    public abstract long countAllDocuments();

    public abstract void deleteDocument(Document document);

    public abstract Document findDocument(Long id);

    public abstract List<Document> findAllDocuments();

    public abstract List<Document> findDocumentEntries(int firstResult, int maxResults);

    public abstract void saveDocument(Document document);

    public abstract Document updateDocument(Document document);

}
