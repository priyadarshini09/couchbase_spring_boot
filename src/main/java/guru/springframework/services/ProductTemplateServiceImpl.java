package guru.springframework.services;

import com.couchbase.client.java.view.ViewQuery;
import guru.springframework.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public class ProductTemplateServiceImpl implements ProductService{

    private static final String DESIGN_DOC = "product";

    private CouchbaseTemplate couchbaseTemplate;

    @Autowired
    public void setCouchbaseTemplate(CouchbaseTemplate couchbaseTemplate) {
        this.couchbaseTemplate = couchbaseTemplate;
    }
    @Override
    public List<Product> listAll() {
        return couchbaseTemplate.findByView(ViewQuery.from(DESIGN_DOC, "all"), Product.class);
    }

    @Override
    public Product getById(String id) {
        return couchbaseTemplate.findById(id, Product.class);
    }

    @Override
    public Product saveOrUpdate(Product product) {
        return null;
    }

    @Override
    public void delete(String id) {
        couchbaseTemplate.remove(id);
    }
}
