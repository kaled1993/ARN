package Bi_color_tree;


abstract public class ArrayFactory<T> {

	/**
	 * Build an array of 'dimension' element
	 * @param dimension the array dimension
	 * @return the built array
	 */
    abstract public T[] buildArray(int dimension);

	// ######
    // Classic Static
	// ######

	/**
	 * Static string array factory
	 * @return the factory
	 */
	public static ArrayFactory<String> buildStringFactory(){
        return new ArrayFactory<String>() {
            @Override
            public String[] buildArray(int dimension) {
                return new String[dimension];
            }
        };
    }

	/**
	 * Static int array factory
	 * @return the factory
	 */
	public static ArrayFactory<Integer> buildIntegerFactory(){
		return new ArrayFactory<Integer>() {
			@Override
			public Integer[] buildArray(int dimension) {
				return new Integer[dimension];
			}
		};
	}



}