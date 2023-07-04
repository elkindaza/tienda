package tiendamarket.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tiendamarket.domain.Product;
import tiendamarket.domain.repository.ProductRepository;
import tiendamarket.persistence.crud.ProductoCrudRepository;
import tiendamarket.persistence.entity.Producto;
import tiendamarket.persistence.mapper.ProductMapper;

import java.util.List;
import java.util.Optional;


@Repository
public class ProductoRepository implements ProductRepository {
    @Autowired
    private ProductoCrudRepository productoCrudRepository;
    @Autowired
    private ProductMapper mapper;

    @Override
    public List<Product> getAll(){

        List<Producto> productos= (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos=productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos=productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity,true);
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return  productoCrudRepository.findById(productId).map(producto -> mapper.toProduct(producto));
    }

    @Override
    public Product save(Product product) {
        Producto producto=mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepository.save(producto));
    }






    public void delete(int idProducto){
        productoCrudRepository.deleteById(idProducto);
    }
}
