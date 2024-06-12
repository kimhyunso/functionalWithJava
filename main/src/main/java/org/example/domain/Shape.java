package org.example.domain;


import java.util.List;

public record Shape(int corners) implements Comparable<Shape>{

    public int cornerCount(){
        return corners();
    }

    public boolean hasCorners(){
        return corners() > 0;
    }

    public List<Shape> twice(){
        return List.of(this, this);
    }

    @Override
    public int compareTo(Shape o) {
        return Integer.compare(corners(), o.corners());
    }

    public static Shape circle(){
        return new Shape(0);
    }

    public static Shape triangle(){
        return new Shape(3);
    }

    public static Shape square(){
        return new Shape(4);
    }
}
