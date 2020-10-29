public class Point {
    int x,y;
    public void move(int dx, int dy){
        x += dx;
        y += dy;
    }
}

class Point3d extends Point {
    int z;
    public void move(int dx, int dy, int dz){
        x += dx;
        y += dy;
        z += dz;
    }
}
