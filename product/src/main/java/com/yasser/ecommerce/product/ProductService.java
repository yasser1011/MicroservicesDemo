package com.yasser.ecommerce.product;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper mapper;

    public Long createProduct(
            ProductRequest request
    ) {
        var product = mapper.toProduct(request);
        return productRepository.save(product).getId();
    }

    public ProductResponse findById(Long id) {
        return productRepository.findById(id)
                .map(mapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + id));
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(mapper::toProductResponse)
                .collect(Collectors.toList());
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public List<ProductPurchaseResponse> purchaseProducts(
            List<ProductPurchaseRequest> request
    ) {
        var productIds = request
                .stream()
                .map(ProductPurchaseRequest::getProductId)
                .toList();
        var storedProducts = productRepository.findAllByIdInOrderById(productIds);
        if (productIds.size() != storedProducts.size()) {
            throw new RuntimeException("One or more products dont exist");
        }
        var sortedRequest = request
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::getProductId))
                .toList();
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        List<Product> productsToSave = new ArrayList<Product>();
        for (int i = 0; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = sortedRequest.get(i);
            if (product.getAvailableQuantity() < productRequest.getQuantity()) {
                throw new RuntimeException("Insufficient quantity for product id " + productRequest.getProductId());
            }
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.getQuantity();
            product.setAvailableQuantity(newAvailableQuantity);
//            productRepository.save(product);
            productsToSave.add(product);
            purchasedProducts.add(mapper.toProductPurchaseResponse(product, productRequest.getQuantity()));
        }
        productRepository.saveAll(productsToSave);
        return purchasedProducts;
    }
}
