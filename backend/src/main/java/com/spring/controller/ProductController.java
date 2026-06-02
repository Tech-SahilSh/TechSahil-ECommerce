package com.spring.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.MyEcommerce1Application;
import com.spring.model.Product;
import com.spring.repository.ProductRepo;
import com.spring.service.ProductService;

import tools.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController 
{

    private final MyEcommerce1Application myEcommerce1Application;

    private final ProductRepo productRepo;
	@Autowired
	private ProductService productService;

    ProductController(ProductRepo productRepo, MyEcommerce1Application myEcommerce1Application) {
        this.productRepo = productRepo;
        this.myEcommerce1Application = myEcommerce1Application;
    }
	
	@GetMapping("/product")
	public ResponseEntity<List<Product>> getProducts()
	{
		return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProductbyId(@PathVariable int id)
	{
		Product product = productService.getProduct(id);
		
		if(product.getId() > 0)
			return new ResponseEntity<>(product, HttpStatus.OK);
		else 
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/product/{productId}/image")
	public ResponseEntity<byte[]> getProductImage(@PathVariable int productId)
	{
		Product product = productService.getProduct(productId);
		
		if(product.getId()>0)
		{
			return new ResponseEntity<>(product.getImageData(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value="/product",
            consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> addProduct(
       @RequestPart("product") String productJson,
       @RequestPart("imageFile") MultipartFile imageFile)
	{
		try
		{
			ObjectMapper mapper = new ObjectMapper();

			Product product =
               mapper.readValue(productJson, Product.class);

			Product savedProduct =
               productService.addOrUpdateProduct(product, imageFile);

			return ResponseEntity.status(HttpStatus.CREATED)
                            .body(savedProduct);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}
	
	@PutMapping(value="/product/{id}",
	        consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> updateProduct(
	        @PathVariable int id,
	        @RequestPart("product") String productJson,
	        @RequestPart("imageFile") MultipartFile imageFile) {

	    try {

	        ObjectMapper mapper = new ObjectMapper();

	        Product product =
	                mapper.readValue(productJson, Product.class);

	        product.setId(id);

	        Product updatedProduct =
	                productService.addOrUpdateProduct(product, imageFile);

	        return ResponseEntity.ok(updatedProduct);

	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.badRequest().body(e.getMessage());
	    }
	}
	
	@DeleteMapping("product/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable int id)
	{
		Product product = productService.getProduct(id);
		if(product != null)
		{
			productService.deleteProduct(id);
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Not Deleted", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/product/search")
	public ResponseEntity<List<Product>> searchProduct(@RequestParam String keyword)
	{
		List<Product> products= productService.searchPoducts(keyword);
		System.out.println("Searching for "+ keyword);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
}
