package pl.training.module06.common;

import java.util.List;

public record Page<T>(List<T> items, long totalPages) {
}
