package service.seller;

import java.util.List;
import java.util.stream.Collectors;

import dao.seller.ProductDAO;
import dao.seller.ProductDAOImpl;
import dao.seller.SellerDAO;
import dto.seller.Product;
import dto.seller.ProductOption;

public class ProductServiceImpl implements ProductService {
	private ProductDAO productDao;

	public ProductServiceImpl() {
		productDao = new ProductDAOImpl();
	}

	@Override
	public void addProduct(Product product, List<ProductOption> options) throws Exception {
		productDao.insertProduct(product);

		for (ProductOption option : options) {
			option.setProductNum(product.getProductNum());
			productDao.insertProductOption(option);
		}
	}

	@Override
	public Product selectProduct(Long productNum) throws Exception {
		return productDao.selectProduct(productNum);
	}

	@Override
	public void updateProduct(Product product, List<ProductOption> options) throws Exception {
		productDao.updateProduct(product);

		List<ProductOption> oldOptions = productDao.selectProductOption(product.getProductNum());

		// 현재 넘어온 옵션들의 번호만 추출
		List<Long> newOptionNums = options.stream().map(ProductOption::getOptionNum).filter(o -> o != null)
				.collect(Collectors.toList());

		// 1. 삭제할 옵션
		for (ProductOption oldOpt : oldOptions) {
			if (!newOptionNums.contains(oldOpt.getOptionNum())) {
				productDao.deleteProductOption(oldOpt.getOptionNum());
			}
		}

		// 2. 추가/수정 처리
		for (ProductOption opt : options) {
			if (opt.getOptionNum() == null) {
				opt.setProductNum(product.getProductNum());
				productDao.insertProductOption(opt);
			} else {
				productDao.updateProductOption(opt);
			}
		}
	}

	@Override
	public void updateProductStatus(Product product) throws Exception {
		productDao.updateProductStatus(product);
	}

	@Override
	public void addProductOption(ProductOption productOption) throws Exception {
		productDao.insertProductOption(productOption);
	}

	@Override
	public List<ProductOption> selectProductOption(Long productNum) throws Exception {
		return productDao.selectProductOption(productNum);
	}

	@Override
	public void updateProductOption(ProductOption productOption) throws Exception {
		productDao.updateProductOption(productOption);
	}

	@Override
	public void deleteProductOption(Long optionNum) throws Exception {
		productDao.deleteProductOption(optionNum);
	}

	@Override
	public List<Product> selectSellerProductList(Long sellerNum) throws Exception {
		// TODO Auto-generated method stub
		return productDao.selectProductList(sellerNum);
	}
}
