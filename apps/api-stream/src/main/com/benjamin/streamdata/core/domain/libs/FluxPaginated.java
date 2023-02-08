package com.benjamin.streamdata.core.domain.libs;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import reactor.core.publisher.Flux;


@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FluxPaginated {

    @EqualsAndHashCode.Include
    private int limit;
    @EqualsAndHashCode.Include
    private int offset;

    public static Flux<FluxPaginated> paginate(int size) {

        Flux<Integer> numbers = Flux.generate(
                 () -> 0,
                 (state, sink) -> {
                    sink.next(state);
                    return state + size;
                });

         return numbers.map(value -> new FluxPaginated(size, value));
    }

}
