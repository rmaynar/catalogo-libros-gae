package com.test.dao;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.appengine.api.search.Document;
import com.google.appengine.api.search.Field;
import com.google.appengine.api.search.Index;
import com.google.appengine.api.search.IndexSpec;
import com.google.appengine.api.search.Results;
import com.google.appengine.api.search.ScoredDocument;
import com.google.appengine.api.search.SearchServiceFactory;
import com.test.data.Libro;
public class ServicioBusqueda {
	
	private static final Logger LOGGER = Logger.getLogger(ServicioBusqueda.class.getName());

	private Index index;

    private PartialTextTokenizer partialTextTokenizer;

    public ServicioBusqueda() {
        IndexSpec indexSpec = IndexSpec.newBuilder().setName("Libro").build();
        index = SearchServiceFactory.getSearchService().getIndex(indexSpec);
        partialTextTokenizer = new PartialTextTokenizer(2);
    }

    public void indexar(Libro bean) {
    	Document doc = Document.newBuilder()
                .setId(String.valueOf(bean.getId()))
                .addField(Field.newBuilder().setName("nombre")
                        .setText(partialTextTokenizer.tokenize(bean.getNombre())))
                .addField(Field.newBuilder().setName("autor")
                        .setText(partialTextTokenizer.tokenize(bean.getAutor())))
                .build();
    	LOGGER.info("Nombre partial tokenized: " + partialTextTokenizer.tokenize(bean.getNombre()));

        index.put(doc);
    }

    public void desindexar(Libro bean) {
        index.delete(String.valueOf(bean.getId()));
    }

    public List<Long> buscar(String text) {
        String query = new StringBuilder()
                .append("nombre").append(" = ").append(text)
                .append(" OR ")
                .append("autor").append(" = ").append(text)
                .toString();

        Results<ScoredDocument> results = index.search(query);
        LOGGER.info("Encontrados "+results.getNumberReturned()+" resultados");
        List<Long> ids = new ArrayList<>();
        for (ScoredDocument document : results) {
            ids.add(Long.valueOf(document.getId()));
        }

        return ids;
    }
}
