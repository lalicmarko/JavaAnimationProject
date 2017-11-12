package main;
/**
 * Interfejs koji implementiraju svi oni objekti koje cemo da bojimo 
 * @author lalicmarko
 *
 */
public interface ImageModifications {

	public void doContrast();
	public void doNegative();
	public void doGrayscale();
	public void doPosterize();
}
