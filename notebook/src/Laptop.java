import java.util.Objects;

public class Laptop {
    public String brand;
    public String os;
    public String ram;
    public String hdd;
    public boolean touchScreen;

    public String getHdd() {
        return hdd;
    }

    public void setHdd(String hdd) {
        this.hdd = hdd;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public boolean isTouchScreen() {
        return touchScreen;
    }
    public void setTouchScreen(boolean touchScreen) {
        this.touchScreen = touchScreen;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "brand='" + brand + '\'' +
                ", os='" + os + '\'' +
                ", ram=" + ram +
                ", hdd=" + hdd +
                ", touchScreen=" + touchScreen +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Laptop laptop = (Laptop) o;
        return Objects.equals(ram, laptop.ram) && Objects.equals(hdd, laptop.hdd) && touchScreen == laptop.touchScreen && Objects.equals(brand, laptop.brand) && Objects.equals(os, laptop.os);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, os, ram, hdd, touchScreen);
    }
}
