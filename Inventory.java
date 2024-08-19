import java.util.*;
import java.util.function.Consumer;

class Product{
    int id;
    String name;
    int price;
    String category;

    public Product(int id, String name, int price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name=" + name +
                ", price=" + price +
                ", category=" + category ;
    }
}

public class Inventory {

    public static void addProduct(List<Product> products, Product p){
        Consumer <Product> c = (product -> products.add(product));
        c.accept(p);
    }

    public static Optional<Product> searchProduct(List<Product> products, int id){
        Optional<Product> p = products.stream().filter(n -> n.id==id).findFirst();
        return p;
    }

    public static void filterAndSort(List<Product> products, String sortingCategory){
        products.stream()
                .filter(product -> (product.category).equals(sortingCategory))
                .sorted(((o1, o2) -> o1.price>o2.price ? 1 : -1))
                .forEach(System.out::println);
    }

    public static void displayProduct(List<Product> products){
        products.stream().map(n->n.name).forEach(System.out::println);
    }

    public static void main(String[] args) {
        List <Product> products = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Choose option: \n 1. Add Product \n 2. Search Product By Id \n 3. Filter by Category and Sort by Price \n 4. Display Products \n 5. Exit");
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Enter product details (id, name, price, category):");
                    int id = sc.nextInt();
                    String name = sc.next();
                    int price = sc.nextInt();
                    String category = sc.next();
                    addProduct(products, new Product(id, name, price, category));
                    break;
                case 2:
                    System.out.println("Enter Id:");
                    int searchId = sc.nextInt();
                    Optional<Product> o = searchProduct(products, searchId);
                    System.out.println(o);
                    break;
                case 3:
                    System.out.println("Enter the category by which products should be sorted:");
                    String sortingCategory = sc.next();
                    filterAndSort(products, sortingCategory);
                    break;
                case 4:
                    displayProduct(products);
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Enter valid choice");
            }
        }
    }
}
