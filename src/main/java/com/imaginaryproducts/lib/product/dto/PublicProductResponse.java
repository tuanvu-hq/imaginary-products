package com.imaginaryproducts.lib.product.dto;

import java.util.List;

public record PublicProductResponse(
        List<PublicProductDTO> data,
        Integer currentPage,
        Integer lastPage,
        Integer resultsPerPage,
        Integer totalResults
) {

}
