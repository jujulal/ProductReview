package my.product.ws.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import my.product.ws.model.Product;
import my.product.ws.service.ProductService;

@RestController
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/product/{id}", method = RequestMethod.GET)
	public ResponseEntity<Product> getProduct(Long id){
		Product product = productService.findOne(id);
		if(product == null){
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	//Creating new product
	@RequestMapping(value = "/api/product", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> createGreeting(@RequestBody Product product) {
		Product savedProduct = productService.create(product);
		return new ResponseEntity<Product>(savedProduct, HttpStatus.CREATED);
	}
	@RequestMapping(value="/api/showMsg", method = RequestMethod.POST, consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<String> showMsg(){
		return new ResponseEntity<String>(new String("Test msg"), HttpStatus.ACCEPTED);
	}
	
}
