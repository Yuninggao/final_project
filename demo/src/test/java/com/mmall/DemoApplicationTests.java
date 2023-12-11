package com.mmall;

import com.mmall.Vo.ProductCategoryVo;
import com.mmall.service.ProductCategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	private ProductCategoryService ProductCategoryService;

	@Test
	void contextLoads() {
		List<ProductCategoryVo> productCategoryVoList = this.ProductCategoryService.buildProductCategoryMenue();
		int i = 0;
	}

}
