package ir.ac.kntu.objects;

public class Location {
    private int x;
    private int y;

    public Location(int x, int y) {
        setX(x);
        setY(y);
    }

    public Location(String xLocation, String yLocation) {
        setY(y);
        setX(x);
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public Location(){}
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public long getDistanceFrom(Location location) {
        return (long) Math.sqrt(Math.pow(this.x - location.getX(), 2) + Math.pow(this.y - location.getY(), 2));
    }
}
