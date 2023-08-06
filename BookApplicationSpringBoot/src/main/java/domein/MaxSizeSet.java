package domein;
import java.util.HashSet;

public class MaxSizeSet<E> extends HashSet<E> {
  
	private final int maxSize;

    public MaxSizeSet(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public boolean add(E e) {
        if (size() >= maxSize) {
        	
            // Handle the case when the set is already at maximum size
            return false;
        }
        return super.add(e);
    }
}
