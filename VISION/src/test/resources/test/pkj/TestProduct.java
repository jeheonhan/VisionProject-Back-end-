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
import com.vision.erp.service.productionmanagement.rudwn.ProductionManagementServicerudwn;
import com.vision.erp.service.user.UserDAO;
import com.vision.erp.service.user.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:config/root-context.xml",
		"classpath:config/aspect-context.xml",
		"classpath:config/servlet-context.xml",
		"classpath:config/transaction-context.xml"
})
public class TestProduct{

	@Resource(name = "productionManagementDAOImplrudwn")
	private ProductionManagementDAOrudwn productionDAO;
	
	@Resource(name = "productionManagementServiceImplrudwn")
	private ProductionManagementServicerudwn productionService;

	//@Test
	public void testSelectDetailProduct() throws Exception {
		Product product = new Product();
		String productNo = "10006";

		product = productionDAO.selectDetailProduct(productNo);

		System.out.println(product);

	}
	//@Test
	public void selectProductList() throws Exception {



		List<Product> list 
		= (List<Product>)productionDAO.selectProductList();

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

		productionDAO.updateProduct(product);

	}

	//@Test
	public void updateUsageStatus() throws Exception {
		Product product = new Product();

		product.setProductNo("10006");
		product.setProductUsageStatusCodeNo("01");
		System.out.println(product);

		productionDAO.updateUsageStatus(product);

		System.out.println(productionDAO.selectDetailProduct("10006"));

	}

	//@Test
	public void addProduct() throws Exception {
		Product product = new Product();

		
		product.setProductName("파워에이드");
		product.setPurchasePrice("700");
		product.setSalesPrice("800");
		product.setQuantity("400");
		product.setVendorNo("v1002");
		product.setProductUsageStatusCodeNo("01");

		System.out.println(product);

		productionDAO.addProduct(product);

	}

	//ServiceTest==========================================================================
	//ServiceTest==========================================================================
	//ServiceTest==========================================================================

	//@Test
	public void addProductService() throws Exception {
		Product product = new Product();

	
		product.setProductName("양파");
		product.setPurchasePrice("900");
		product.setSalesPrice("990");
		product.setQuantity("500");
		product.setVendorNo("v1001");
		product.setProductUsageStatusCodeNo("01");

		System.out.println(product);

		productionService.addProduct(product);

	}
	
	//@Test
	public void updateProductService() throws Exception {
		Product product = new Product();
		
		product.setProductNo("10023");
		product.setQuantity("100");
		product.setPurchasePrice("300");
		product.setSalesPrice("400");

		System.out.println(product);
		
		productionService.updateProduct(product);
	
	}
	
	//@Test
	public void updateUsageStatusService()throws Exception{
		Product product = new Product();
		
		product.setProductNo("10023");
		
		product.setProductUsageStatusCodeNo("01");
		
		System.out.println(product);
		
		productionService.updateUsageStatus(product);
		
	}
	
	//@Test
	public void selectProductListService() throws Exception{
		List<Product> list 
		= (List<Product>)productionService.selectProductList();

		for(int i = 0; i<list.size(); i++) {
			Product product = list.get(i);
			System.out.println(product);
		}
		
	}
	
	

}
