package com.nicolasortiz.ecommerce.controller;

import com.nicolasortiz.ecommerce.model.dto.ResponseDto;
import com.nicolasortiz.ecommerce.model.entity.Product;
import com.nicolasortiz.ecommerce.model.entity.ProductCategory;
import com.nicolasortiz.ecommerce.service.ICategoryService;
import com.nicolasortiz.ecommerce.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;
    private final ICategoryService categoryService;

    // ------------ Products ------------

    // Buscar todos con paginación
    @GetMapping
    public ResponseEntity<ResponseDto> findAll(Pageable pageable){
        return ResponseEntity.ok()
                .body(ResponseDto.builder()
                        .message("Productos encontrados")
                        .response(productService.findAll(pageable))
                        .build());
    }

    // Buscar producto por su ID
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> findProductById(@PathVariable int id){
        return ResponseEntity.ok()
                .body(ResponseDto.builder()
                        .message("Producto encontrado")
                        .response(productService.findById(id))
                        .build());
    }

    // Buscar productos por nombre de categoría y con paginación (param. categoryName required)
    @GetMapping("/search")
    public ResponseEntity<ResponseDto> findProductsByCategoryName(
            @RequestParam("category") String category,
            Pageable pageable){
        return ResponseEntity.ok()
                .body(ResponseDto.builder()
                        .message("Productos encontrados")
                        .response(productService.findByCategoryName(pageable, category))
                        .build());
    }

    // Guardar un producto
    @PostMapping
    public ResponseEntity<ResponseDto> saveProduct(@RequestBody Product product){
        return ResponseEntity.ok()
                .body(ResponseDto.builder()
                        .message("Producto creado correctamente")
                        .response(productService.save(product))
                        .build());
    }

    // Actualizar un producto
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateProduct(@PathVariable int id, @RequestBody Product product){
        return ResponseEntity.ok()
                .body(ResponseDto.builder()
                        .message("Producto actualizado correctamente")
                        .response(productService.update(id, product))
                        .build());
    }

    // Eliminar un producto por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteProductById(@PathVariable int id){
        return ResponseEntity.ok()
                .body(ResponseDto.builder()
                        .message("Producto eliminado correctamente")
                        .response(productService.deleteById(id))
                        .build());
    }

    // ------------ Categories ------------

    // Buscar todas las categorías
    @GetMapping("/categories")
    public ResponseEntity<ResponseDto> findAllCategories(){
        return ResponseEntity.ok()
                .body(ResponseDto.builder()
                        .message("Categorias encontradas")
                        .response(categoryService.findAll())
                        .build());
    }

    // Buscar categoría por su ID
    @GetMapping("/categories/{id}")
    public ResponseEntity<ResponseDto> findCategoryById(@PathVariable int id){
        return ResponseEntity.ok()
                .body(ResponseDto.builder()
                        .message("Categoria encontrada")
                        .response(categoryService.findById(id))
                        .build());
    }

    // Guardar una categoría
    @PostMapping("/categories")
    public ResponseEntity<ResponseDto> saveCategory(@RequestBody ProductCategory category){
        return ResponseEntity.ok()
                .body(ResponseDto.builder()
                        .message("Categoria guardada correctamente")
                        .response(categoryService.save(category))
                        .build());
    }

    // Actualizar una categoría
    @PutMapping("/categories/{id}")
    public ResponseEntity<ResponseDto> updateCategory(@PathVariable int id,
                                                      @RequestBody ProductCategory category){
        return ResponseEntity.ok()
                .body(ResponseDto.builder()
                        .message("Categoria editada correctamente")
                        .response(categoryService.update(id, category))
                        .build());
    }

    // Eliminar una categoría
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<ResponseDto> deleteCategoryById(@PathVariable int id){
        return ResponseEntity.ok()
                .body(ResponseDto.builder()
                        .message("Categoria eliminada correctamente")
                        .response(categoryService.delete(id))
                        .build());
    }

    // ------------ Stock ------------

}
