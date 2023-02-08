package com.benjamin.streamdata.core.domain.application;


import com.benjamin.streamdata.core.application.ReactiveProductUseCases;
import com.benjamin.streamdata.core.application.dto.Brand;
import com.benjamin.streamdata.core.domain.model.Product;
import com.benjamin.streamdata.core.domain.services.ReactiveProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class ReactiveProductUseCasesTests {

    private final ReactiveProductService mock = Mockito.mock(ReactiveProductService.class);

    Product buildProduct(Integer sku,String name,String department,Double price,Integer stock, String brand){
        return Product.builder()
                .sku(sku)
                .name(name)
                .department(department)
                .price(price)
                .stock(stock)
                .brand(brand)
                .build();
    }


    @Test
    void getBrandsTest() {

        Mockito
                .when(mock.getFlux())
                .thenReturn(Flux.just(
                        buildProduct(1,"p1","1",1000.0,10,"adidas"),
                        buildProduct(2,"p2","1",1000.0,10,"adidas"),
                        buildProduct(3,"p3","10",1000.0,10,"zara")
                ));

        var useCases = new ReactiveProductUseCases(mock);

        var flux = useCases.getBrands();
        StepVerifier.create(flux)
                .expectNextCount(2)
                .expectComplete()
                .verify();
    }

    @Test
    void getSkuWithoutStockTest() {
        Mockito
                .when(mock.getFlux())
                .thenReturn(Flux.just(
                        buildProduct(1,"p1","1",1000.0,10,"adidas"),
                        buildProduct(2,"p2","1",1000.0,0,"adidas"),
                        buildProduct(3,"p3","10",1000.0,-1,"zara")
                ));

        var useCases = new ReactiveProductUseCases(mock);

        var flux = useCases.getProductWithoutStock().map(Product::getSku);
        StepVerifier.create(flux)
                .expectNext(2, 3)
                .expectComplete()
                .verify();

    }

    @Test
    void countSkuWithoutStockTest() {
        Mockito
                .when(mock.getFlux())
                .thenReturn(Flux.just(
                        buildProduct(1,"p1","1",1000.0,10,"adidas"),
                        buildProduct(2,"p2","1",1000.0,0,"adidas"),
                        buildProduct(3,"p3","10",1000.0,-1,"zara")
                ));

        var useCases = new ReactiveProductUseCases(mock);

        var flux = useCases.countSkuWithoutStock();
        StepVerifier.create(flux)
                .expectNext(2L)
                .expectComplete()
                .verify();
    }

    @Test
    void sumStockDepartment604Test() {
        Mockito
                .when(mock.getFlux())
                .thenReturn(Flux.just(
                        buildProduct(1,"p1","604",1000.0,10,"adidas"),
                        buildProduct(2,"p2","604",1000.0,10,"adidas"),
                        buildProduct(3,"p3","10",1000.0,-1,"zara")
                ));

        var useCases = new ReactiveProductUseCases(mock);

        var flux = useCases.sumStockDepartment604();
        StepVerifier.create(flux)
                .expectNext(20)
                .expectComplete()
                .verify();

    }

    @Test
    void groupByDepartment604BrandTest() {
        Mockito
                .when(mock.getFlux())
                .thenReturn(Flux.just(
                        buildProduct(1,"p1","604",1000.0,10,"adidas"),
                        buildProduct(2,"p2","604",1000.0,10,"adidas"),
                        buildProduct(3,"p3","604",1000.0,-1,"zara")
                ));

        var useCases = new ReactiveProductUseCases(mock);

        var flux = useCases.groupByDepartment604Brand().map(Brand::name);
        StepVerifier.create(flux)
                .expectNext("adidas")
                .expectNext("zara")
                .expectComplete()
                .verify();

    }

}