package com.benjamin.streamdata.core.domain.libs;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.stream.IntStream;
import java.util.stream.Stream;

@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class StreamPaginated {

    @EqualsAndHashCode.Include
    private int limit;
    @EqualsAndHashCode.Include
    private int offset;

    public static Stream<StreamPaginated> paginate(int size) {
        return IntStream
                .iterate(0, i -> i + size)
                .boxed()
                .map(skip -> new StreamPaginated(size, skip));
    }

}
