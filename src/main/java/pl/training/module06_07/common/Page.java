package pl.training.module06_07.common;

import java.util.List;

public record Page<T>(List<T> items, long totalPages) {
}
