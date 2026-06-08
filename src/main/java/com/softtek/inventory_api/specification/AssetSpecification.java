package com.softtek.inventory_api.specification;

import com.softtek.inventory_api.dto.AssetFilterDTO;
import com.softtek.inventory_api.entity.Asset;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class AssetSpecification {

    private AssetSpecification() {
    }

    public static Specification<Asset> filter(AssetFilterDTO filter) {



        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (filter.serialNumber() != null && !filter.serialNumber().isBlank()) {

                predicates.add(
                        cb.like(
                                cb.lower(
                                        root.get("serialNumber")
                                ),
                                "%" +
                                        filter.serialNumber()
                                                .toLowerCase()
                                        + "%"
                        )
                );
            }
            if (filter.brandModel() != null && !filter.brandModel().isBlank()) {

                predicates.add(
                        cb.like(
                                cb.lower(
                                        root.get("brandModel")
                                ),
                                "%" +
                                        filter.brandModel()
                                                .toLowerCase()
                                        + "%"
                        )
                );
            }

            if (filter.categoryId() != null) {

                predicates.add(
                        cb.equal(
                                root.get("category")
                                        .get("id"),
                                filter.categoryId()
                        )
                );
            }

            if (filter.status() != null) {

                predicates.add(
                        cb.equal(
                                root.get("status"),
                                filter.status()
                        )
                );
            }

            if (filter.costMin() != null) {

                predicates.add(
                        cb.greaterThanOrEqualTo(
                                root.get("acquisitionCost"),
                                filter.costMin()
                        )
                );
            }

            if (filter.costMax() != null) {

                predicates.add(
                        cb.lessThanOrEqualTo(
                                root.get("acquisitionCost"),
                                filter.costMax()
                        )
                );
            }

            return cb.and(
                    predicates.toArray(
                            new Predicate[0]
                    )
            );
        };
    }
}
