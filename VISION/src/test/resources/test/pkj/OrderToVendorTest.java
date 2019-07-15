package test.pkj;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vision.erp.common.Search;
import com.vision.erp.service.domain.HumanResourceCard;
import com.vision.erp.service.domain.OrderToVendor;
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
public class OrderToVendorTest{

	@Resource(name = "productionManagementDAOImplrudwn")
	private ProductionManagementDAOrudwn productionDAO;

	@Test
	public void addOrderToVendor() throws Exception {
		OrderToVendor orderToVendor = new OrderToVendor();
	
		orderToVendor.setTotalAmount("5000");
		orderToVendor.setOrderToVenStatusCodeNo("01");

		System.out.println(orderToVendor);
		String statementNo = productionDAO.addOrderToVendor(orderToVendor);
		System.out.println("check :: " +statementNo);
		
		
	}
	//@Test
	public void selectOrderToVendorList() throws Exception {

		List<OrderToVendor> list 
		= (List<OrderToVendor>)productionDAO.selectOrderToVendorList();

		for(int i = 0; i<list.size(); i++) {
			OrderToVendor orderToVendor = list.get(i);
			System.out.println(orderToVendor);
		}

	}

	//@Test
	public void modifyOrderToVenCode() throws Exception {

		OrderToVendor orderToVendor = new OrderToVendor();
		
		orderToVendor.setOrderToVendorNo("1022");
		orderToVendor.setOrderToVenStatusCodeNo("01");
		
		
		System.out.println(orderToVendor);

		productionDAO.modifyOrderToVenCode(orderToVendor);

	}


	//==========================================================================
	
	
	


}
