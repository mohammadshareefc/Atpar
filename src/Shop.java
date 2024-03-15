public class Shop {
    public static void main(String args[]){
        int number=36;
    String size="large";
    switch (number){
        case 30:
        size = "small";
        break;
        case 32:
        size = "medium";
        break;
        case 34:
        size = "large";
        break;
        case 36:
        size = "x large";
    }
    System.out.println("size:" + size);
    }
}
