package property;

import org.javatuples.Triplet;

public interface PropertyProcessor {
    Triplet<String, Object, Integer> process(String line, int offset) throws RuntimeException;
}
