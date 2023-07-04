package tiendamarket.persistence.mapper;

import org.mapstruct.*;
import tiendamarket.domain.Category;
import tiendamarket.persistence.entity.Categoria;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mappings({
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "descripcion", target = "category"),
            @Mapping(source = "estado", target = "active")
    })
    Category toCategory(Categoria categoria);

    @InheritInverseConfiguration
    Categoria toCategoria(Category category);
}
