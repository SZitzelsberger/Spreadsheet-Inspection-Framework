package sif.technicalDepartment.management;

import java.util.Comparator;

import sif.technicalDepartment.equipment.scanning.ElementScanner;

/***
 * Compares{@link ElementScanner}s by their scan priority.
 * 
 * @author Sebastian Zitzelsberger
 * 
 */
public class ElementScannerComparator implements Comparator<ElementScanner<?>> {

	@Override
	public int compare(ElementScanner<?> o1, ElementScanner<?> o2) {

		int result = 0;
		if (!o1.getScannedElementClass().equals(o2.getScannedElementClass())) {
			result = o1.getScanPriority().compareTo(o2.getScanPriority());
			// If priority is the same, scanners are not equal.
			if (result == 0) {
				// o1 comes before o2.
				result = 1;
			}
		}

		return result;
	}
}
