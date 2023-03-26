import java.lang.reflect.Field;
import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);
        List<Laptop> laptopList = new ArrayList<>();
        InitFilling(laptopList);
        String action;
        System.out.println("Выберите действие:");
        Map<String,String> filter = CreatFilterMap();
        StartMenu();
        boolean loop = true;
        while(loop){
            action = scanner.nextLine();
            if(action.equals("1")){
                Laptop laptop = CreateLaptop(scanner);
                laptopList.add(laptop);
            }
            if (action.equals("2")) {
                Map<String, String> categoriesMap = SelectCategory(filter, scanner);
                System.out.println(Filter(laptopList, filter, categoriesMap));
            }else {
                System.exit(0);
            }
        }
    }

    public static Laptop CreateLaptop(Scanner scanner){
        Laptop laptop = new Laptop();
        System.out.println("Введите марку: ");
        laptop.brand = scanner.nextLine();
        System.out.println("Введите ОС: ");
        laptop.os = scanner.nextLine();
        System.out.println("Введите Ж/Д: ");
        laptop.hdd = scanner.nextLine();
        System.out.println("Введите ОЗУ: ");
        laptop.ram = scanner.nextLine();
        System.out.println("Введите наличие сенсорного экрана Y/N: ");
        if (scanner.nextLine().equals("Y")){
            laptop.touchScreen = true;
        }else{
            laptop.touchScreen = false;
        }
        return laptop;
    }

    public static Laptop CreateLaptop(String brand, String os, String hdd, String ram, boolean touchScreen){
        Laptop laptop = new Laptop();
        laptop.brand = brand;
        laptop.os = os;
        laptop.hdd = hdd;
        laptop.ram = ram;
        laptop.touchScreen = touchScreen;

        return laptop;
    }

    public static void StartMenu(){
        System.out.println("1 - Добавить ноутбук");
        System.out.println("2 - Фильтр ноутбуков");
        System.out.println("3 - Выход");
    }

    public static  Map<String,String> CreatFilterMap(){
        Map<String,String> filter = new HashMap<>();
        filter.put("1","brand");
        filter.put("2","os");
        filter.put("3","hdd");
        filter.put("4","ram");
        filter.put("5","touchScreen");
        filter.put("6","exit");
        return filter;
    }

    public static void InitFilling(List<Laptop> notebookList){
        notebookList.add(CreateLaptop("ASUS","Windows","128","8",true));
        notebookList.add(CreateLaptop("HP","Windows","512","16",false));
        notebookList.add(CreateLaptop("Acer","Windows","512","16",false));
        notebookList.add(CreateLaptop("Apple","macOS","512","8",true));
        notebookList.add(CreateLaptop("HP","ChromeOS","128","8",true));
    }

    public static Map<String,String > SelectCategory( Map<String,String> filter,Scanner scanner){
        System.out.println("Выберите категории поиска");
        boolean loopFilter = true;
        Map<String, String> categoriesMap = new HashMap<>();
        while (loopFilter) {
            for (Map.Entry entry : filter.entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }
            String actionFilter;
            actionFilter = scanner.nextLine();
            if (!actionFilter.equals("6")) {
                System.out.println("Введите значение");
                categoriesMap.put(actionFilter, scanner.nextLine());
            } else {
                loopFilter = false;
            }
        }
        return categoriesMap;
    }

    public static List<Laptop> Filter(List<Laptop> laptopList, Map<String,String> filter, Map<String,String> categoriesMap) throws Exception{
        List<Laptop> filterList = new ArrayList<>();
        boolean isFilter = false;
        for (Laptop laptop : laptopList) {
            isFilter = false;
            Class<?> c = laptop.getClass();
            for (Map.Entry entry : categoriesMap.entrySet()) {
                Field chap = c.getField(filter.get(entry.getKey()));
                if (chap.get(laptop).equals(entry.getValue())) {
                    isFilter = true;
                }else {
                    isFilter = false;
                    break;
                }
            }
            if(isFilter){
                filterList.add(laptop);
            }
        }
        return filterList;

    }
}
