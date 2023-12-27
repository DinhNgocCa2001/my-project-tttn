package com.bookstore.services;

import com.bookstore.dao.IProductDAO;
import com.bookstore.entity.DTO.ColorItem;
import com.bookstore.entity.DTO.SizeAndColorItem;
import com.bookstore.entity.DTO.SizeItem;
import com.bookstore.entity.ProductVariant;
import com.bookstore.repo.ProductVariantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductVariantService implements IProductVariantService {

	private boolean defaultStatus = true;
	@Autowired
	private IProductDAO dao;

	private ProductVariantRepo repo;

	public ProductVariantService(ProductVariantRepo repository) {
		this.repo = repository;
	}

//	@Override
//	public List<User> getUsers() {
//		return dao.getUsers();
//	}

//	public static Long generateUniqueId() {
//		Random random = new Random();
//		long min = 10000L;
//		long max = 99999L;
//		return min + ((long) (random.nextDouble() * (max - min)));
//	}
//
//	@Override
//	public User createUser(User user) {
//		user.setId(generateUniqueId());
//		user.setUsername(user.getUsername());
//		user.setPassword(user.getPassword());
//		return repo.save(user);
//	}
//
//	@Override
//	public User LoginUser(User user) {
//		user.setId(generateUniqueId());
//		user.setUsername(user.getUsername());
//		user.setPassword(user.getPassword());
//		Optional<User> abc = repo.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//		return abc.get();
//	}

//	@Override
//	public User updateUser(Long productId, User product) {
//		return dao.updateUser(productId, product);
//	}
//
	@Override
	public List<Map<String, Object>> getByProductId(Long productId) {
		return repo.getByProductId(productId);
	}

	@Override
	public ProductVariant findBySizeIdAndColorIdAndProductId(Long sizeId, Long colorId, Long productId) {
		Optional<ProductVariant> result = repo.findBySizeIdAndColorIdAndProductId(sizeId, colorId, productId);
		return result.get();
	}

	@Override
	public List<ColorItem> getColorByProductId(Long productId) {
		try{
			List<Object[]> listObj =  repo.getColorByProductId(productId);
			List<ColorItem> listColorItem = new ArrayList<ColorItem>();
			for (Object[] obj : listObj) {
				ColorItem colorItem = new ColorItem();
				colorItem = convert_result_to_colorItem(obj);

				listColorItem.add(colorItem);
			}
			return listColorItem;
		}catch (Exception ex){
			System.out.println(ex);
			List<ColorItem> result = null;
			return result;
		}
	}

	private ColorItem convert_result_to_colorItem(Object[] obj) {
		ColorItem colorItem = new ColorItem();
		colorItem.setColorId(Objects.nonNull(obj[0]) ? ((Integer) obj[0]).longValue() : null);
		colorItem.setColorName(Objects.nonNull(obj[1]) ? obj[1].toString() : null);
		return colorItem;
	}

	@Override
	public List<SizeItem> getSizeByProductId(Long productId) {
		try{
			List<Object[]> listObj =  repo.getSizeByProductId(productId);
			List<SizeItem> listSizeItem = new ArrayList<SizeItem>();
			for (Object[] obj : listObj) {
				SizeItem sizeItem = new SizeItem();
				sizeItem = convert_result_to_sizeItem(obj);
				listSizeItem.add(sizeItem);
			}
			return listSizeItem;
		}catch (Exception ex){
			System.out.println(ex);
			List<SizeItem> result = null;
			return result;
		}
	}

	private SizeItem convert_result_to_sizeItem(Object[] obj) {
		SizeItem sizeItem = new SizeItem();
		sizeItem.setSizeId(Objects.nonNull(obj[0]) ? ((Integer) obj[0]).longValue() : null);
		sizeItem.setSizeName(Objects.nonNull(obj[1]) ? obj[1].toString() : null);
		return sizeItem;
	}

	@Override
	public List<SizeAndColorItem> getSizeAndColorByProductId(Long productId) {
		try{
			List<Object[]> listObj =  repo.getSizeAndColorByProductId(productId);
			List<SizeAndColorItem> listSizeItem = new ArrayList<SizeAndColorItem>();
			for (Object[] obj : listObj) {
				SizeAndColorItem sizeAndColorItem = new SizeAndColorItem();
				sizeAndColorItem = convert_result_to_size_color_Item(obj);
				listSizeItem.add(sizeAndColorItem);
			}
			return listSizeItem;
		}catch (Exception ex){
			System.out.println(ex);
			List<SizeAndColorItem> result = null;
			return result;
		}
	}

	private SizeAndColorItem convert_result_to_size_color_Item(Object[] obj) {
		SizeAndColorItem sizeItem = new SizeAndColorItem();
		sizeItem.setSizeId(Objects.nonNull(obj[0]) ? ((Integer) obj[0]).longValue() : null);
		sizeItem.setColorId(Objects.nonNull(obj[1]) ? ((Integer) obj[1]).longValue() : null);
		sizeItem.setSizeName(Objects.nonNull(obj[2]) ? obj[2].toString() : null);
		sizeItem.setColorName(Objects.nonNull(obj[3]) ? obj[3].toString() : null);
		return sizeItem;
	}
}
