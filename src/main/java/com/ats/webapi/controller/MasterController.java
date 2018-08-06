package com.ats.webapi.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.webapi.commons.Firebase;
import com.ats.webapi.model.CategoryList;
import com.ats.webapi.model.ConfigureFranchisee;
import com.ats.webapi.model.FrListForSupp;
import com.ats.webapi.model.FrTarget;
import com.ats.webapi.model.FrTargetList;
import com.ats.webapi.model.FrTotalSale;
import com.ats.webapi.model.FranchiseSup;
import com.ats.webapi.model.FranchiseSupList;
import com.ats.webapi.model.GetItemSup;
import com.ats.webapi.model.GetRegSpCakeOrders;
import com.ats.webapi.model.GetSpCkSupplement;
import com.ats.webapi.model.Info;
import com.ats.webapi.model.Item;
import com.ats.webapi.model.ItemSup;
import com.ats.webapi.model.ItemSupList;
import com.ats.webapi.model.PostFrItemStockDetail;
import com.ats.webapi.model.PostFrItemStockHeader;
import com.ats.webapi.model.RegularSpCkOrders;
import com.ats.webapi.model.SpCake;
import com.ats.webapi.model.SpCakeSupplement;
import com.ats.webapi.model.SubCategory;
import com.ats.webapi.model.SubCategoryRes;
import com.ats.webapi.model.tally.FranchiseeList;
import com.ats.webapi.model.tray.TrayType;
import com.ats.webapi.repository.ConfigureFrRepository;
import com.ats.webapi.repository.FrListForSuppRepository;
import com.ats.webapi.repository.FranchiseSupRepository;
import com.ats.webapi.repository.FranchiseeRepository;
import com.ats.webapi.repository.GetSpCakeSupRepository;
import com.ats.webapi.repository.ItemRepository;
import com.ats.webapi.repository.ItemSupRepository;
import com.ats.webapi.repository.PostFrOpStockDetailRepository;
import com.ats.webapi.repository.PostFrOpStockHeaderRepository;
import com.ats.webapi.repository.SpCakeListRepository;
import com.ats.webapi.repository.SpCkDeleteOrderRepository;
import com.ats.webapi.repository.SubCategoryRepository;
import com.ats.webapi.repository.SubCategoryResRepository;
import com.ats.webapi.service.FranchiseeService;
import com.ats.webapi.service.ItemService;
import com.ats.webapi.service.OrderService;
import com.ats.webapi.service.PostFrOpStockService;
import com.ats.webapi.service.RegularSpCkOrderService;
import com.ats.webapi.service.SpecialCakeService;
  
@RestController
public class MasterController {

	@Autowired
	ItemService itemService;
	
	@Autowired
	ItemSupRepository itemSuppRepository;
	
	@Autowired
	SubCategoryResRepository subCategoryResRepository;
	
	@Autowired
	FranchiseeService franchiseeService;
	
	@Autowired
	RegularSpCkOrderService regularSpCkOrderService;
	
	@Autowired
	FranchiseeRepository franchiseeRepository;
	
	@Autowired
	SpCkDeleteOrderRepository spCkDeleteOrderRepository;
	
	@Autowired
	SpecialCakeService spCakeService;
	
	@Autowired
	FrListForSuppRepository frListForSuppRepository;
	
	@Autowired
	SpCakeListRepository spCakeListRepository;
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	FranchiseSupRepository franchiseSupRepository;
	
	@Autowired
	private SubCategoryRepository subCategoryRepository;
	
	@Autowired
	ConfigureFrRepository configureFrRepository;
	

	@Autowired
	PostFrOpStockHeaderRepository postFrOpStockHeaderRepository;

	@Autowired
	GetSpCakeSupRepository getSpCakeSupRepository;
	
	@Autowired
	PostFrOpStockDetailRepository postFrOpStockDetailRepository;

	// ----------------------------GET FrToken--------------------------------
	@RequestMapping(value = { "/getFrToken" }, method = RequestMethod.POST)
	public @ResponseBody String getFrToken(@RequestParam("frId") int frId) {
		
	  String frToken=franchiseSupRepository.findTokenByFrId(frId);

	  return frToken;
	}
	//-------------------------------------------------------------------------
	// ----------------------------SAVE Item Sup---------------------------
		@RequestMapping(value = { "/saveItemSup" }, method = RequestMethod.POST)
		public @ResponseBody Info saveItemSup(@RequestBody ItemSup itemSup) {

			ItemSup itemSupRes = null;
			Info info = new Info();
			try {

				itemSupRes = itemService.saveItemSup(itemSup);

				if (itemSupRes != null) {
					info.setError(false);
					info.setMessage("ItemSup Saved Successfully.");
				} else {
					info.setError(true);
					info.setMessage("ItemSup Not Saved .");
				}

			} catch (Exception e) {

				info.setError(true);
				info.setMessage("ItemSup Not Saved .");

				e.printStackTrace();
				System.out.println("Exception In MasterController /saveItemSup" + e.getMessage());

			}
			return info;

		}
      //---------------------------------------------------------------------------
		
		// ----------------------------SAVE Sub Category---------------------------
				@RequestMapping(value = { "/saveSubCategory" }, method = RequestMethod.POST)
				public @ResponseBody Info saveSubCategory(@RequestBody SubCategoryRes subCategory) {

					SubCategoryRes subCategoryRes = null;
					Info info = new Info();
					try {

						subCategoryRes = subCategoryResRepository.save(subCategory);

						if (subCategoryRes != null) {
							info.setError(false);
							info.setMessage("SubCategoryRes Saved Successfully.");
						} else {
							info.setError(true);
							info.setMessage("SubCategoryRes Not Saved .");
						}

					} catch (Exception e) {

						info.setError(true);
						info.setMessage("SubCategoryRes Not Saved .");

						e.printStackTrace();
						System.out.println("Exception In MasterController /saveSubCategory" + e.getMessage());

					}
					return info;

				}
		      //---------------------------------------------------------------------------
				// ------------------------Delete SubCategoryRes------------------------------------
				@RequestMapping(value = { "/deleteSubCategory" }, method = RequestMethod.POST)
				public @ResponseBody Info deleteSubCategory(@RequestParam("subCatId") int subCatId) {

					int isDeleted = subCategoryResRepository.deleteSubCategory(subCatId);
					Info infoRes=new Info();
					if(isDeleted>=1)
					{
						infoRes.setError(false);
						infoRes.setMessage("SubCategoryRes Deleted");
					}
					else
					{
						infoRes.setError(true);
						infoRes.setMessage("SubCategoryRes Deletion Failed");
					}
					return infoRes;
				}
		        //------------------------------------------------------------------------
				@RequestMapping(value = { "/getSubCategory" }, method = RequestMethod.POST)
				public @ResponseBody SubCategoryRes getSubCategory(@RequestParam("subCatId") int subCatId) {

					SubCategoryRes getSubCategoryRes = null;
			    	try {
						getSubCategoryRes = subCategoryResRepository.findBySubCatId(subCatId);

					} catch (Exception e) {
						getSubCategoryRes=new SubCategoryRes();
				    	e.printStackTrace();
					}
					return getSubCategoryRes;

				}
		// ----------------------------SAVE SpCake Sup---------------------------
				@RequestMapping(value = { "/saveSpCakeSup" }, method = RequestMethod.POST)
				public @ResponseBody Info saveSpCakeSup(@RequestBody SpCakeSupplement spCakeSupplement) {

					SpCakeSupplement spCakeSupplementRes = null;
					Info info = new Info();
					try {

						spCakeSupplementRes = spCakeService.saveSpCakeSup(spCakeSupplement);

						if (spCakeSupplementRes != null) {
							info.setError(false);
							info.setMessage("SpCakeSupplement Saved Successfully.");
							
							try {
							    List<String> frTokens=franchiseSupRepository.findTokens();

							 for(String token:frTokens) {
					          Firebase.sendPushNotifForCommunication(token,"Special Cake Details Updated","Changes have been made in OPS at item level, SP level, in the rates. Kindly refer the OPS for exact changes made.","updateList");
							 }
					         }
					         catch(Exception e2)
					         {
						       e2.printStackTrace();
					         }
						} else {
							info.setError(true);
							info.setMessage("SpCakeSupplement Not Saved .");
						}

					} catch (Exception e) {

						info.setError(true);
						info.setMessage("SpCakeSupplement Not Saved .");

						e.printStackTrace();
						System.out.println("Exception In MasterController /saveSpCakeSup" + e.getMessage());

					}
					return info;

				}
		 //---------------------------------------------------------------------------
		// ------------------------Delete SpCake Sup------------------------------------
		@RequestMapping(value = { "/deleteSpCakeSup" }, method = RequestMethod.POST)
		public @ResponseBody Info deleteSpCakeSup(@RequestParam("id") List<Integer> id) {

			Info info = spCakeService.deleteSpCakeSup(id);
			return info;
		}
        //------------------------------------------------------------------------
		// ------------------------------------------------------------
		@RequestMapping(value = { "/updateItemHsnAndPer" }, method = RequestMethod.POST)
		public @ResponseBody Info updateItemHsnAndPer(@RequestParam("items") List<Integer> items,@RequestParam("itemHsncd")String itemHsncd,@RequestParam("itemTax1")double itemTax1,@RequestParam("itemTax2")double itemTax2,@RequestParam("itemTax3")double itemTax3) {
			Info info=null;
			try {
				System.err.println(items+"hsn"+itemHsncd+"t1"+itemTax1+"t2"+itemTax2+"t3"+itemTax3);
			if(!items.isEmpty()) {				System.err.println(items+"hsn"+itemHsncd+"t1"+itemTax1+"t2"+itemTax2+"t3"+itemTax3);
 				
			for(Integer id:items) {
					System.err.println(id+"hsn"+itemHsncd+"t1"+itemTax1+"t2"+itemTax2+"t3"+itemTax3);

			     int isUpdate=itemRepository.updateItemHsnAndPerInItem(id,itemTax1,itemTax2,itemTax3);
			     try { 
					 int isUpdated = itemSuppRepository.updateItemHsnAndPerInSup(id,itemHsncd);
				 }catch (Exception e) {
						e.printStackTrace();
					}
				    
				}
				info=new Info();
				info.setError(false);
				info.setMessage("Updated");
			}

		
			}
			catch (Exception e) {
				e.printStackTrace();
				info=new Info();
				info.setError(true);
				info.setMessage("Updation Failed");
			}
			return info;
		}
        //------------------------------------------------------------------------
		
		// ------------------------Delete ItemSup------------------------------------
		@RequestMapping(value = { "/deleteItemSup" }, method = RequestMethod.POST)
		public @ResponseBody Info deleteItemSup(@RequestParam("id") List<String>  itemId) {

			Info info = itemService.deleteItemSup(itemId);
			return info;
		}
        //------------------------------------------------------------------------
		// ---------------------------Getting ItemSup List-----------------------
		@RequestMapping(value = { "/getItemSupList" }, method = RequestMethod.GET)
		public @ResponseBody ItemSupList getItemSupList() {

			ItemSupList itemSupList = itemService.getItemSupList();

			return itemSupList;

		}
		//------------------------------------------------------------------------
		// ---------------------------Getting SpCakeSup List-----------------------
		@RequestMapping(value = { "/getSpCakeSuppList" }, method = RequestMethod.GET)
		public @ResponseBody List<GetSpCkSupplement> getSpCakeSupList() {

			List<GetSpCkSupplement> spCakeSupplementList = spCakeService.getSpCakeSupList();

			return spCakeSupplementList;

		}
		//------------------------------------------------------------------------
		// ---------------------------Getting SpCakeList List-----------------------
				@RequestMapping(value = { "/getSpCakeList" }, method = RequestMethod.GET)
				public @ResponseBody List<SpCake> getSpCakeList() {

					List<SpCake> spCakeList;
					try {
					 spCakeList = spCakeListRepository.getSpCakeList();
					}
					catch(Exception e)
					{
						spCakeList=new ArrayList<>();
						e.printStackTrace();
					}
					return spCakeList;

				}
				//------------------------------------------------------------------------
		// ------------------------Getting One ItemSup by Id-----------------------
		@RequestMapping(value = { "/getItemSup" }, method = RequestMethod.POST)
		public @ResponseBody GetItemSup getItemSup(@RequestParam("id") int id) {

			GetItemSup getItemSupRes = null;
			try {
				getItemSupRes = itemService.getItemSup(id);

				if (getItemSupRes != null) {
					getItemSupRes.setError(false);
					getItemSupRes.setMessage("ItemSup Found Successfully");
				} else {
					getItemSupRes = new GetItemSup();
					getItemSupRes.setError(true);
					getItemSupRes.setMessage("ItemSup Not Found");
				}
			} catch (Exception e) {
				getItemSupRes = new GetItemSup();
				getItemSupRes.setError(true);
				getItemSupRes.setMessage("ItemSup Not Found");
				System.out.println("Exception In getItemSup:" + e.getMessage());
			}

			return getItemSupRes;

		}
		// ------------------------Getting One SpCakeSup by Id-----------------------
				@RequestMapping(value = { "/getSpCakeSupp" }, method = RequestMethod.POST)
				public @ResponseBody GetSpCkSupplement getSpCakeSupp(@RequestParam("id") int id) {

					GetSpCkSupplement getSpCkSupRes = null;
					try {
						getSpCkSupRes = spCakeService.getSpCakeSupp(id);

						if (getSpCkSupRes != null) {
							getSpCkSupRes.setError(false);
							getSpCkSupRes.setMessage("GetSpCkSupplement Found Successfully");
						} else {
							getSpCkSupRes = new GetSpCkSupplement();
							getSpCkSupRes.setError(true);
							getSpCkSupRes.setMessage("GetSpCkSupplement Not Found");
						}
					} catch (Exception e) {
						getSpCkSupRes = new GetSpCkSupplement();
						getSpCkSupRes.setError(true);
						getSpCkSupRes.setMessage("GetSpCkSupplement Not Found");
						System.out.println("Exception In getSpCakeSupp:" + e.getMessage());
					}

					return getSpCkSupRes;

				}
				// ------------------------Getting One SpCakeSup by Id-----------------------
				@RequestMapping(value = { "/getSpCakeSupplement" }, method = RequestMethod.POST)
				public @ResponseBody GetSpCkSupplement getSpCakeSupplement(@RequestParam("id") int id) {

					GetSpCkSupplement getSpCkSupRes = null;
					try {
						getSpCkSupRes = getSpCakeSupRepository.getSpCakeSupplement(id);

						if (getSpCkSupRes != null) {
							getSpCkSupRes.setError(false);
							getSpCkSupRes.setMessage("GetSpCkSupplement Found Successfully");
						} else {
							getSpCkSupRes = new GetSpCkSupplement();
							getSpCkSupRes.setError(true);
							getSpCkSupRes.setMessage("GetSpCkSupplement Not Found");
						}
					} catch (Exception e) {
						getSpCkSupRes = new GetSpCkSupplement();
						getSpCkSupRes.setError(true);
						getSpCkSupRes.setMessage("GetSpCkSupplement Not Found");
						System.out.println("Exception In getSpCakeSupp:" + e.getMessage());
					}

					return getSpCkSupRes;

				}

	// ----------------------------SAVE FranchiseSup---------------------------
		@RequestMapping(value = { "/saveFranchiseSup" }, method = RequestMethod.POST)
		public @ResponseBody Info saveFranchiseSup(@RequestBody FranchiseSup franchiseSup) {

			FranchiseSup franchiseSupRes = null;
			Info info = new Info();
			try {

				franchiseSupRes = franchiseeService.saveFranchiseSup(franchiseSup);

				if (franchiseSupRes != null) {
					info.setError(false);
					info.setMessage("FranchiseSup Saved Successfully.");
				} else {
					info.setError(true);
					info.setMessage("FranchiseSup Not Saved .");
				}

			} catch (Exception e) {

				info.setError(true);
				info.setMessage("FranchiseSup Not Saved .");

				e.printStackTrace();
				System.out.println("Exception In MasterController /saveFranchiseSup" + e.getMessage());

			}
			return info;

		}
      //---------------------------------------------------------------------------
	// ------------------------Delete FranchiseSup------------------------------------
				@RequestMapping(value = { "/deleteFranchiseSup" }, method = RequestMethod.POST)
				public @ResponseBody Info deleteFranchiseSup(@RequestParam int frId) {

					Info info = franchiseeService.deleteFranchiseSup(frId);
					return info;
				}
	 //------------------------------------------------------------------------
				
	// ---------------------------Getting FranchiseSup List-----------------------
				@RequestMapping(value = { "/getFranchiseSupList" }, method = RequestMethod.GET)
				public @ResponseBody FranchiseSupList getFranchiseSupList() {

					FranchiseSupList franchiseSupList = franchiseeService.getFranchiseSupList();

					return franchiseSupList;

				}
	//------------------------------------------------------------------------	
		// ------------------------Getting One FranchiseSup by Id-----------------------
				@RequestMapping(value = { "/getFranchiseSup" }, method = RequestMethod.POST)
				public @ResponseBody FranchiseSup getFranchiseSup(@RequestParam("id") int id) {

					FranchiseSup getFranchiseSupRes = null;
					try {
						getFranchiseSupRes = franchiseeService.getFranchiseSup(id);

						if (getFranchiseSupRes != null) {
							getFranchiseSupRes.setError(false);
							getFranchiseSupRes.setMessage("FranchiseSup Found Successfully");
						} else {
							getFranchiseSupRes = new FranchiseSup();
							getFranchiseSupRes.setError(true);
							getFranchiseSupRes.setMessage("FranchiseSup Not Found");
						}
					} catch (Exception e) {
						getFranchiseSupRes = new FranchiseSup();
						getFranchiseSupRes.setError(true);
						getFranchiseSupRes.setMessage("FranchiseSup Not Found");
						System.out.println("Exception In getFranchiseSup:" + e.getMessage());
					}

					return getFranchiseSupRes;

				}
				// ----------------------------SAVE FranchiseSup---------------------------
				@RequestMapping(value = { "/saveFrTarget" }, method = RequestMethod.POST)
				public @ResponseBody Info saveFrTarget(@RequestBody List<FrTarget> frTargetList) {

					Info	info = franchiseeService.saveFrTarget(frTargetList);

					return info;

				}
		      //---------------------------------------------------------------------------	
				// ---------------------------Getting FrTarget List-----------------------
				@RequestMapping(value = { "/getFrTargetList" }, method = RequestMethod.POST)
				public @ResponseBody FrTargetList getFrTargetList(@RequestParam("frId")int frId,@RequestParam("year")int year) {

					FrTargetList frTargetList = franchiseeService.getFrTargetList(frId,year);

					return frTargetList;

				}
	        //------------------------------------------------------------------------	
				// Get Items
				@RequestMapping(value = "/getItemsByIsAllowBday", method = RequestMethod.GET)
				public @ResponseBody List<Item> getItemsByIsAllowBday() {

					List<Item> items = itemService.getItemsByIsAllowBday();
					return items;

				}
				// ---------------------------Getting TotalFrSale List-----------------------
				@RequestMapping(value = { "/getFrTotalSale" }, method = RequestMethod.POST)
				public @ResponseBody FrTotalSale getFrTotalSale(@RequestParam("frId")int frId,@RequestParam("month")int month,@RequestParam("year")int year) {

					System.out.println("frId"+frId);
					FrTotalSale frTotalSale = franchiseeService.getFrTargetList(frId,month,year);

					return frTotalSale; 

				}
	        //------------------------------------------------------------------------		
				// --------------------------------Update Fr Admin password------------------
				@RequestMapping(value = { "/updateAdminPwd" }, method = RequestMethod.POST)
				public @ResponseBody Info updateAdminPwd(@RequestParam("frId")int frId,@RequestParam("adminPwd")String adminPwd) {

					Info info = franchiseeService.updateAdminPwd(frId,adminPwd);

					return info; 

				}
	        //------------------------------------------------------------------------			
				// ------------------------Update FranchiseSup Passwords------------------------------------
				@RequestMapping(value = { "/updateFranchiseSupUsrPwd" }, method = RequestMethod.POST)
				public @ResponseBody Info updateFranchiseUsrPwd(@RequestParam int frId,@RequestParam String pass2,@RequestParam String pass3) {

					Info info = franchiseeService.updateFranchiseSupUsrPwd(frId,pass2,pass3);
					return info;
				}
	 //------------------------------------------------------------------------				
				// ------------------------Getting One FranchiseSup by Id-----------------------
				@RequestMapping(value = { "/getFrSupByFrId" }, method = RequestMethod.POST)
				public @ResponseBody FranchiseSup getFrSupByFrId(@RequestParam("frId") int frId) {

					FranchiseSup getFranchiseSupRes = null;
					try {
						getFranchiseSupRes = franchiseeService.getFrSupByFrId(frId);

						if (getFranchiseSupRes != null) {
							getFranchiseSupRes.setError(false);
							getFranchiseSupRes.setMessage("FranchiseSup Found Successfully");
						} else {
							getFranchiseSupRes = new FranchiseSup();
							getFranchiseSupRes.setError(true);
							getFranchiseSupRes.setMessage("FranchiseSup Not Found");
						}
					} catch (Exception e) {
						getFranchiseSupRes = new FranchiseSup();
						getFranchiseSupRes.setError(true);
						getFranchiseSupRes.setMessage("FranchiseSup Not Found");
						System.out.println("Exception In getFranchiseSup:" + e.getMessage());
					}

					return getFranchiseSupRes;

				}		
				// ---------------------------Getting Franchise List For Supplement-----------------------
				@RequestMapping(value = { "/getFrListForSupp" }, method = RequestMethod.GET)
				public @ResponseBody List<FrListForSupp> getFrListForSupp() {

					List<FrListForSupp> franchiseSupList = frListForSuppRepository.getFrListForSupp();

					return franchiseSupList;

				}
	              //------------------------------------------------------------------------	
				// ------------------------Delete RegularSpOrder------------------------------------
				@RequestMapping(value = { "/deleteRegularSpOrder" }, method = RequestMethod.POST)
				public @ResponseBody Info deleteRegularSpOrder(@RequestParam int rspId) {

					Info info = regularSpCkOrderService.deleteRegularSpOrder(rspId);
					return info;
				}
	          //------------------------------------------------------------------------
				// ------------------------DeleteSpCkOrder------------------------------------
				@RequestMapping(value = { "/deleteSpCkOrder" }, method = RequestMethod.POST)
				public @ResponseBody Info deleteSpCkOrder(@RequestParam int spOrderNo) {

					int isDeleted =spCkDeleteOrderRepository.deleteSpCkOrder(spOrderNo);
					Info info=new Info();
					if(isDeleted==1)
					{
						info.setError(false);
						info.setMessage("SpCkOrder Deleted");
					}
					else
					{
						info.setError(true);
						info.setMessage("SpCkOrder Deletion Failed");
					}
					return info;
				}
	          //------------------------------------------------------------------------	
				// ------------------------Get Unique Franchise Code------------------------------------
				@RequestMapping(value = { "/getUnigueFrCode" }, method = RequestMethod.GET)
				public @ResponseBody Integer getUnigueFrCode() {

					int maxId =franchiseeRepository.getUnigueFrCode();
					
					return maxId;
				}
	          //------------------------------------------------------------------------
				// ------------------------Get Unique Item Code------------------------------------
				@RequestMapping(value = { "/getUniqueItemCode" }, method = RequestMethod.GET)
				public @ResponseBody Integer getUniqueItemCode() {

					int maxId =itemService.getUniqueItemCode();
					
					return maxId;
				}
	          //------------------------------------------------------------------------
				// ------------------------Get TrayTypes------------------------------------
				@RequestMapping(value = { "/getTrayTypes" }, method = RequestMethod.GET)
				public @ResponseBody List<TrayType> getTrayTypes() {

					List<TrayType> trayTypes =itemService.getTrayTypes();
					
					return trayTypes;
				}
	          //------------------------------------------------------------------------
				// Get Items
				@RequestMapping(value = "/getItemsByCatIdForSup", method = RequestMethod.POST)
				public @ResponseBody List<Item> getItemsByCatIdForSup(@RequestParam String itemGrp1) {

					List<Item> items = itemService.getItemsByCatIdForSup(itemGrp1);
					return items;

				}		
				// Get Items By Category order by sub cat and sort id
				@RequestMapping(value = "/getItemsByCatIdForDisp", method = RequestMethod.POST)
				public @ResponseBody List<Item> getItemsByCatIdForDisp(@RequestParam List<String> catIdList) {
		           
					List<Item> items=null;
					try {
					 items = itemRepository.findByItemGrp1InAndDelStatusOrderByItemGrp2AscItemNameAsc(catIdList,0);
					}/*findByItemGrp1InAndDelStatusOrderByItemGrp2AscItemSortIdAsc*/
					catch(Exception e)
					{
						items=new ArrayList<>();
						e.printStackTrace();
						
					}
					return items;

				}
				// Get SubCategories
				@RequestMapping(value = "/getSubCatList")
				public @ResponseBody List<SubCategory> getAllSubCategories(@RequestParam List<String> catId) {

					List<SubCategory> subCategoryList;
					try {
					 subCategoryList = subCategoryRepository.findByCatIdInAndDelStatus(catId);
					}
					catch (Exception e) {
						subCategoryList=new ArrayList<>();
						e.printStackTrace();

					}
					return subCategoryList;

				}
				// Get SubCategories
				@RequestMapping(value = "/getAllSubCatList", method = RequestMethod.GET)
				public @ResponseBody List<SubCategory> getAllSubCatList() {

					List<SubCategory> subCategoryList;
					try {
					 subCategoryList = subCategoryRepository.findAllSubCategories();
					}
					catch (Exception e) {
						subCategoryList=new ArrayList<>();
						e.printStackTrace();

					}
					return subCategoryList;

				}
				@RequestMapping(value = { "/getRegSpCakeOrderHistory" }, method = RequestMethod.POST)
				@ResponseBody
				public List<GetRegSpCakeOrders> getRegSpCakeOrderHistory(
						@RequestParam String spDeliveryDt,@RequestParam int  frId,@RequestParam int catId) {
					List<GetRegSpCakeOrders> regSpCakeOrder = regularSpCkOrderService.getRegSpCakeOrderHistory(spDeliveryDt, frId,catId);
					return regSpCakeOrder;
				}
				  public static boolean contains(int[] arr, int item) {
				      for (int n : arr) {
				         if (item == n) {
				            return true;
				         }
				      }
				      return false;
				   }
				@RequestMapping(value = "/updateConfiguredItems", method = RequestMethod.POST)
				public @ResponseBody Info updateConfiguredItems(@RequestParam List<String> frIdList,@RequestParam int menuId,@RequestParam List<String> itemIdList,@RequestParam int catId)
				{
					Info info=new Info();
					try {
						for(String frId:frIdList)
						{
							
						 ConfigureFranchisee configureFr=configureFrRepository.findByFrIdAndMenuIdAndDelStatus(Integer.parseInt(frId),menuId,0);
						
						 if(configureFr!=null) {
						 String itemShow=configureFr.getItemShow();
						 int[] intArray = null;
						 try {
						  intArray = Arrays.stream(itemShow.split(","))
								    .mapToInt(Integer::parseInt)
								    .toArray();
						 }
						 catch (Exception e) {
							e.printStackTrace();
						}
						  for(String itemId:itemIdList)
						  {
							  if(intArray.length!=0)
							  {
							  if(!contains(intArray, Integer.parseInt(itemId)))
							  {
						     	itemShow=itemShow+","+itemId;
							  }
							  }
						  }
						  configureFr.setItemShow(itemShow);
						  
						  ConfigureFranchisee configureFranchiseeReport=configureFrRepository.save(configureFr);
						 
						  try {

							  PostFrItemStockHeader stockHeader=postFrOpStockHeaderRepository.findByFrIdAndCatIdAndIsMonthClosed(Integer.parseInt(frId),catId,0);

							  if(stockHeader!=null)
							  {				  

							  for(String itemId:itemIdList)
							  {
								 
								  PostFrItemStockDetail stockDetailRes=postFrOpStockDetailRepository.findByOpeningStockHeaderIdAndItemId(stockHeader.getOpeningStockHeaderId(),Integer.parseInt(itemId));
							  
								  if(stockDetailRes==null)
									  {
									    PostFrItemStockDetail stockDetail=new PostFrItemStockDetail();
							            stockDetail.setOpeningStockHeaderId(stockHeader.getOpeningStockHeaderId());
							            stockDetail.setItemId(Integer.parseInt(itemId));		  
							            postFrOpStockDetailRepository.save(stockDetail);
									  }
							  }
							  }
							  
						  }catch (Exception e) {
							e.printStackTrace();
						}
						 }
						  
						}
						info.setError(false);
						info.setMessage("CF Updated");
					}catch (Exception e) {
						e.printStackTrace();
						info.setError(true);
						info.setMessage("CF Updation Failed");
					}
					
					return info;
				}
		           
				
}
