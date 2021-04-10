package accountproperties;

import org.javatuples.Triplet;

public interface AccountProperty {

    Triplet<String, Object, Integer> process(String line, int offset) throws RuntimeException;
}
