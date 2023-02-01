package com.cinshop.admin.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cinshop.common.entity.Brand;
import com.cinshop.common.entity.Category;
import com.cinshop.common.entity.ProductDetail;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductUtility utility;

	public Page<ProductDetail> findAll(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	public Page<ProductDetail> findByText(String txt, Pageable pageable) {
		return productRepository.findByText(txt, pageable);
	}

	public Page<ProductDetail> findByBrandOrCategory(Integer brandId,Integer catId,Pageable pageable){
		return productRepository.findByBrandOrCategory(brandId, catId, pageable);
	}

	public List<Brand> findAllBrands() {
		return utility.findAllBrands();
	}

	public List<Category> findAllCategories() {
		return utility.findAllCategories();
	}
}
