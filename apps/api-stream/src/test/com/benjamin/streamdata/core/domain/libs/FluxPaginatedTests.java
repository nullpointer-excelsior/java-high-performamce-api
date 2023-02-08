package com.benjamin.streamdata.core.domain.libs;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;



public class FluxPaginatedTests {

    @Test
    void paginatedTests() {

        var flux = FluxPaginated.paginate(10).take(3);

        StepVerifier.create(flux)
                .expectNext(
                        new FluxPaginated(10, 0),
                        new FluxPaginated(10, 10),
                        new FluxPaginated(10, 20)
                )
                .expectComplete()
                .verify();


    }

}
