package pl.training.module06.model.repository;

import pl.training.module06.model.Account;

import java.util.List;

public record Page(List<Account> accounts, long totalPages) {
}
