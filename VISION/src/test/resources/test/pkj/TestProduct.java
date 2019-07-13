package test.pkj;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vision.erp.common.Search;
import com.vision.erp.service.domain.HumanResourceCard;
import com.vision.erp.service.domain.Product;
import com.vision.erp.service.domain.User;

import com.vision.erp.service.productionmanagement.rudwn.ProductionManagementDAOrudwn;
import com.vision.erp.service.user.UserDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:config/root-context.xml",
		"classpath:config/aspect-context.xml",
		"classpath:config/servlet-context.xml",
		"classpath:config/transaction-context.xml"
})
public class TestProduct{

	@Resource(name = "productionManagementDAOImplrudwn")
	private ProductionManagementDAOrudwn productDAO;

	//@Test
	public void testSelectDetailProduct() throws Exception {
		Product product = new Product();
		String productNo = "10006";

		product = productDAO.selectDetailProduct(productNo);

		System.out.println(product);

	}
	//@Test
	public void selectProductList() throws Exception {



		List<Product> list 
		= (List<Product>)productDAO.selectProductList();

		for(int i = 0; i<list.size(); i++) {
			Product product = list.get(i);
			System.out.println(product);
		}

	}
	
	//@Test
	public void updateProduct() throws Exception {
		Product product = new Product();
		
		product.setProductNo("10006");
		product.setPurchasePrice("300");
		product.setSalesPrice("500");
		product.setQuantity("800");
		
		System.out.println(product);

		productDAO.updateProduct(product);

	}
	
	//@Test
		public void updateUsageStatus() throws Exception {
			Product product = new Product();
			
			product.setProductNo("10006");
			product.setProductUsageStatusCodeNo("01");
			System.out.println(product);

			productDAO.updateUsageStatus(product);
			
			System.out.println(productDAO.selectDetailProduct("10006"));

		}
		
		//@Test
		public void addProduct() throws Exception {
			Product product = new Product();
			
			product.setProductNo("10007");
			product.setProductName("ÄÝ¶ó");
			product.setPurchasePrice("500");
			product.setSalesPrice("600");
			product.setQuantity("300");
			product.setVendorNo("v1002");
			product.setProductUsageStatusCodeNo("01");
			
			System.out.println(product);
			
			productDAO.addProduct(product);

		}
	
	

}
