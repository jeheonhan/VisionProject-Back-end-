package com.vision.erp.web.productionmanagementrudwn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vision.erp.service.domain.OrderToVendor;
import com.vision.erp.service.domain.OrderToVendorProduct;
import com.vision.erp.service.domain.Product;
import com.vision.erp.service.productionmanagement.rudwn.ProductionManagementServicerudwn;


@RestController
@RequestMapping("/production/*")
public class ProductionManagementControllerrudwn {
	
	@Autowired
	@Qualifier("productionManagementServiceImplrudwn")
	private ProductionManagementServicerudwn productionService;
	
	@RequestMapping(value = "json/addProduct",method=RequestMethod.POST)
	public void addProduct(@RequestBody Product product)throws Exception{
		System.out.println("product ::" + product);
		productionService.addProduct(product);
	}
	
	@RequestMapping(value = "json/updateProduct",method=RequestMethod.POST)
	public void updateProduct(@RequestBody Product product)throws Exception{
		productionService.updateProduct(product);
	}
	
	@RequestMapping(value = "json/updateUsageStatus",method=RequestMethod.POST)
	public void updateUsageStatus(@RequestBody Product product)throws Exception{
		productionService.updateUsageStatus(product);
	}
	
	@RequestMapping(value = "json/selectProductList",method=RequestMethod.GET)
	public List<Product> selectProductList() throws Exception{
		List<Product> list 
		= (List<Product>)productionService.selectProductList();

		for(int i = 0; i<list.size(); i++) {
			Product product = list.get(i);
			System.out.println(product);
		}
		
		return list;
	}
	
	//======================================================발주
	
	
	@RequestMapping(value = "json/selectOrderToVendorList",method=RequestMethod.GET)
	public List<OrderToVendor> selectOrderToVendorList() throws Exception{
		List<OrderToVendor> list 
		= (List<OrderToVendor>)productionService.selectOrderToVendorList();

		for(int i = 0; i<list.size(); i++) {
			OrderToVendor orderToVendor = list.get(i);
			System.out.println(orderToVendor);
		}
		
		return list;
	}
	
	@RequestMapping(value = "json/modifyOrderToVenCode",method=RequestMethod.POST)
	public void modifyOrderToVenCode(@RequestBody OrderToVendor orderToVendor) throws Exception {
		
		productionService.modifyOrderToVenCode(orderToVendor);
	}
	
	@RequestMapping(value = "json/orderToVendorDetailList",method=RequestMethod.POST)
	public List<OrderToVendorProduct> orderToVendorDetailList(@RequestBody OrderToVendorProduct orderToVendorProduct) throws Exception{
		System.out.println("orderToVendorProduct check ::: " + orderToVendorProduct);
		List<OrderToVendorProduct> list 
		= (List<OrderToVendorProduct>)productionService.orderToVendorDetailList(orderToVendorProduct);

		for(int i = 0; i<list.size(); i++) {
			OrderToVendorProduct orderToVendorProduct1 = list.get(i);
			System.out.println(orderToVendorProduct1);
		}
		
		return list;
	}
	
	//상태변경시 물품추가되야함
	//서비스단에서 dao2번가야함. 도메인과 해당 prodNo가져와야함.
	@RequestMapping(value = "json/modifyOrderToVenItemCode",method=RequestMethod.POST)
	public void modifyOrderToVenItemCode(@RequestBody OrderToVendorProduct orderToVendorProduct , @PathVariable String prodNo) throws Exception{
		productionService.modifyOrderToVenItemCode(orderToVendorProduct);
		
		Product product = new Product();
		product.setProductNo(prodNo);
		product.setQuantity(orderToVendorProduct.getQuantity());
		
		productionService.updateProductCount(product);
		
		
	}
	
}
