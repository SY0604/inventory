package com.example.inventorymanagerproj.services;

import com.example.inventorymanagerproj.DTOS.ProductDTO;
import com.example.inventorymanagerproj.entities.Category;
import com.example.inventorymanagerproj.entities.Inventory;
import com.example.inventorymanagerproj.entities.Product;
import com.example.inventorymanagerproj.errorhandlers.ResourceNotFoundException;
import com.example.inventorymanagerproj.repositories.CategoryRepository;
import com.example.inventorymanagerproj.repositories.InventoryRepository;
import com.example.inventorymanagerproj.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final InventoryRepository inventoryRepository;

    public ProductDTO createProduct(ProductDTO productDTO) {
        Category category = categoryRepository.findById(productDTO.getCategoryId())
            .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);

        Product savedProduct = productRepository.save(product);

        // Create initial inventory
        Inventory inventory = new Inventory();
        inventory.setProduct(savedProduct);
        inventory.setQuantity(productDTO.getQuantity() != null ? productDTO.getQuantity() : 0);
        inventoryRepository.save(inventory);

        return mapToDTO(savedProduct);
    }

    public List<ProductDTO> searchProducts(String name) {
        return productRepository.findByNameContainingIgnoreCase(name).stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }

    public List<ProductDTO> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId).stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }

    private ProductDTO mapToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setCategoryId(product.getCategory().getId());
        dto.setQuantity(product.getInventory() != null ? product.getInventory().getQuantity() : 0);
        return dto;
    }
} 