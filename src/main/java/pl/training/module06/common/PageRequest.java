package pl.training.module06.common;

public record PageRequest(int index, int size) {

    public int offest() {
        return index * size;
    }

}
