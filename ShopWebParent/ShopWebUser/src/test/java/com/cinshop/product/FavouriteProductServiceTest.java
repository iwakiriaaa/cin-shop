package com.cinshop.product;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.cinshop.common.entity.Brand;
import com.cinshop.common.entity.Category;
import com.cinshop.common.entity.Color;
import com.cinshop.common.entity.FavouriteProduct;
import com.cinshop.common.entity.Product;
import com.cinshop.common.entity.Size;
import com.cinshop.dto.FavouriteProductDTO;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(value = false)
public class FavouriteProductServiceTest {

	@Autowired
	private FavouriteProductService service;

	@Test
	public void addFavTest() {
		service.addFavProduct(1, 1);
		service.addFavProduct(1, 1);
		service.addFavProduct(1, 2);
	}

	@Test
	public void getFavProductsByCustIdTest() {
		List<Product> favs = service.findAllByCustomerId(1);

		favs.forEach(fav -> System.out.println(fav));
		assertThat(favs.size()).isGreaterThan(0);
	}

	@Test
	public void removeFavTest() {
		service.removeFavProduct(2, 1);
	}

}
