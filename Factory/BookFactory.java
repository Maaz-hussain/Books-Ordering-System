
package Factory;
public class BookFactory { 
    public Factory.Book getFactory(String FactoryType) {
        if (FactoryType == null) {
            return null;
        }
        if (FactoryType.equalsIgnoreCase("author")) {
            return new ItemCategoryClass();
        } else if (FactoryType.equalsIgnoreCase("book")) {
            return new ItemClass();
        } else if (FactoryType.equalsIgnoreCase("staff")) {
            return new StaffClass();
        } 
        return null;
    }
}
