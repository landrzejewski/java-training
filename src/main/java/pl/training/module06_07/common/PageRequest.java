package pl.training.module06_07.common;

public record PageRequest(int index, int size) {

    public int offest() {
        return index * size;
    }

}
