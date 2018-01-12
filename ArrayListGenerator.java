package Bi_color_tree;



import java.util.ArrayList;
import java.util.List;

abstract public class ArrayListGenerator<T extends Comparable> extends ArrayFactory<T> {

	/**
	 * Build an object T
     * @return the built object T
     */
    abstract T buildObject();

	/**
	 * Build 'count' object and return them into a List
     * @param count the number of object
     * @return the generated objects
     */
    final public List<T> generate(final int count){
        final ArrayList<T> res = new ArrayList<>();
        for(int i = 0; i < count; i++){
            res.add(this.buildObject());
        }
        return res;
    }; 
}