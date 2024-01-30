package com.imaginaryproducts.lib.product.service;

import com.imaginaryproducts.lib.product.dao.ProductRepository;
import com.imaginaryproducts.lib.product.dto.ProductMapper;
import com.imaginaryproducts.lib.product.dto.ProductResponse;
import com.imaginaryproducts.lib.product.dto.PublicProductDTO;
import com.imaginaryproducts.lib.product.dto.PublicProductResponse;
import com.imaginaryproducts.lib.product.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceable implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    private final Logger logger = LoggerFactory.getLogger(ProductServiceable.class);

    /**
     * Takes a list of {@link Product}, and returns a mapped list to {@link PublicProductDTO}.
     *
     * @return Returns a {@link PublicProductDTO} list.
     * */
    @Override
    public List<PublicProductDTO> findAllPublic() {
        return productRepository.findAll().stream().map(ProductMapper::getPublicProductDTO).sorted(Comparator.comparing(PublicProductDTO::name)).collect(Collectors.toList());
    }

    /**
     * Returns a list of all {@link Product}.
     *
     * @return Returns a list of all {@link Product}.
     * */
    @Override
    public List<Product> findAll() {
        return productRepository.findAll().stream().sorted(Comparator.comparing(Product::getName)).collect(Collectors.toList());
    }

    /**
     * Returns a {@link Product} by ID.
     *
     * @param id ID of the {@link Product}.
     * @return Returns a {@link Product} by ID.
     * */
    @Override
    public Product findByUUID(UUID id) {
        Optional<Product> search = productRepository.findById(id);

        if (search.isEmpty()) throw new RuntimeException("User does not exist.");

        return search.get();
    }

    /**
     * Returns a response {@link PublicProductResponse}, that consists the following properties:
     * <ul>
     *     <li>Data: {@link PublicProductDTO} list</li>
     *     <li>Current page</li>
     *     <li>Last page</li>
     *     <li>Results per page</li>
     *     <li>Total results</li>
     * </ul>
     *
     * @param currentPage Represents the current page of the pagination.
     * @param resultsPerPage Represents the maximum displayed results of the pagination.
     * @return Returns a paginated {@link PublicProductDTO} list.
     * @throws Exception The {@link ProductServiceable#getPublicProductResponse(int, int)} does not handle errors, so do not break it.
     * */
    @Override
    public PublicProductResponse getPublicProductResponse(int currentPage, int resultsPerPage) {
        Pageable pageable = PageRequest.of(currentPage - 1, resultsPerPage, Sort.by("name"));
        Page<Product> page = productRepository.findAll(pageable);
        List<PublicProductDTO> data = page.getContent().stream().map(ProductMapper::getPublicProductDTO).sorted(Comparator.comparing(PublicProductDTO::name)).collect(Collectors.toList());

        int _currentPage = page.getNumber() + 1;
        int _lastPage = (int) (Math.ceil((double) page.getTotalElements() / page.getSize()));
        int _resultsPerPage = page.getSize();
        int _totalResults = (int) page.getTotalElements();

        return new PublicProductResponse(data, _currentPage, _lastPage, _resultsPerPage, _totalResults);
    }

    /**
     * Returns a response {@link ProductResponse}, that consists the following properties:
     * <ul>
     *     <li>Data: {@link Product} list</li>
     *     <li>Current page</li>
     *     <li>Last page</li>
     *     <li>Results per page</li>
     *     <li>Total results</li>
     * </ul>
     *
     * @param currentPage Represents the current page of the pagination.
     * @param resultsPerPage Represents the maximum displayed results of the pagination.
     * @return Returns a paginated {@link Product} list.
     * @throws Exception The {@link ProductServiceable#getProductResponse(int, int)} does not handle errors, so do not break it.
     * */
    @Override
    public ProductResponse getProductResponse(int currentPage, int resultsPerPage) {
        Pageable pageable = PageRequest.of(currentPage - 1, resultsPerPage, Sort.by("name"));
        Page<Product> page = productRepository.findAll(pageable);
        List<Product> data = page.getContent().stream().sorted(Comparator.comparing(Product::getName)).collect(Collectors.toList());

        int _currentPage = page.getNumber() + 1;
        int _lastPage = (int) (Math.ceil((double) page.getTotalElements() / page.getSize()));
        int _resultsPerPage = page.getSize();
        int _totalResults = (int) page.getTotalElements();

        return new ProductResponse(data, _currentPage, _lastPage, _resultsPerPage, _totalResults);
    }

    /**
     * Returns a saved {@link Product}.
     *
     * @param product Requires a {@link Product}.
     * @return Returns a saved {@link Product}.
     * */
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product, UUID id) {
        Optional<Product> search = productRepository.findById(id);

        if (search.isEmpty()) throw new RuntimeException("Product does not exist.");

        return productRepository.save(product);
    }

    /**
     * Remove a product after confirmation.
     *
     * @param id Represents the ID of {@link Product}.
     * */
    @Override
    public void delete(UUID id) {
        Optional<Product> search = productRepository.findById(id);

        if (search.isEmpty()) throw new RuntimeException("User does not exist.");

        productRepository.delete(search.get());
    }
}
