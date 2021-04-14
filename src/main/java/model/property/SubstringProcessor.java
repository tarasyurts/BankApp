package model.property;

import org.javatuples.Triplet;

public interface SubstringProcessor {
    Triplet<String, Object, Integer> process(String line, int offset) throws RuntimeException;
}
