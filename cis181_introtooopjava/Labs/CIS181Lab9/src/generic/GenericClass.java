package generic;

// define a class with generic programming
// generic indicates an unknown data type, when a data type cannot be determined
// generic accepts any data type
// a data type is determined when an object is instantiated

public class GenericClass<E> {
    private E name;

    public GenericClass() {
    }

    public GenericClass(E name) {
        this.name = name;
    }

    public E getName() {
        return name;
    }

    public void setName(E name) {
        this.name = name;
    }
}
